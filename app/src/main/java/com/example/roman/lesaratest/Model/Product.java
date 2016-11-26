package com.example.roman.lesaratest.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Product {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("msrp")
    @Expose
    private String msrp;
    @SerializedName("sku")
    @Expose
    private String sku;
    @SerializedName("enabled_from")
    @Expose
    private String enabledFrom;
    @SerializedName("show_msrp")
    @Expose
    private String showMsrp;
    @SerializedName("show_msrp_index")
    @Expose
    private Integer showMsrpIndex;
    @SerializedName("discount")
    @Expose
    private Integer discount;
    @SerializedName("thumbnail_path")
    @Expose
    private String thumbnailPath;

    /**
     *
     * @return
     * The id
     */
    public String getId() {
        return id;
    }

    /**
     *
     * @param id
     * The id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     *
     * @return
     * The name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     * The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     * The price
     */
    public String getPrice() {
        return price;
    }

    /**
     *
     * @param price
     * The price
     */
    public void setPrice(String price) {
        this.price = price;
    }

    /**
     *
     * @return
     * The msrp
     */
    public String getMsrp() {
        return msrp;
    }

    /**
     *
     * @param msrp
     * The msrp
     */
    public void setMsrp(String msrp) {
        this.msrp = msrp;
    }

    /**
     *
     * @return
     * The sku
     */
    public String getSku() {
        return sku;
    }

    /**
     *
     * @param sku
     * The sku
     */
    public void setSku(String sku) {
        this.sku = sku;
    }

    /**
     *
     * @return
     * The enabledFrom
     */
    public String getEnabledFrom() {
        return enabledFrom;
    }

    /**
     *
     * @param enabledFrom
     * The enabled_from
     */
    public void setEnabledFrom(String enabledFrom) {
        this.enabledFrom = enabledFrom;
    }

    /**
     *
     * @return
     * The showMsrp
     */
    public String getShowMsrp() {
        return showMsrp;
    }

    /**
     *
     * @param showMsrp
     * The show_msrp
     */
    public void setShowMsrp(String showMsrp) {
        this.showMsrp = showMsrp;
    }

    /**
     *
     * @return
     * The showMsrpIndex
     */
    public Integer getShowMsrpIndex() {
        return showMsrpIndex;
    }

    /**
     *
     * @param showMsrpIndex
     * The show_msrp_index
     */
    public void setShowMsrpIndex(Integer showMsrpIndex) {
        this.showMsrpIndex = showMsrpIndex;
    }

    /**
     *
     * @return
     * The discount
     */
    public Integer getDiscount() {
        return discount;
    }

    /**
     *
     * @param discount
     * The discount
     */
    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    /**
     *
     * @return
     * The thumbnailPath
     */
    public String getThumbnailPath() {
        return thumbnailPath;
    }

    /**
     *
     * @param thumbnailPath
     * The thumbnail_path
     */
    public void setThumbnailPath(String thumbnailPath) {
        this.thumbnailPath = thumbnailPath;
    }

}