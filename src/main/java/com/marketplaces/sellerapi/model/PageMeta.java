package com.marketplaces.sellerapi.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.criteria.CriteriaBuilder;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PageMeta {
    private Integer size;
    private Integer page;
    private Integer totalPage;
}
