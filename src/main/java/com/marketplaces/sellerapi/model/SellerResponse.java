package com.marketplaces.sellerapi.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SellerResponse {

    private String sellerName;
    private String externalId;
    private String marketplaceId;

    private List<ProducerSellerStateResponse> producerSellerStates = new ArrayList<>();
}
