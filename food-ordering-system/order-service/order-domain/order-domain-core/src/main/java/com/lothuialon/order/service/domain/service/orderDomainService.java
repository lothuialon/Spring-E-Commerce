package com.lothuialon.order.service.domain.service;

import java.util.List;

import com.lothuialon.order.service.domain.entity.order;
import com.lothuialon.order.service.domain.entity.seller;
import com.lothuialon.order.service.domain.event.orderCancelledEvent;
import com.lothuialon.order.service.domain.event.orderCreatedEvent;
import com.lothuialon.order.service.domain.event.orderPaidEvent;

public interface orderDomainService {
    
    orderCreatedEvent initiateOrder(order order, seller seller);
    orderPaidEvent payOrder(order order);
    orderCancelledEvent cancelOrderPayment(order order, List<String> failureMessages);

    void approveOrder(order order);
    void cancelOrder(order order, List<String> failureMessages);

}
