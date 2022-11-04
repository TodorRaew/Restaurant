package firstRealProject.classResourses.objects;

import firstRealProject.classResourses.accsessories.Item;
import firstRealProject.classResourses.models.Staff;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class Waiter extends Staff
{
  private List<Table> tables;
  private BigDecimal  tips;
  private BigDecimal  turnOver;

  public Waiter(String firstName, String lastName, int age, BigDecimal salary)
  {
    super(firstName, lastName, age, salary);
    this.tables = new ArrayList<>();
    this.tips = BigDecimal.valueOf(0);
    this.turnOver = BigDecimal.valueOf(0);
  }

  public void setTurnOver(BigDecimal turnOver)
  {
    this.turnOver = this.turnOver.add(turnOver);
  }

  public BigDecimal getTurnOver()
  {
    return turnOver;
  }

  public List<Table> getTables()
  {
    return tables;
  }

  public void setTables(Table table)
  {
    this.tables.add(table);
  }

  public void getOrder(Restaurant restaurant)
  {
    //Iterate list of tables of each waiter
    for (Table table : tables) {
      Iterator<Client> iterator = table.getClients().iterator();
      //Iterate each client of table list clients
      while (iterator.hasNext()) {
        Client client = iterator.next();
        //Client make an order
        client.setOrder(restaurant);

        // Is client stay
        if (!client.isLeft()) {

          //Randomly taking one chef from the list of chefs
          restaurant.getChefs().
              get(new Random().
                  nextInt(restaurant.
                      getChefs().
                      size())).getProductsFromStorage(client, restaurant);

          //Randomly taking one bartender from the list of bartenders
          restaurant.getBartenders().
              get(new Random().
                  nextInt(restaurant.
                      getBartenders().
                      size())).getProductsFromStorage(client, restaurant);
        }
        else {
          iterator.remove();
        }

      }
    }
  }

  public void setTip(BigDecimal tips)
  {
    this.tips = this.tips.add(tips);
  }

  public BigDecimal getTip()
  {
    return tips;
  }

  public void cleanTable(Restaurant restaurant)
  {
    //waiter iterate the tables
    for (Table table : tables) {
      for (Client client : table.getClients()) {
        for (Item item : client.getOrder().getItems()) {
          //set the dirty dishes
          restaurant.setDirtyDishes(item.getDish());
        }
      }
    }
  }

  public void serveFood(Restaurant restaurant)
  {
    for (Table table : tables) {
      for (Client client : table.getClients()) {
            //chef set dish to the food
        restaurant.getChefs().get(new Random().nextInt(restaurant.getChefs().size())).prepareDish(client,restaurant);
      }
    }
  }

  public void serveDrink(Restaurant restaurant)
  {
    for (Table table : tables) {
      for (Client client : table.getClients()) {
        //bartender set dish to the drinks
        restaurant.getBartenders().get(new Random().nextInt(restaurant.getBartenders().size())).prepareDish(client,restaurant);
      }
    }
  }

  @Override
  public String toString()
  {
    return "Waiter{" +
        "tables=" + tables +
        "} " + super.toString();
  }

  public BigDecimal getTips()
  {
    return tips;
  }

  public void setTips(BigDecimal tips)
  {
    this.tips = this.tips.add(tips);
  }
}