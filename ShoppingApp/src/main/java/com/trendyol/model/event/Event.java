package com.trendyol.model.event;

public interface Event {

    double getTotalAmountAfterDiscounts();

    double getCouponDiscount();

    double getCampaignDiscount();

    double getDeliveryCost();

}
