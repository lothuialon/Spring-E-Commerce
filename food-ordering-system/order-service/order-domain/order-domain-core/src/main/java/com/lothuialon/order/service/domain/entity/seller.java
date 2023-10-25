package com.lothuialon.order.service.domain.entity;

import java.util.List;

import com.lothuialon.domain.entity.AggregateRoot;
import com.lothuialon.domain.valueObject.sellerId;

public class seller extends AggregateRoot<sellerId>{
    private final List<product> products;
    private final boolean active;
    private final int rating;

    private seller(Builder builder) {
        super.setId(builder.sellerId);
        products = builder.products;
        active = builder.active;
        rating = builder.rating;
    }


    public List<product> getProducts() {
        return this.products;
    }

    public boolean isActive() {
        return this.active;
    }


    public int getRating() {
        return this.rating;
    }


    public static final class Builder {
        private sellerId sellerId;
        private List<product> products;
        private boolean active;
        private int rating;

        private Builder() {
        }

        public static Builder builder() {
            return new Builder();
        }

        public Builder id(sellerId val) {
            sellerId = val;
            return this;
        }

        public Builder products(List<product> val) {
            products = val;
            return this;
        }

        public Builder active(boolean val) {
            active = val;
            return this;
        }

        public Builder rating(int val) {
            rating = val;
            return this;
        }

        public seller build() {
            return new seller(this);
        }
    }
}
