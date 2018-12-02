package com.trendyol.model.discount;

import com.trendyol.model.Category;
import com.trendyol.util.DiscountType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Campaign extends ShoppingCartDiscount {
    private Category category;
    private int quantity;

    public Campaign(Category category, double discount, int quantity, DiscountType discountType){
        super(discount, discountType);
        this.category = category;
        this.quantity = quantity;
    }
}
