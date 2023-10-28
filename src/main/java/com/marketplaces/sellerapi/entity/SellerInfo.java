package com.marketplaces.sellerapi.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "seller_infos")
public class SellerInfo {
    @Id
    @GeneratedValue
    private UUID id;
    private String name;
    private String url;
    private String country;
    @Column(name = "external_id")
    private String externalId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "marketplace_id", referencedColumnName = "id")
    private Marketplace marketPlace;

    @OneToMany(targetEntity = Seller.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "seller_info_id", referencedColumnName = "id")
    private List<Seller> sellers;

}
