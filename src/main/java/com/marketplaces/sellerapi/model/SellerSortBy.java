package com.marketplaces.sellerapi.model;

public enum SellerSortBy {

    SELLER_INFO_EXTERNAL_ID_ASC("externalId#ASC"),
    SELLER_INFO_EXTERNAL_ID_DESC("externalId#DESC"),
    NAME_ASC("name#ASC"),
    NAME_DESC("name#DESC"),
    MARKETPLACE_ID_ASC("marketPlace.id#ASC"),
    MARKETPLACE_ID_DESC("marketPlace.id#DESC");

    private String fieldWithSortDirection;

    private SellerSortBy(String fieldWithSortDirection) {
        this.fieldWithSortDirection = fieldWithSortDirection;
    }

    public String getFieldWithSortDirection() {
        return this.fieldWithSortDirection;
    }

}
