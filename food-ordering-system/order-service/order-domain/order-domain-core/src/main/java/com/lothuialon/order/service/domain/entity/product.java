package com.lothuialon.order.service.domain.entity;

import java.util.List;

import com.lothuialon.domain.entity.BaseEntity;
import com.lothuialon.domain.valueObject.money;
import com.lothuialon.domain.valueObject.productId;

public class product extends BaseEntity<productId>{
    private String name;
    private money price;
    private List<String> photos;


    public product(String name, money price, List<String> photos, productId productId) {
        this.name = name;
        this.price = price;
        this.photos = photos;
        super.setId(productId);
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public money getPrice() {
        return this.price;
    }

}
