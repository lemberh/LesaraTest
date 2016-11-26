package com.example.roman.lesaratest.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RootObject {

    @SerializedName("trend_products")
    @Expose
    private TrendProducts trendProducts;

    /**
     *
     * @return
     * The trendProducts
     */
    public TrendProducts getTrendProducts() {
        return trendProducts;
    }

    /**
     *
     * @param trendProducts
     * The trend_products
     */
    public void setTrendProducts(TrendProducts trendProducts) {
        this.trendProducts = trendProducts;
    }

}