import com.trendyol.model.Category;
import org.junit.Test;

import static org.junit.Assert.assertNull;

public class CategoryTest {
    @Test
    public void testCategorywithoutSubCategory(){
        Category food = new Category("food");

        assertNull(food.getSubCategory());
    }
}
