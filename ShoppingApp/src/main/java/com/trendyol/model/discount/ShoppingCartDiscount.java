package com.trendyol.model.discount;


import com.trendyol.util.DiscountType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class ShoppingCartDiscount {
    private double discount;
    private DiscountType discountType;

    public ShoppingCartDiscount(double discount, DiscountType discountType){
        this.discount = discount;
        this.discountType = discountType;
    }
}
