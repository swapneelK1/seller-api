package com.marketplaces.sellerapi.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProducerSellerStateResponse {

    private UUID producerId;
    private String producerName;
    private String sellerState;
    private UUID sellerId;
}
