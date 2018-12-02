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


public class ShoppingCartTest {
    private Category food;
    private Product apple;
    private Product almond;
    private ShoppingCart cart;
    private List<Double> discounts = new ArrayList();
    private Campaign campaign1;
    private Campaign campaign2;
    private Campaign campaign3;
    private double discount1;
    private double discount2;
    private double discount3;

    @Before
    public void setUp() throws Exception {
        food = new Category("food");
        apple = new Product("Apple", 100.0, food);
        almond = new Product("Almonds", 150.0, food);
        cart = new ShoppingCart();
        cart.addItem(apple, 3);
        cart.addItem(almond, 1);

        campaign1 = new Campaign(food, 20.0, 3, DiscountType.Rate);
        campaign2 = new Campaign(food, 50.0, 5, DiscountType.Rate);
        campaign3 = new Campaign(food, 5.0, 5, DiscountType.Amount);

        discount1 = cart.CampaignDiscount(campaign1);
        discounts.add(discount1);
        discount2 = cart.CampaignDiscount(campaign2);
        discounts.add(discount2);
        discount3 = cart.CampaignDiscount(campaign3);
        discounts.add(discount3);
        cart.applyDiscounts(discounts);
    }

    @After
    public void after() {
        cart.setCouponDiscount(0);
        cart.setCampaignDiscount(0);

    }

    @Test
    public void testGetTotalAmountAfterDiscounts() {
        assertEquals(390, cart.getTotalAmountAfterDiscounts(), 00001);

    }

    @Test
    public void testGetCouponDiscount() {
        Coupon coupon = new Coupon(100, 10, DiscountType.Rate);
        cart.applyCoupon(coupon);

        assertEquals(39, cart.getCouponDiscount(), 00001);

    }

    @Test
    public void testGetCampaignDiscount() {
        assertEquals(60, cart.getCampaignDiscount(), 00001);
    }

}
