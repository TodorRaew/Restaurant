package firstRealProject.classResourses.objects;

import firstRealProject.classResourses.models.Staff;
import java.math.BigDecimal;

public class DishWasher extends Staff
{

  public DishWasher(String firstName, String lastName, int age, BigDecimal salary)
  {
    super(firstName, lastName, age, salary);
  }

  public void washDish(Restaurant restaurant)
  {
    if (!restaurant.getDirtyDishes().isEmpty()) {

      Dish dish = restaurant.getDirtyDishes().pop();
      dish.setClean(true);
      restaurant.getCleanDishes().push(dish);
    }
  }
}