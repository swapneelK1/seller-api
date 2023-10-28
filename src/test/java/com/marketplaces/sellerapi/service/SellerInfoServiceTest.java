package com.marketplaces.sellerapi.service;

import com.marketplaces.sellerapi.entity.Marketplace;
import com.marketplaces.sellerapi.entity.Producer;
import com.marketplaces.sellerapi.entity.Seller;
import com.marketplaces.sellerapi.entity.SellerInfo;
import com.marketplaces.sellerapi.model.PageInput;
import com.marketplaces.sellerapi.model.SellerFilter;
import com.marketplaces.sellerapi.model.SellerPageableResponse;
import com.marketplaces.sellerapi.model.SellerSortBy;
import com.marketplaces.sellerapi.repository.SellerInfoRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SellerInfoServiceTest {

    @Autowired
    private SellerInfoService sellerInfoService;

    @MockBean
    private SellerInfoRepository sellerInfoRepository;

    @Test
    public void testGetSellersWithSearchByNameWitSort() {
        //given
        SellerFilter filter = new SellerFilter();
        filter.setSearchByName("TestName");
        PageInput pageInput = new PageInput(0, 10);
        SellerSortBy sortBy = SellerSortBy.NAME_ASC;

        SellerInfo sellerInfo = new SellerInfo();

        // mocking
        Page<SellerInfo> mockPage = new PageImpl<>(Collections.singletonList(getMockedSellerInfo()));
        when(sellerInfoRepository.findByNameLike(any(), any())).thenReturn(mockPage);

        // execution
        SellerPageableResponse response = sellerInfoService.getSellers(filter, pageInput, sortBy);

        // assertions
        assertEquals(1, response.getData().size());
    }

    @Test
    public void testGetSellersWithProducerIds() {
        // given
        SellerFilter filter = new SellerFilter();
        filter.setProducerIds(Collections.singletonList(UUID.randomUUID()));
        PageInput pageInput = new PageInput(0, 10);
        SellerSortBy sortBy = null;

        // mocking
        Page<SellerInfo> mockPage = new PageImpl<>(Collections.singletonList(getMockedSellerInfo()));
        when(sellerInfoRepository.findBySellers_Producer_IdIn(any(), any())).thenReturn(mockPage);

        // execution
        SellerPageableResponse response = sellerInfoService.getSellers(filter, pageInput, sortBy);

        // assertions
        assertEquals(1, response.getData().size());
    }

    @Test
    public void testGetSellersWithMarketplaceIds() {
        // given
        SellerFilter filter = new SellerFilter();
        filter.setMarketplaceIds(Collections.singletonList("TestMarketplaceId"));
        PageInput pageInput = new PageInput(0, 10);
        SellerSortBy sortBy = SellerSortBy.MARKETPLACE_ID_ASC;

        // mocking
        Page<SellerInfo> mockPage = new PageImpl<>(Collections.singletonList(getMockedSellerInfo()));
        when(sellerInfoRepository.findByMarketPlace_IdIn(any(), any())).thenReturn(mockPage);

        // execution
        SellerPageableResponse response = sellerInfoService.getSellers(filter, pageInput, sortBy);

        // assertions
        assertEquals(1, response.getData().size());
    }

    @Test
    public void testGetSellersWithoutFilter() {
        // given
        SellerFilter filter = null;
        PageInput pageInput = new PageInput(0, 10);
        SellerSortBy sortBy = SellerSortBy.SELLER_INFO_EXTERNAL_ID_ASC;

        // mocking
        Page<SellerInfo> mockPage = new PageImpl<>(Collections.singletonList(getMockedSellerInfo()));
        when(sellerInfoRepository.findAll((Pageable) any())).thenReturn(mockPage);

        // execution
        SellerPageableResponse response = sellerInfoService.getSellers(filter, pageInput, sortBy);

        // assertions
        assertEquals(1, response.getData().size());
    }


    private SellerInfo getMockedSellerInfo() {
        SellerInfo sellerInfo = new SellerInfo();
        sellerInfo.setName("SellerInfo");
        sellerInfo.setUrl("http://xyz.com");
        sellerInfo.setCountry("Country");
        sellerInfo.setExternalId("123456");

        Marketplace marketplace = new Marketplace();
        marketplace.setId("MarketplaceID");
        marketplace.setDescription("Dummy Marketplace Description");

        Producer producer = new Producer();
        producer.setName("Producer");

        Seller seller = new Seller();
        seller.setState("State");
        seller.setProducer(producer);

        sellerInfo.setMarketPlace(marketplace);
        sellerInfo.setSellers(Collections.singletonList(seller));

        return sellerInfo;
    }
}
