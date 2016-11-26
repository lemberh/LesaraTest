package com.example.roman.lesaratest.Model;

import java.util.List;

/**
 * Created by roman on 26/11/16.
 */

public class ListDataModel {
    public final List<Product> products;

    private int pageSize;
    private boolean endOfListReached;

    public ListDataModel(List<Product> head, List<Product> tail) {
        products = head;
        products.addAll(tail);
    }
    public ListDataModel(List<Product> products) {
        this.products = products;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public boolean isEndOfListReached() {
        return endOfListReached;
    }

    public void setEndOfListReached(boolean endOfListReached) {
        this.endOfListReached = endOfListReached;
    }
}
