package com.marketplaces.sellerapi.repository;

import com.marketplaces.sellerapi.entity.SellerInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface SellerInfoRepository extends JpaRepository<SellerInfo, UUID> {

    public Page<SellerInfo> findByNameLike(String name, PageRequest pageRequest);
    public Page<SellerInfo> findBySellers_Producer_IdIn(List<UUID> producerIds, PageRequest pageRequest);

    public Page<SellerInfo> findByMarketPlace_IdIn(List<String> marketplaceIds, PageRequest pageRequest);
}
