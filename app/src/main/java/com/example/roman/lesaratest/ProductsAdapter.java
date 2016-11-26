package com.example.roman.lesaratest;

import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.roman.lesaratest.Model.ListDataModel;
import com.example.roman.lesaratest.Model.Product;
import com.example.roman.lesaratest.loaders.ProductsListLoader;
import com.squareup.picasso.Picasso;

import java.util.Locale;

/**
 * Created by roman on 24/11/16.
 */

public class ProductsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final int FOOTER = 1;
    public static final int REGULAR = 0;


    private ListDataModel listDataModel;

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == REGULAR) {
            return new ViewHolderRegular(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false));
        }else{
            return new ViewHolderFooter(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_footer, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ViewHolderRegular) {
            ((ViewHolderRegular)holder).setModel(getItem(position));
        }else{
            if (listDataModel.isEndOfListReached()) {
                ((ViewHolderFooter)holder).decorateLastItem();
            }
        }
        if (position == getItemCount() - listDataModel.getPageSize()/3){
            if (!listDataModel.isEndOfListReached()) {
                LocalBroadcastManager.getInstance(holder.itemView.getContext()).
                        sendBroadcast(new Intent(ProductsListLoader.FETCH_NEW_PAGE_ACTION));
            }
        }
    }

    @Override
    public int getItemViewType(int position) {
        return position == (getItemCount() - 1) ? FOOTER : REGULAR;
    }

    @Override
    public int getItemCount() {
        return listDataModel == null ? 0 : listDataModel.products.size() + 1;
    }

    private Product getItem(int pos){
        return listDataModel.products.get(pos);
    }

    public void setListDataModel(ListDataModel listDataModel) {
        this.listDataModel = listDataModel;
        notifyDataSetChanged();
//        notifyItemRangeRemoved(0,getItemCount());
    }

    static class ViewHolderFooter extends RecyclerView.ViewHolder{

        private ProgressBar progressBar;
        private TextView textView;

        ViewHolderFooter(View itemView) {
            super(itemView);
            progressBar = (ProgressBar) itemView.findViewById(R.id.progress);
            textView= (TextView) itemView.findViewById(R.id.text);
        }

        void decorateLastItem(){
            progressBar.setVisibility(View.GONE);
            textView.setText(R.string.end_of_list_label);
        }
    }
    static class ViewHolderRegular extends RecyclerView.ViewHolder{

        final TextView name;
        final TextView price;
        final ImageView photo;

        ViewHolderRegular(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name);
            price = (TextView) itemView.findViewById(R.id.price);
            photo = (ImageView) itemView.findViewById(R.id.photo);
        }

        void setModel(Product p){
            name.setText(p.getName());
            price.setText(String.format(Locale.getDefault(), "%.2f$",Double.parseDouble(p.getPrice())));
            Picasso.with(itemView.getContext()).load(RestApi.BASE_IMAGE_URL + p.getThumbnailPath())
                    .into(photo);
        }
    }
}
