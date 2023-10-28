package com.marketplaces.sellerapi.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SellerFilter {

    private String searchByName;
    private List<UUID> producerIds;
    private List<String> marketplaceIds;
}
