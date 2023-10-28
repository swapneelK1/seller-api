package com.marketplaces.sellerapi.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SellerPageableResponse {

    private PageMeta meta;
    private List<SellerResponse> data;
}
