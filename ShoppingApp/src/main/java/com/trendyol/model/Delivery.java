package com.trendyol.model;

import com.trendyol.model.event.ShoppingCart;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class Delivery {

    private double costPerDelivery;
    private double costPerProduct;
    private static final double fixedCost = 2.9;

    public double calculateFor(ShoppingCart cart){
        long numberOfDeliveries;
        int numberOfProducts;
        double delivery;
        List<String> categories = new ArrayList<>();
        List<String> products = new ArrayList<>();
        cart.getItemList().forEach((product, value) -> {
            if(!categories.contains(product.getCategory().getTitle())){
                categories.add(product.getCategory().getTitle());
            } if(!products.contains(product.getTitle())){
                products.add(product.getTitle());
            }
        });
        numberOfDeliveries = categories.size();
        numberOfProducts = products.size();
        delivery = (costPerDelivery * numberOfDeliveries) + (costPerProduct * numberOfProducts) + fixedCost;
        return delivery;
    }
}
