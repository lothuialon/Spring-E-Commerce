package com.lothuialon.order.service.domain.service.Implementations;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lothuialon.domain.exception.domainException;
import com.lothuialon.order.service.domain.entity.order;
import com.lothuialon.order.service.domain.entity.product;
import com.lothuialon.order.service.domain.entity.seller;
import com.lothuialon.order.service.domain.event.orderCancelledEvent;
import com.lothuialon.order.service.domain.event.orderCreatedEvent;
import com.lothuialon.order.service.domain.event.orderPaidEvent;
import com.lothuialon.order.service.domain.service.orderDomainService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class orderDomainServiceImp implements orderDomainService{

    @Override
    public orderCreatedEvent initiateOrder(order order, seller seller) {
        validateSeller(seller);
        setOrderAndProductInformation(order, seller);
        order.validateOrder();
        order.init();

        log.info("Order: {} is initiated", order.getId().getValue());

        return new orderCreatedEvent(order, ZonedDateTime.now(ZoneId.of("UTC")));
    }


    @Override
    public orderPaidEvent payOrder(order order) {
        order.pay();
        log.info("Order number " + order.getId().getValue() + " is paid");
        return new orderPaidEvent(order, ZonedDateTime.now(ZoneId.of("UTC")));
    }

    @Override
    public orderCancelledEvent cancelOrderPayment(order order, List<String> failureMessages) {
        order.initCancel(failureMessages);
        log.info("Order number " + order.getId() + " is cancelling");
        return new orderCancelledEvent(order, ZonedDateTime.now(ZoneId.of("UTC")));
    }

    @Override
    public void approveOrder(order order) {
        order.approve();
        log.info("Order number " + order.getId().getValue() + " is approved by seller");
    }

    @Override
    public void cancelOrder(order order, List<String> failureMessages) {
        order.cancel(failureMessages);
        log.info("Order number " + order.getId().getValue() + " is approved by seller");
    }
    private void validateSeller(seller seller){
        if(!seller.isActive()){
            throw new domainException("Seller is not actively selling");
        }

    }
    
    private void setOrderAndProductInformation(order order, seller seller) {
        //check if price passed from client and from the product objects are the same
        
        order.getItems().forEach(item -> seller.getProducts().forEach(sellerItem -> {
            product product = item.getProduct();
            if(product.equals(sellerItem)){
                product.updateWithConfirmedSpecs(sellerItem.getName(), sellerItem.getPrice());
            }
        }));
    }
    /* 
    Map<Long, product> productMap = new HashMap<>();
    
    // Populate the product map for the seller's products
    for (product sellerItem : seller.getProducts()) {
        productMap.put((sellerItem.getId()), sellerItem);
    }

    // Update order items with confirmed specs
    for (OrderItem item : order.getItems()) {
        product product = item.getProduct();
        product sellerItem = productMap.get(product.getId());
        
        if (sellerItem != null) {
            product.updateWithConfirmedSpecs(sellerItem.getName(), sellerItem.getPrice());
        }
    }
    */
}
