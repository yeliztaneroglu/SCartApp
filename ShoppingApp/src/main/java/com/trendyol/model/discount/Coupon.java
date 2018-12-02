package com.trendyol.model.discount;

import com.trendyol.util.DiscountType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Coupon extends ShoppingCartDiscount {
    private double minPurchaseAmount;

    public Coupon(double minPurchaseAmount, double discount, DiscountType discountType) {
        super(discount, discountType);
        this.minPurchaseAmount = minPurchaseAmount;
    }
}
