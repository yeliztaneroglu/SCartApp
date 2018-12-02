import com.trendyol.model.Category;
import com.trendyol.model.Product;
import com.trendyol.model.discount.Campaign;
import com.trendyol.model.discount.Coupon;
import com.trendyol.model.event.ShoppingCart;
import com.trendyol.util.DiscountType;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ShoppingCartHelperTest {
    private Category food;
    private Product apple;
    private Product almond;
    private ShoppingCart cart;

    @Before
    public void setUp() throws Exception {
        food = new Category("food");
        apple = new Product("Apple", 100.0, food);
        almond = new Product("Almonds", 150.0, food);
        cart = new ShoppingCart();
        cart.addItem(apple, 3);
        cart.addItem(almond, 1);
    }

    @After
    public void after() {
        cart.setCouponDiscount(0);
        cart.setCampaignDiscount(0);
    }

    @Test
    public void testGetDeliveryCost() {
        assertEquals(4.9, cart.getDeliveryCost(), 00001);
    }

    @Test
    public void testGetTotalAmount() {
        assertEquals(450, cart.getTotalAmount(), 00001);
    }

    @Test
    public void testApplyCouponLessThanMin() {
        Campaign campaign1 = new Campaign(food, 20.0, 3, DiscountType.Rate);
        List<Double> discounts = new ArrayList();
        double discount1 = cart.CampaignDiscount(campaign1);
        discounts.add(discount1);
        cart.applyDiscounts(discounts);
        Coupon coupon = new Coupon(9000000, 10, DiscountType.Rate);

        cart.applyCoupon(coupon);

        assertEquals(0, cart.getCouponDiscount(), 00001);
    }

    @Test
    public void testApplyCouponMoreThanMin() {
        Campaign campaign2 = new Campaign(food, 20.0, 3, DiscountType.Rate);
        List<Double> discounts = new ArrayList();
        double discount2 = cart.CampaignDiscount(campaign2);
        discounts.add(discount2);
        cart.applyDiscounts(discounts);
        Coupon coupon = new Coupon(100, 10, DiscountType.Rate);

        cart.applyCoupon(coupon);

        assertEquals(39, cart.getCouponDiscount(), 00001);
    }


    @Test
    public void testAddItem() {
        assertTrue(cart.getItemList().containsKey(apple));
    }

    @Test
    public void testCampaignDiscount() {
        Campaign campaign1 = new Campaign(food, 20.0, 3, DiscountType.Rate);

        double discount1 = cart.CampaignDiscount(campaign1);

        assertEquals(60, discount1, 00001);
    }
}

