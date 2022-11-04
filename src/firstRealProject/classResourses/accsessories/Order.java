package firstRealProject.classResourses.accsessories;


import firstRealProject.classResourses.objects.Table;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Order
{
  private List<Item> items ;


  public Order()
  {
    this.items = new ArrayList<>();
  }

  public BigDecimal getTotalOrderPrice()
  {
    BigDecimal sum = BigDecimal.ZERO;
    //Sum all item price to total order amount
    for (Item item : items) {
      sum = sum.add(item.getPrice());
    }
    return sum;
  }

  public List<Item> getItems()
  {
    return items;
  }

  public void setItems(Item item)
  {
    this.items.add(item);
  }

}