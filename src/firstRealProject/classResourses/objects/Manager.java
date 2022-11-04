package firstRealProject.classResourses.objects;

import firstRealProject.classResourses.models.Staff;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Manager extends Staff
{

  public Manager(String firstName, String lastName, int age, BigDecimal salary)
  {
    super(firstName, lastName, age, salary);
  }

  public void calculateProfit(Restaurant restaurant)
  {
    //Taking turnover from each waiter and adding to the restaurant total profit
    for (Waiter waiter : restaurant.getWaiters()) {

      restaurant.setTotalProfit(waiter.getTurnOver());
    }
  }
}