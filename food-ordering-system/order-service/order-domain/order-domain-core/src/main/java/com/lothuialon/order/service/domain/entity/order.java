package com.lothuialon.order.service.domain.entity;

import java.util.List;
import java.util.UUID;

import com.lothuialon.domain.entity.AggregateRoot;
import com.lothuialon.domain.valueObject.customerId;
import com.lothuialon.domain.valueObject.money;
import com.lothuialon.domain.valueObject.orderId;
import com.lothuialon.domain.valueObject.orderStatus;
import com.lothuialon.domain.valueObject.sellerId;
import com.lothuialon.order.service.domain.exception.orderDomainException;
import com.lothuialon.order.service.domain.valueObject.address;
import com.lothuialon.order.service.domain.valueObject.orderItemId;
import com.lothuialon.order.service.domain.valueObject.trackingId;

public class order extends AggregateRoot<orderId>{
 
    private final customerId customerId;
    private final List<sellerId> sellerIds;
    private final address deliveryAddress;
    private final money price;
    private final List<orderItem> items;

    //assigned later
    private trackingId trackingId;
    private orderStatus orderStatus;
    private List<String> failureMessages;


    public void init(){
        //initialize the order, then initialize the order items
        setId(new orderId(UUID.randomUUID()));
        trackingId = new trackingId(UUID.randomUUID());
        orderStatus = orderStatus.PENDING; 

        initOrderItems();
    }

    private void initOrderItems(){
        //loop over item list
        for (int i = 0; i < items.size(); i++) {
            items.get(i).initOrderItem(super.getId(), new orderItemId(((long)i)));
        }
    };

    public void validateOrder(){
        validateInit();
        validateTotalPrice();
        validateItems();
    }

    //compare price
    private void validateItems() {
        money orderItemsTotal = items.stream().map(item -> {
            validateItemPrice(item);
            return item.getItemTotal();
        }).reduce(money.ZERO, money::add);

        if(!price.equals(orderItemsTotal)){
            throw new orderDomainException("Validation of the items is unsuccessful, total price of order: "
            + price.getAmount() + " is not equal to total price of items: " + orderItemsTotal);
        }
    }

    private void validateItemPrice(orderItem item) {
        if(!item.isPriceValid()){
            throw new orderDomainException("Validation of the items is unsuccessful, total price of item: "
            + item.getPrice().getAmount() + " is not valid for assigned product: " + item.getProduct().getId().getValue());
        }
    }

    private void validateTotalPrice() {
        if(price != null || price.isGreaterThanZero()){
            throw new orderDomainException("Validation of total price is unsuccesful");
        }
    }

    private void validateInit() {
        //check if order is initialized
        if(orderStatus != null || getId() != null){
            throw new orderDomainException("Order can't be initialized");
        }
    }

    //----------------------------------------------
    public void pay(){
        if(orderStatus != orderStatus.PENDING){
            throw new orderDomainException("Order status is unvalid");
        }
        //logic
        orderStatus = orderStatus.APPROVED;
    }

    public void approve(){
        if(orderStatus != orderStatus.APPROVED){
            throw new orderDomainException("Order status is unvalid");
        }
        //logic
        orderStatus = orderStatus.APPROVED;
    }

    public void initCancel(List<String> failureMessages){
        if(orderStatus != orderStatus.CANCELLING){
            throw new orderDomainException("Order status is unvalid");
        }
        //logic
        orderStatus = orderStatus.CANCELLING;
        updateFailureMessages(failureMessages);
    }

    private void updateFailureMessages(List<String> failureMessages) {
        if(this.failureMessages != null && failureMessages != null){
            this.failureMessages.addAll(failureMessages);
        }
        if(this.failureMessages == null ){
            this.failureMessages = failureMessages;
        }
    }

    //both pending and cancelling can transition into cancelled state
    public void cancel(List<String> failureMessages){
        if(!(orderStatus != orderStatus.CANCELLING || orderStatus == orderStatus.PENDING)){
            throw new orderDomainException("Order status is unvalid");
        }
        //logic
        orderStatus = orderStatus.CANCELLED;
    }



    private order(Builder builder) {
        super.setId(builder.orderId);
        customerId = builder.customerId;
        sellerIds = builder.sellerIds;
        deliveryAddress = builder.deliveryAddress;
        price = builder.price;
        items = builder.items;
        trackingId = builder.trackingId;
        orderStatus = builder.orderStatus;
        failureMessages = builder.failureMessages;
    }

    public customerId getCustomerId() {
        return this.customerId;
    }


    public List<sellerId> getSellerIds() {
        return this.sellerIds;
    }


    public address getDeliveryAddress() {
        return this.deliveryAddress;
    }


    public money getPrice() {
        return this.price;
    }


    public List<orderItem> getItems() {
        return this.items;
    }


    public trackingId getTrackingId() {
        return this.trackingId;
    }



    public orderStatus getOrderStatus() {
        return this.orderStatus;
    }


    public List<String> getFailureMessages() {
        return this.failureMessages;
    }

    public static final class Builder {
        private orderId orderId;
        private customerId customerId;
        private List<sellerId> sellerIds;
        private address deliveryAddress;
        private money price;
        private List<orderItem> items;
        private trackingId trackingId;
        private orderStatus orderStatus;
        private List<String> failureMessages;

        private Builder() {
        }

        public static Builder builder() {
            return new Builder();
        }

        public Builder id(orderId val) {
            orderId = val;
            return this;
        }

        public Builder customerId(customerId val) {
            customerId = val;
            return this;
        }

        public Builder sellerIds(List<sellerId> val) {
            sellerIds = val;
            return this;
        }

        public Builder deliveryAddress(address val) {
            deliveryAddress = val;
            return this;
        }

        public Builder price(money val) {
            price = val;
            return this;
        }

        public Builder items(List<orderItem> val) {
            items = val;
            return this;
        }

        public Builder trackingId(trackingId val) {
            trackingId = val;
            return this;
        }

        public Builder orderStatus(orderStatus val) {
            orderStatus = val;
            return this;
        }

        public Builder failureMessages(List<String> val) {
            failureMessages = val;
            return this;
        }

        public order build() {
            return new order(this);
        }
    }

}
