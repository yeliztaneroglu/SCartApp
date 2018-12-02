package com.trendyol.model.event;

import com.trendyol.model.Category;
import com.trendyol.model.Delivery;
import com.trendyol.model.Product;
import com.trendyol.model.discount.Campaign;
import com.trendyol.model.discount.Coupon;
import com.trendyol.util.DiscountType;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

import static java.util.stream.Collectors.groupingBy;

@Getter
@Setter
public class ShoppingCart implements Event {
    private HashMap<Product, Integer> itemList;
    private double campaignDiscount;
    private double couponDiscount;

    public ShoppingCart() {
        itemList = new HashMap<>();
    }

    public double getTotalAmount() {
        return getItemList().entrySet()
                .stream()
                .map(Map.Entry::getKey)
                .mapToDouble(p -> p.getPrice() * getItemList().get(p)).sum();
    }

    public void addItem(Product product, int quantity) {
        itemList.put(product, quantity);
    }

    public void applyCoupon(Coupon coupon) {
        double newTotalAmount = getTotalAmount() - getCampaignDiscount();
        if (newTotalAmount >= coupon.getMinPurchaseAmount() &&
            coupon.getDiscountType().equals(DiscountType.Rate)){
                setCouponDiscount(getCouponDiscount() + (newTotalAmount * coupon.getDiscount()) / 100);
            }
    }

    public void applyDiscounts(List<Double> discountList) {
        double max = Collections.max(discountList);
        setCampaignDiscount(max);
    }

    @Override
    public double getTotalAmountAfterDiscounts() {
        double totalAmount = getTotalAmount();
        double couponDiscount = getCouponDiscount();
        double campaignDiscount = getCampaignDiscount();
        double totalAmountAfterDiscounts = totalAmount - (campaignDiscount + couponDiscount);

        return totalAmountAfterDiscounts;
    }

    public double CampaignDiscount(Campaign campaign) {
        final double[] discount = new double[1];
        getItemList().forEach((product, value) -> {
            if (product.getCategory().getTitle().equals(campaign.getCategory().getTitle())) {
                if (value >= campaign.getQuantity()) {
                    if (campaign.getDiscountType().equals(DiscountType.Rate)) {
                        discount[0] = ((product.getPrice() * value) * campaign.getDiscount()) / 100;
                    } else if (campaign.getDiscountType().equals(DiscountType.Amount)) {
                        discount[0] = campaign.getDiscount();
                    }
                }
            }
        });

        return discount[0];
    }

    public double getDeliveryCost() {
        Delivery delivery = new Delivery(1, 0.5);
        double del = delivery.calculateFor(this);
        return del;
    }

    public void print() {
        Map<Product, Integer> cartItemList = getItemList();
        Map<Category, List<Product>> groupByCategory = cartItemList.keySet().stream().collect(groupingBy(Product::getCategory));
        System.out.format("%-15s%-15s%-15s%-15s%-15s\n",
                "Category Name", "Product Name",
                "Quantity", "Unit Price", "Total Price");
        groupByCategory.forEach(((category, products) -> {
            products.forEach(product -> {
                System.out.format("%-15s%-15s%-15s%-15s%-15s\n",
                        category.getTitle(), product.getTitle(),
                        getItemList().get(product),
                        product.getPrice(), getItemList().get(product) * product.getPrice());
            });
        }));

        System.out.println("\nTotal Amount: " + getTotalAmountAfterDiscounts());
        System.out.println("Total Discount (coupon, campaign): " + getCampaignDiscount() + " , " + getCouponDiscount());
        System.out.println("Delivery Cost: " + getDeliveryCost());

    }

}
