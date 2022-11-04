package firstRealProject.classResourses.objects;

import firstRealProject.classResourses.Interfaces.Preparable;
import firstRealProject.classResourses.accsessories.Item;
import firstRealProject.classResourses.models.Staff;

import java.math.BigDecimal;
import java.util.Map;

public class Bartender extends Staff implements Preparable
{
  public Bartender(String firstName, String lastName, int age, BigDecimal salary)
  {
    super(firstName, lastName, age, salary);
  }

  @Override
  public void prepareDish(Client client,Restaurant restaurant)
  {
    for (Item item : client.getOrder().getItems()) {
      if (item.getFoodType().equalsIgnoreCase("cold")||item.getFoodType().equalsIgnoreCase("hot")) {
        //set cup full and dirty

        item.setDish(restaurant.getCleanDishes().pop());
        item.getDish().setEmpty(false);
        item.getDish().setClean(false);
      }
    }
  }

  public static boolean isPossibleToPrepareDrinkOrder(Client client, Restaurant restaurant)
  {

    for (int k = 0; k < client.getOrder().getItems().size(); k++) {
      // Check about item is drink
      if (client.getOrder().getItems().get(k).getFoodType().equalsIgnoreCase("hot")||client.getOrder().getItems().get(k).getFoodType().equalsIgnoreCase("cold")) {
        //Iterate each item and check its ingredients in the storage
        for (Map.Entry<String, Double> entry : client.getOrder().getItems().get(k).getIngredians()
            .entrySet()) {

          String key = entry.getKey();
          Double value = entry.getValue();
          //Checking availability of ingredients
          if ((restaurant.getStorage().getProducts().get(key)).compareTo(value) < 0) {
            return false;

          }
        }
      }
    }
    return true;
  }

  @Override
  public void getProductsFromStorage(Client client, Restaurant restaurant)
  {
    // enough ingredients in the storage
    if (isPossibleToPrepareDrinkOrder(client,restaurant)) {

      //iterate items of client order
      for (int k = 0; k < client.getOrder().getItems().size(); k++) {
        // Check about item is drink
        if (client.getOrder().getItems().get(k).getFoodType().equalsIgnoreCase("hot")||client.getOrder().getItems().get(k).getFoodType().equalsIgnoreCase("cold")) {
          for (Map.Entry<String, Double> entry : client.getOrder().getItems().get(k).getIngredians()
              .entrySet()) {
            String key = entry.getKey();
            Double value = entry.getValue();
            //Taking necessary quantity from the storage and set updated ingredients in the storage
            restaurant.getStorage().getProducts()
                .put(key, restaurant.getStorage().getProducts().get(key) - value);
          }
        }
      }
    }
  }
}