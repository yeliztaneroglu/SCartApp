package com.trendyol;

import com.trendyol.model.Category;
import com.trendyol.model.Product;
import com.trendyol.model.discount.Campaign;
import com.trendyol.model.discount.Coupon;
import com.trendyol.model.event.ShoppingCart;
import com.trendyol.util.DiscountType;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Category food = new Category("food");
        Product apple = new Product("Apple",100.0, food);
        Product almond = new Product("Almonds",150.0,food);

        ShoppingCart cart = new ShoppingCart();
        cart.addItem(apple,3);
        cart.addItem(almond,1);

        Campaign campaign1 = new Campaign(food,20.0,3, DiscountType.Rate);
        Campaign campaign2 = new Campaign(food, 50.0,5,DiscountType.Rate);
        Campaign campaign3 = new Campaign(food,5.0,5,DiscountType.Amount);

        List<Double> discounts = new ArrayList();
        double discount1 = cart.CampaignDiscount(campaign1);
        discounts.add(discount1);
        double discount2 = cart.CampaignDiscount(campaign2);
        discounts.add(discount2);
        double discount3 = cart.CampaignDiscount(campaign3);
        discounts.add(discount3);

        cart.applyDiscounts(discounts);

        Coupon coupon = new Coupon(100,10,DiscountType.Rate);
        cart.applyCoupon(coupon);

        double totalAmount = cart.getTotalAmountAfterDiscounts();
        double couponDiscount = cart.getCouponDiscount();
        double campaignDiscount = cart.getCampaignDiscount();
        double deliveryCost = cart.getDeliveryCost();

        cart.print();
    }
}