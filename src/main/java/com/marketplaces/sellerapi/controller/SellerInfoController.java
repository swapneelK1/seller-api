package com.marketplaces.sellerapi.controller;

import com.marketplaces.sellerapi.model.PageInput;
import com.marketplaces.sellerapi.model.SellerFilter;
import com.marketplaces.sellerapi.model.SellerPageableResponse;
import com.marketplaces.sellerapi.model.SellerSortBy;
import com.marketplaces.sellerapi.service.SellerInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
public class SellerInfoController {

    @Autowired
    private SellerInfoService sellerInfoService;

    @QueryMapping
    public SellerPageableResponse sellers(@Argument SellerFilter filter, @Argument PageInput page, @Argument SellerSortBy sortBy) {
        SellerPageableResponse response = sellerInfoService.getSellers(filter, page, sortBy);
        return response;
    }
}
