package com.example.roman.lesaratest.Model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TrendProducts {

    @SerializedName("products")
    @Expose
    private List<Product> products = new LinkedList<>();
    @SerializedName("number_products")
    @Expose
    private Integer numberProducts;
    @SerializedName("number_pages")
    @Expose
    private Integer numberPages;
    @SerializedName("current_page")
    @Expose
    private Integer currentPage;

    /**
     * @return The products
     */
    public List<Product> getProducts() {
        return products;
    }

    /**
     * @param products The products
     */
    public void setProducts(List<Product> products) {
        this.products = products;
    }

    /**
     * @return The numberProducts
     */
    public Integer getNumberProducts() {
        return numberProducts;
    }

    /**
     * @param numberProducts The number_products
     */
    public void setNumberProducts(Integer numberProducts) {
        this.numberProducts = numberProducts;
    }

    /**
     * @return The numberPages
     */
    public Integer getNumberPages() {
        return numberPages;
    }

    /**
     * @param numberPages The number_pages
     */
    public void setNumberPages(Integer numberPages) {
        this.numberPages = numberPages;
    }

    /**
     * @return The currentPage
     */
    public Integer getCurrentPage() {
        return currentPage;
    }

    /**
     * @param currentPage The current_page
     */
    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

}