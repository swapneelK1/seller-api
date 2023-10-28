package com.marketplaces.sellerapi.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "producers")
public class Producer {

    @Id
    @GeneratedValue
    private UUID id;
    private String name;
    private Timestamp createdAt;

}
