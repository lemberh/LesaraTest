package com.example.roman.lesaratest;

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
    private ProgressBar mProgressBar;

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
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if (mAdapter.getItemViewType(position) == ProductsAdapter.REGULAR) {
                    return 1;
                } else {
                    return 2;
                }
            }
        });
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mAdapter);

        mProgressBar = (ProgressBar) findViewById(R.id.progress);
    }

    @Override
    protected void onStart() {
        super.onStart();
        getSupportLoaderManager().initLoader(0, null, loaderCallbacks);
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
