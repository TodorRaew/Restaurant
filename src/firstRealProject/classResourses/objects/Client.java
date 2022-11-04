package firstRealProject.classResourses.objects;

import firstRealProject.classResourses.accsessories.Item;
import firstRealProject.classResourses.accsessories.Menu;
import firstRealProject.classResourses.accsessories.Order;
import firstRealProject.classResourses.models.Person;
import firstRealProject.classResourses.accsessories.Order;
import firstRealProject.classResourses.models.Person;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Random;

public class Client extends Person
{

  private BigDecimal wallet;
  private Order      order;
  private boolean    isLeft;

  public Client(String firstName, String lastName, int age, BigDecimal wallet)
  {
    super(firstName, lastName, age);
    this.wallet = wallet;
    this.order = new Order();
    this.isLeft = false;
  }

  public void setDishOrder(Menu menu)
  {
    Random random = new Random();

    this.order.setItems(
        menu.getFoods().getStarter().get(random.nextInt(menu.getFoods().getStarter().size())));
    this.order.setItems(
        menu.getFoods().getMain().get(random.nextInt(menu.getFoods().getMain().size())));
    this.order.setItems(
        menu.getFoods().getDesert().get(random.nextInt(menu.getFoods().getDesert().size())));
  }

  public void setDrinkOrder(Menu menu)
  {
    Random random = new Random();

    this.order.setItems(
        menu.getDrinks().getCold().get(random.nextInt(menu.getDrinks().getCold().size())));
    this.order.setItems(
        menu.getDrinks().getHot().get(random.nextInt(menu.getDrinks().getHot().size())));
  }

  public void eat()
  {
    for (Item item : order.getItems()) {
      if (item.getFoodType().equalsIgnoreCase("starter")||item.getFoodType().equalsIgnoreCase("main")||item.getFoodType().equalsIgnoreCase("desert")) {
        //set dish is empty but still dirty
        item.getDish().setEmpty(true);
      }
    }
  }

  public void drink()
  {
    for (Item item : order.getItems()) {
      if (item.getFoodType().equalsIgnoreCase("cold")||item.getFoodType().equalsIgnoreCase("hot")) {
        //set cup is empty but still dirty
        item.getDish().setEmpty(true);
      }
    }
  }

  public BigDecimal payBill()
  {
    for (Item item : order.getItems()) {
      //Increment total item count order
      item.setCounter(item.getCounter() + 1);
    }
    //Decrease wallet with amount of the order
    this.wallet = this.wallet.subtract(order.getTotalOrderPrice());
    return order.getTotalOrderPrice();
  }

  public BigDecimal getWallet()
  {
    return wallet;
  }

  public void setWallet(BigDecimal wallet)
  {
    this.wallet = wallet;
  }

  public Order getOrder()
  {
    return order;
  }

  public void setOrder(Restaurant restaurant)
  {
    this.order.getItems().clear();

    //Client make an order
    this.setDrinkOrder(restaurant.getMenu());
    this.setDishOrder(restaurant.getMenu());

    int counterMoney = 3;
    int counterProducts = 3;

    while (true) {

      counterProducts--;
      // Check if order can be made
      if (!Chef.isPossibleToCompleteOrder(this, restaurant)
          || !Bartender.isPossibleToPrepareDrinkOrder(this, restaurant)) {

        // If order can't be made, client make another order
        this.order.getItems().clear();

        while (true) {
          this.order.getItems().clear();
          this.setDrinkOrder(restaurant.getMenu());
          this.setDishOrder(restaurant.getMenu());
          counterMoney--;

          if ((this.wallet.compareTo(order.getTotalOrderPrice())) >= 0) {
            break;
          }
          //if client is out of money 3 times, he leaves the restaurant
          if (counterMoney == 0) {

            this.setLeft(true);
            System.out.printf("✹ %-25s %s\n", (this.getFirstName() + " " + this.getLastName()), " --> Left, out of money");
            this.order.getItems().clear();
            return;
          }
        }

        //if client has money but his order can't be made 3 times, he leaves the restaurant
        if (counterProducts == 0) {

          this.setLeft(true);
          System.out.printf("✹ %-25s %s\n", (this.getFirstName() + " " + this.getLastName()), " --> Left, because the restaurant is out of products");
          this.order.getItems().clear();
          break;
        }
      }
      //if the order can be made and client has money, we make the order
      else if ((this.wallet.compareTo(order.getTotalOrderPrice())) >= 0) {
        break;
      }
      //if the order can be made and client doesn't have money he makes another order
      else {
        this.order.getItems().clear();
        this.setDrinkOrder(restaurant.getMenu());
        this.setDishOrder(restaurant.getMenu());
        counterMoney--;

        if (counterMoney == 0) {

          this.setLeft(true);
          System.out.printf("✹ %-25s %s\n", (this.getFirstName() + " " + this.getLastName()), " --> Left, out of money");
          this.order.getItems().clear();
          break;
        }
      }
    }
  }

  public BigDecimal giveTips()
  {
    Random random = new Random();
    MathContext m = new MathContext(3);
    //Randomly give tips between 10 and 30 %, if wallet after pay bill is more than 1
    if (new Random().nextBoolean() && this.wallet.compareTo(BigDecimal.ONE) > 0) {
      BigDecimal tip = wallet.multiply(BigDecimal.valueOf(random.nextInt(10, 31)))
          .divide(BigDecimal.valueOf(100)).round(m);
      wallet = getWallet().subtract(tip);
      return tip;
    }
    else {
      return BigDecimal.ZERO;
    }
  }

  public boolean isLeft()
  {
    return isLeft;
  }

  public void setLeft(boolean left)
  {
    isLeft = left;
  }

  @Override
  public String toString()
  {
    return "Client{} " + super.toString();
  }
}