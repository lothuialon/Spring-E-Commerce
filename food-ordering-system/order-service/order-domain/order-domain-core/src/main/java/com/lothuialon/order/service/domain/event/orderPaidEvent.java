package com.lothuialon.order.service.domain.event;

import java.time.ZonedDateTime;
import java.util.Date;

import com.lothuialon.domain.event.domainEvent;
import com.lothuialon.order.service.domain.entity.order;

public class orderPaidEvent extends orderEvent{

    public orderPaidEvent(order order, ZonedDateTime createDate) {
        super(order, createDate);
    }

  
}
