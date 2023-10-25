package com.lothuialon.order.service.domain.event;

import java.time.ZonedDateTime;
import java.util.Date;

import com.lothuialon.domain.event.domainEvent;
import com.lothuialon.order.service.domain.entity.order;

public abstract class orderEvent implements domainEvent<order>{

    private final order order;
    private final ZonedDateTime createDate;

    public orderEvent(order order, ZonedDateTime createDate) {
        this.order = order;
        this.createDate = createDate;
    }

    public order getOrder() {
        return this.order;
    }


    public ZonedDateTime getCreateDate() {
        return this.createDate;
    }
    
}
