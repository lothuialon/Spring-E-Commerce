package com.lothuialon.order.service.domain.valueObject;

import java.util.UUID;
import java.util.Objects;

public class address {
    private final UUID id;
    private final String city;
    private final String street;
    private final String postalCode;


    public address(UUID id, String city, String street, String postalCode) {
        this.id = id;
        this.city = city;
        this.street = street;
        this.postalCode = postalCode;
    }

    public UUID getId() {
        return this.id;
    }


    public String getCity() {
        return this.city;
    }


    public String getStreet() {
        return this.street;
    }


    public String getPostalCode() {
        return this.postalCode;
    }


    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof address)) {
            return false;
        }
        address address = (address) o;
        return Objects.equals(id, address.id) && Objects.equals(city, address.city) && Objects.equals(street, address.street) && Objects.equals(postalCode, address.postalCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, city, street, postalCode);
    }

    

}
