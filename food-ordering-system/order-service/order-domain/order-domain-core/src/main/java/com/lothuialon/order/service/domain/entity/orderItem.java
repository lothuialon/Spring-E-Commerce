package com.lothuialon.order.service.domain.entity;

import com.lothuialon.domain.entity.BaseEntity;
import com.lothuialon.domain.valueObject.money;
import com.lothuialon.domain.valueObject.orderId;
import com.lothuialon.order.service.domain.valueObject.orderItemId;

import lombok.Builder;

@Builder
public class orderItem extends BaseEntity<orderItemId>{
    private orderId orderId;
    private final product product;
    private final int quantity;
    private final money price;
    private final money itemTotal;


    public orderItem(orderId orderId, product product, int quantity, money price, money itemTotal) {
        this.orderId = orderId;
        this.product = product;
        this.quantity = quantity;
        this.price = price;
        this.itemTotal = itemTotal;
    }


    public orderId getOrderId() {
        return this.orderId;
    }

    public void setOrderId(orderId orderId) {
        this.orderId = orderId;
    }

    public product getProduct() {
        return this.product;
    }


    public int getQuantity() {
        return this.quantity;
    }


    public money getPrice() {
        return this.price;
    }


    public money getItemTotal() {
        return this.itemTotal;
    }

    //builder

    public static Builder builder() {
        return new Builder();
    }

    private orderItem(Builder builder) {
        super.setId(builder.orderItemId);
        product = builder.product;
        quantity = builder.quantity;
        price = builder.price;
        itemTotal = builder.itemTotal;
    }

    public static final class Builder {
        private orderItemId orderItemId;
        private product product;
        private int quantity;
        private money price;
        private money itemTotal;

        private Builder() {
        }

        public Builder orderItemId(orderItemId val) {
            orderItemId = val;
            return this;
        }

        public Builder product(product val) {
            product = val;
            return this;
        }

        public Builder quantity(int val) {
            quantity = val;
            return this;
        }

        public Builder price(money val) {
            price = val;
            return this;
        }

        public Builder itemTotal(money val) {
            itemTotal = val;
            return this;
        }

        public orderItem build() {
            return new orderItem(this);
        }
    }
    //only accessed in this package
    void initOrderItem(orderId orderId, orderItemId orderItemId) {
        this.orderId = orderId;
        super.setId(orderItemId);
    }

    boolean isPriceValid(){
        return price.isGreaterThanZero() 
        && price.equals(product.getPrice()) 
        && price.multiply(quantity).equals(itemTotal);
    }

}
