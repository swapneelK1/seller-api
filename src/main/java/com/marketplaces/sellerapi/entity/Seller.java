package com.marketplaces.sellerapi.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "sellers")
public class Seller {
    @Id
    @GeneratedValue
    private UUID id;
    private String state;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "producer_id", referencedColumnName = "id")
    private Producer producer;

}
