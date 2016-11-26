package com.example.roman.lesaratest.loaders;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.annotation.NonNull;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.LocalBroadcastManager;

import com.example.roman.lesaratest.Model.ListDataModel;
import com.example.roman.lesaratest.Model.RootObject;
import com.example.roman.lesaratest.Model.TrendProducts;
import com.example.roman.lesaratest.RestApi;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by roman on 24/11/16.
 */

public class ProductsListLoader extends AsyncTaskLoader<ListDataModel> {

    public static final String FETCH_NEW_PAGE_ACTION = "FETCH_NEW_PAGE_ACTION";

    private ListDataModel dataModel;
    private TrendProducts lastFetched;
    private FetchMoreDataReceiver receiver;
    private int indexToLoad = 1;

    public ProductsListLoader(Context context) {
        super(context);
    }

    @Override
    public ListDataModel loadInBackground() {
        try {
            Gson gson = new Gson();
            final String json = loadJson(RestApi.PRODUCTS_LIST_URL.replace("[page]",Integer.toString(indexToLoad)));
            lastFetched = gson.fromJson(json, RootObject.class).getTrendProducts();
        }catch (IOException ignored){}

        if (lastFetched.getCurrentPage() >= lastFetched.getNumberPages()){
            dataModel.setEndOfListReached(true);
            return dataModel;
        }

        if (dataModel == null){
            dataModel = new ListDataModel(lastFetched.getProducts());
        }else{
            // create new object because loaderManager checks if new data isn't equals to old data
            dataModel = new ListDataModel(dataModel.products,lastFetched.getProducts());
        }
        dataModel.setPageSize(lastFetched.getProducts().size());
        return dataModel;
    }

    @Override
    protected void onStartLoading() {
        if (receiver == null){
            receiver = new FetchMoreDataReceiver(this);
            LocalBroadcastManager.getInstance(getContext()).
                    registerReceiver(receiver, new IntentFilter(FETCH_NEW_PAGE_ACTION));
        }
        if (takeContentChanged() || dataModel == null){
            forceLoad();
        }else {
            deliverResult(dataModel);
        }
    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        indexToLoad++;
    }

    @Override
    protected void onReset() {
        dataModel = null;
        lastFetched = null;
        indexToLoad = 0;
        LocalBroadcastManager.getInstance(getContext()).unregisterReceiver(receiver);
        receiver = null;
    }

    @NonNull
    private String loadJson(String url) throws IOException {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    static class FetchMoreDataReceiver extends BroadcastReceiver {

        private ProductsListLoader loader;

        FetchMoreDataReceiver(ProductsListLoader loader) {
            this.loader = loader;
        }

        @Override
        public void onReceive(Context context, Intent intent) {
            loader.onContentChanged();
        }
    };
}