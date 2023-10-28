package com.marketplaces.sellerapi.controller;

import com.marketplaces.sellerapi.model.*;
import com.marketplaces.sellerapi.service.SellerInfoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SellerInfoControllerTest {

    @Autowired
    private SellerInfoController sellerInfoController;

    @MockBean
    private SellerInfoService sellerInfoService;

    @Test
    public void testSellersEndpointWithFilter() throws Exception {
        // Given
        SellerFilter filter = new SellerFilter();
        filter.setSearchByName("TestName");
        PageInput page = new PageInput(0, 10);
        SellerSortBy sortBy = SellerSortBy.NAME_DESC;

        // mock
        when(sellerInfoService.getSellers(any(), any(), any())).thenReturn(getMockedSellerPageableResponse());

        SellerPageableResponse response = sellerInfoController.sellers(filter, page, sortBy);
        verify(sellerInfoService).getSellers(any(), any(), any());
        assertEquals(getMockedSellerPageableResponse().getData().get(0).getSellerName(), response.getData().get(0).getSellerName());
        assertEquals(getMockedSellerPageableResponse().getData().get(0).getExternalId(), response.getData().get(0).getExternalId());
        assertEquals(getMockedSellerPageableResponse().getData().get(0).getMarketplaceId(), response.getData().get(0).getMarketplaceId());

    }

    private SellerPageableResponse getMockedSellerPageableResponse() {
        SellerPageableResponse sellerPageableResponse = new SellerPageableResponse();

        PageMeta pageMeta = new PageMeta();
        pageMeta.setSize(10);
        pageMeta.setPage(0);
        pageMeta.setTotalPage(2);
        sellerPageableResponse.setMeta(pageMeta);

        List<SellerResponse> sellerResponses = new ArrayList<>();
        SellerResponse sellerResponse = new SellerResponse();
        sellerResponse.setSellerName("sellerName");
        sellerResponse.setExternalId("externalId");
        sellerResponse.setMarketplaceId("marketplaceId");

        List<ProducerSellerStateResponse> producerSellerStates = new ArrayList<>();
        ProducerSellerStateResponse producerSellerState = new ProducerSellerStateResponse();
        producerSellerState.setProducerId(UUID.randomUUID());
        producerSellerState.setProducerName("producerName");
        producerSellerState.setSellerState("sellerState");
        producerSellerState.setSellerId(UUID.randomUUID());
        producerSellerStates.add(producerSellerState);
        sellerResponse.setProducerSellerStates(producerSellerStates);
        sellerResponses.add(sellerResponse);

        sellerPageableResponse.setData(sellerResponses);

        return sellerPageableResponse;

    }
}
