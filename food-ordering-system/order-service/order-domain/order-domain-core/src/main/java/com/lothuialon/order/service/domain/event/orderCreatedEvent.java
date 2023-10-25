package com.lothuialon.order.service.domain.event;

import java.time.ZonedDateTime;
import java.util.Date;

import com.lothuialon.order.service.domain.entity.order;

public class orderCreatedEvent extends orderEvent {

    public orderCreatedEvent(order order, ZonedDateTime zonedDateTime) {
        super(order, zonedDateTime);

    }


}
