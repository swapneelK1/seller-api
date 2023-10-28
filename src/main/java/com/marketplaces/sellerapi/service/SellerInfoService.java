package com.marketplaces.sellerapi.service;

import com.marketplaces.sellerapi.entity.Seller;
import com.marketplaces.sellerapi.entity.SellerInfo;
import com.marketplaces.sellerapi.model.*;
import com.marketplaces.sellerapi.repository.SellerInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class SellerInfoService {

    @Autowired
    private SellerInfoRepository sellerInfoRepository;

    public SellerPageableResponse getSellers(SellerFilter sellerFilter, PageInput pageInput, SellerSortBy sellerSortBy) {
        Page<SellerInfo> res = null;
        if (Objects.nonNull(sellerFilter)) {
            if (StringUtils.hasText(sellerFilter.getSearchByName())) {
                res = sellerInfoRepository.findByNameLike(sellerFilter.getSearchByName(), getPageRequest(pageInput, sellerSortBy));
            } else if (sellerFilter.getProducerIds() != null && !sellerFilter.getProducerIds().isEmpty()) {
                res = sellerInfoRepository.findBySellers_Producer_IdIn(sellerFilter.getProducerIds(), getPageRequest(pageInput, sellerSortBy));
            } else if (sellerFilter.getMarketplaceIds() != null && !sellerFilter.getMarketplaceIds().isEmpty()) {
                res = sellerInfoRepository.findByMarketPlace_IdIn(sellerFilter.getMarketplaceIds(), getPageRequest(pageInput, sellerSortBy));
            }
        } else {
            res = sellerInfoRepository.findAll(getPageRequest(pageInput, sellerSortBy));
        }

        List<SellerResponse> response = new ArrayList<>();
        for(SellerInfo resp: res) {
            SellerResponse sellerResp = new SellerResponse();
            sellerResp.setSellerName(resp.getName());
            sellerResp.setExternalId(resp.getExternalId());
            sellerResp.setMarketplaceId(resp.getMarketPlace().getId());

            for(Seller sellerRes: resp.getSellers()) {
                ProducerSellerStateResponse proSellStateResp = new ProducerSellerStateResponse();
                proSellStateResp.setSellerId(sellerRes.getId());
                proSellStateResp.setSellerState(sellerRes.getState());
                proSellStateResp.setProducerId(sellerRes.getProducer().getId());
                proSellStateResp.setProducerName(sellerRes.getProducer().getName());

                sellerResp.getProducerSellerStates().add(proSellStateResp);
            }
            response.add(sellerResp);
        }

        SellerPageableResponse sellerPageableResponse = new SellerPageableResponse();
        sellerPageableResponse.setData(response);
        PageMeta meta = new PageMeta();
        meta.setPage(res.getNumber());
        meta.setTotalPage(res.getTotalPages());
        meta.setSize(res.getSize());
        sellerPageableResponse.setMeta(meta);
        return sellerPageableResponse;

    }

    private PageRequest getPageRequest(PageInput pageInput, SellerSortBy sellerSortBy) {
        if(Objects.nonNull(sellerSortBy)) {
            String[] fieldWithSortDirection = sellerSortBy.getFieldWithSortDirection().split("#");
            return PageRequest.of(pageInput.getPage(), pageInput.getSize()).withSort(Sort.by(Sort.Direction.valueOf(fieldWithSortDirection[1]), fieldWithSortDirection[0]));
        } else {
            return PageRequest.of(pageInput.getPage(), pageInput.getSize());
        }
    }
}
