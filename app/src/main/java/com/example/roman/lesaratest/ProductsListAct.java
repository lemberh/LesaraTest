package com.example.roman.lesaratest;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;

import com.example.roman.lesaratest.Model.ListDataModel;
import com.example.roman.lesaratest.loaders.ProductsListLoader;

public class ProductsListAct extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private ProductsAdapter mAdapter;
    private GridLayoutManager mLayoutManager;
    private ProgressBar mProgressBar;
    private int spansCount = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        mAdapter = new ProductsAdapter();
        mLayoutManager = new GridLayoutManager(this, spansCount);
        mLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if (mAdapter.getItemViewType(position) == ProductsAdapter.REGULAR) {
                    return 1;
                } else {
                    return spansCount;
                }
            }
        });
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        mProgressBar = (ProgressBar) findViewById(R.id.progress);
    }

    @Override
    protected void onStart() {
        super.onStart();
        getSupportLoaderManager().initLoader(0, null, loaderCallbacks);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        // Checks the orientation of the screen
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            spansCount = 3;
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
            spansCount = 2;
        }
        mLayoutManager.setSpanCount(spansCount);
    }

    private LoaderManager.LoaderCallbacks<ListDataModel> loaderCallbacks = new LoaderManager.LoaderCallbacks<ListDataModel>() {
        @Override
        public Loader<ListDataModel> onCreateLoader(int id, Bundle args) {
            return new ProductsListLoader(ProductsListAct.this);
        }

        @Override
        public void onLoadFinished(Loader<ListDataModel> loader, ListDataModel data) {
            mAdapter.setListDataModel(data);
            mProgressBar.setVisibility(View.GONE);
        }

        @Override
        public void onLoaderReset(Loader<ListDataModel> loader) {
        }
    };
}
