package firstRealProject;

import firstRealProject.classResourses.accsessories.ConsoleColor;
import firstRealProject.classResourses.accsessories.Item;
import firstRealProject.classResourses.objects.*;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.*;
import java.util.List;

public class Main
{
  static DecimalFormat df     = new DecimalFormat("0.00");
  static Random        random = new Random();
  static Scanner       scan   = new Scanner(System.in);

  public static void main(String[] args) throws IOException
  {
    List<Item> leastOrdered = new ArrayList<>();
    List<Item> mostOrdered = new ArrayList<>();

    System.out.print("Enter maximal number of clients which you want to generate: ");
    int maxNumberOfClients = validateInput(scan, 15, 120);

    System.out.print("Enter maximal number of chefs which you want to generate: ");
    int maxNumberOfChefs = validateInput(scan, 1, 3);

    System.out.print("Enter maximal number of waiters which you want to generate: ");
    int maxNumberOfWaiters = validateInput(scan, 1, 5);

    System.out.print("Enter maximal number of bartenders which you want to generate: ");
    int maxNumberOfBartenders = validateInput(scan, 1, 2);

    System.out.print("Enter maximal number of dishwashers which you want to generate: ");
    int maxNumberOfDishwashers = validateInput(scan, 1, 3);

    System.out.print("Enter maximal number of tables which you want to generate: ");
    int maxNumberOfTables = validateInput(scan, 4, 15);

    Restaurant restaurant = new Restaurant(maxNumberOfClients, maxNumberOfChefs, maxNumberOfBartenders, maxNumberOfTables, maxNumberOfWaiters, maxNumberOfDishwashers);

    printRestaurantProperties(restaurant);
    printStorageStats(restaurant);
    printMenu(restaurant, leastOrdered);

    // Restaurant properties - Step 1
    System.out.println("Press Enter to continue . . . ");
    scan.nextLine();


    while (!restaurant.getClients().isEmpty()) {

      // Give table to the clients or waiting
      setClientToTable(restaurant);

      // Set tables to the waiters
      setTableToWaiter(restaurant);

      // Waiter getting order from the client
      getOrderOfClient(restaurant);

      // Printing the order of each client
      printClientsOrders(restaurant);

      // the client eating
      setClientAction(restaurant);

      // the dishwasher washing
      dishWashing(random, restaurant);
    }

    //Clients stats - Step 2
    System.out.println("Press Enter to continue . . . ");
    scan.nextLine();

    getWaiterStats(restaurant);
    // Waiters stats - Step 3
    System.out.println("Press Enter to continue . . . ");
    scan.nextLine();

    List<Item> itemSorted = new ArrayList<Item>();
    itemSorted.addAll(restaurant.getMenu().getDrinks().getCold());
    itemSorted.addAll(restaurant.getMenu().getDrinks().getHot());
    itemSorted.addAll(restaurant.getMenu().getFoods().getStarter());
    itemSorted.addAll(restaurant.getMenu().getFoods().getMain());
    itemSorted.addAll(restaurant.getMenu().getFoods().getDesert());


    printOrderedItems(itemSorted);
    // Ordered Items  - Step 4
    System.out.println("Press Enter to continue . . . ");
    scan.nextLine();

    Comparator<Item> comparator = new Comparator<Item>()
    {
      @Override
      public int compare(Item o1, Item o2)
      {
        return Integer.compare(o1.getCounter(), o2.getCounter());
      }
    };

    //Sorted list according to the count of ordered of each item for the day
    itemSorted = itemSorted.stream().sorted(comparator).toList();

    //Taking count of the first item in the sorted list
    final int first = itemSorted.get(0).getCounter();
    //Taking count of the last item in the sorted list
    final int last = itemSorted.get(itemSorted.size() - 1).getCounter();

    if (!(last==0 && first == 0)) {

      //if the amount of least and most ordered is equal, taking first element from the list as least ordered,
      // else taking all items with equal count of orders to the first as least ordered
      leastOrdered = first == last ? itemSorted.subList(0, 1) : itemSorted.stream().filter(e -> (e.getCounter() == first)).toList();

      //if the amount of least and most ordered is equal, removing first element from the list,
      // else removing all items with equal count of orders to the first element
      itemSorted = first == last ? itemSorted.subList(1, itemSorted.size() - 1) : itemSorted.stream().filter(e -> (e.getCounter() > first)).toList();

      //if the amount of least and most ordered is equal, taking last element from the list as most ordered,
      // else taking all items with equal count of orders to the last as most ordered
      mostOrdered = first == last ? itemSorted.subList(itemSorted.size() - 2, itemSorted.size() - 1) : itemSorted.stream().filter(e -> (e.getCounter() == last)).toList();
    }
    printMostOrderedItems(mostOrdered);
    // Most ordered items stats - Step 5
    System.out.println("Press Enter to continue . . . ");
    scan.nextLine();
    printLeastOrderedItems(leastOrdered);
    // Least ordered items stats - Step 6
    System.out.println("Press Enter to continue . . . ");
    scan.nextLine();
    printSortedOrderedItems(itemSorted);
    // Sorted ordered items stats - Step 7
    System.out.println("Press Enter to continue . . . ");
    scan.nextLine();
    printMenu(restaurant, leastOrdered);
    System.out.println();
    // Menu after removed least ordered items - Step 8
    System.out.println("Press Enter to continue . . . ");
    scan.nextLine();
    printStorageStats(restaurant);
    // Updated Storage stats - Step 9

    System.out.println("Press Enter to continue . . . ");
    scan.nextLine();
    getTurnoverStats(restaurant);

    System.out.println();
    System.out.println("Press Enter to continue . . . ");
    scan.nextLine();
    printGreetings();

  }

  private static void printGreetings()
  {
    System.out.println();
    System.out.println();
    System.out.println(ConsoleColor.RED + "♥ ".repeat(40) + ConsoleColor.RESET);
    System.out.printf(ConsoleColor.RED + "♥" +ConsoleColor.GREEN_BOLD +" ".repeat(28)+"Thank you for your time!".toUpperCase(Locale.ROOT)+" ".repeat(30) + ConsoleColor.RED + "♥" + ConsoleColor.RESET + "\n", " ");
    System.out.printf(ConsoleColor.RED + "♥  "+ConsoleColor.GREEN_UNDERLINED + "Made by:" +ConsoleColor.RESET +ConsoleColor.RED+" ".repeat(72)+ "♥" + ConsoleColor.RESET + "\n", " ");
    System.out.printf(ConsoleColor.RED + "♥" + ConsoleColor.GREEN_BOLD+" ◆ Todor Raev"+ " ".repeat(69)+ConsoleColor.RED+ "♥" + ConsoleColor.RESET + "\n", " ");

    System.out.println(ConsoleColor.RED + "♥ ".repeat(40) + ConsoleColor.RESET);
    System.out.println();
  }

  private static void printSortedOrderedItems(List<Item> itemSorted)
  {
    System.out.println(ConsoleColor.PURPLE_BACKGROUND + " ".repeat(31) + ConsoleColor.RESET + " SORTED ORDERED ITEMS " + ConsoleColor.PURPLE_BACKGROUND + " ".repeat(30) + ConsoleColor.RESET);
    System.out.println("-".repeat(83));
    System.out.printf("%-35s %47s\n", "        Item name", "| Count |");
    System.out.println("-".repeat(83));

    itemSorted.forEach(item -> {
      System.out.printf("◆ %-71s |  %-4s |" + "\n", item.getName(), item.getCounter());
      System.out.println("-".repeat(83));
    });
    System.out.println(ConsoleColor.PURPLE_BACKGROUND + " ".repeat(83) + ConsoleColor.RESET);
    System.out.println();
  }

  private static void printLeastOrderedItems(List<Item> leastOrdered)
  {
    System.out.println(ConsoleColor.YELLOW_BACKGROUND + " ".repeat(31) + ConsoleColor.RESET + " LEAST ORDERED ITEMS " + ConsoleColor.YELLOW_BACKGROUND + " ".repeat(31) + ConsoleColor.RESET);
    System.out.println("-".repeat(83));
    System.out.printf("%-35s %47s\n", "        Item name", "| Count |");
    System.out.println("-".repeat(83));

    leastOrdered.forEach(item -> {
      System.out.printf("◆ %-71s |  %-4s |" + "\n", item.getName(), item.getCounter());
      System.out.println("-".repeat(83));
    });
    System.out.println(ConsoleColor.YELLOW_BACKGROUND + " ".repeat(83) + ConsoleColor.RESET);
    System.out.println();
  }

  private static void printMostOrderedItems(List<Item> mostOrdered)
  {
    System.out.println(ConsoleColor.CYAN_BACKGROUND_BRIGHT + " ".repeat(32) + ConsoleColor.RESET + " MOST ORDERED ITEMS " + ConsoleColor.CYAN_BACKGROUND_BRIGHT + " ".repeat(31) + ConsoleColor.RESET);
    System.out.println("-".repeat(83));
    System.out.printf("%-35s %47s\n", "        Item name", "| Count |");
    System.out.println("-".repeat(83));

    mostOrdered.forEach(item -> {
      System.out.printf("◆ %-71s |  %-4s |" + "\n", item.getName(), item.getCounter());
      System.out.println("-".repeat(83));
    });
    System.out.println(ConsoleColor.CYAN_BACKGROUND_BRIGHT + " ".repeat(83) + ConsoleColor.RESET);
    System.out.println();
  }

  private static void printOrderedItems(List<Item> itemSorted)
  {
    System.out.println(ConsoleColor.PURPLE_BACKGROUND_BRIGHT + " ".repeat(34) + ConsoleColor.RESET + " ORDERED ITEMS " + ConsoleColor.PURPLE_BACKGROUND_BRIGHT + " ".repeat(34) + ConsoleColor.RESET);
    System.out.println("-".repeat(83));
    System.out.printf("%-35s %47s\n", "        Item name", "| Count |");
    System.out.println("-".repeat(83));

    itemSorted.forEach(item -> {
      System.out.printf("◆ %-71s |  %-4s |" + "\n", item.getName(), item.getCounter());
      System.out.println("-".repeat(83));
    });
    System.out.println(ConsoleColor.PURPLE_BACKGROUND_BRIGHT + " ".repeat(83) + ConsoleColor.RESET);
    System.out.println();
  }

  private static void printClientsOrders(Restaurant restaurant)
  {
    System.out.println("\n");

    System.out.println(ConsoleColor.BLUE_BACKGROUND + " ".repeat(37) + ConsoleColor.RESET + " CLIENTS " + ConsoleColor.BLUE_BACKGROUND + " ".repeat(37) + ConsoleColor.RESET);

    restaurant.getWaiters().forEach(waiter -> {
      waiter.getTables().forEach(table -> {
        table.getClients().forEach(client -> {
          System.out.println("-".repeat(83));

          System.out.printf(ConsoleColor.YELLOW_BACKGROUND_BRIGHT + "%-40s | %44s\n", ("Client: " + client.getFirstName() + " " + client.getLastName() + " " + client.getAge() + " years old"), ("Wallet: " + df.format(client.getWallet()) + "lv." + ConsoleColor.RESET));
          System.out.println("-".repeat(38) + " Order " + "-".repeat(38));
          System.out.printf("%-35s %47s\n", "        Item", "|  Price   |");
          System.out.println("-".repeat(83));
          client.getOrder().getItems().forEach(item -> {

            System.out.printf("◆ %-68s | %5.2flv. |%n", item.getName(), item.getPrice());
          });
          System.out.println("-".repeat(83));

          System.out.printf("%-70s | %5.2flv. |\n", "        Total order price : ", client.getOrder().getTotalOrderPrice());
        });
      });
    });
    System.out.println("-".repeat(83));
    System.out.println(ConsoleColor.BLUE_BACKGROUND + " ".repeat(83) + ConsoleColor.RESET);
    System.out.println();
  }

  private static void printMenu(Restaurant restaurant, List<Item> leastOrderedItems)
  {
    Iterator<Item> iteratorHotDrink = restaurant.getMenu().getDrinks().getHot().iterator();
    Iterator<Item> iteratorColdDrink = restaurant.getMenu().getDrinks().getCold().iterator();
    Iterator<Item> iteratorStarterFood = restaurant.getMenu().getFoods().getStarter().iterator();
    Iterator<Item> iteratorMainFood = restaurant.getMenu().getFoods().getMain().iterator();
    Iterator<Item> iteratorDesertFood = restaurant.getMenu().getFoods().getDesert().iterator();

    System.out.println(ConsoleColor.RED_BACKGROUND + " ".repeat(45) + ConsoleColor.RESET + ConsoleColor.BLUE + " MENU " + ConsoleColor.RESET + ConsoleColor.RED_BACKGROUND + " ".repeat(46) + ConsoleColor.RESET);

    System.out.println("*".repeat(44) + " DRINKS " + "*".repeat(45));

    System.out.println("-".repeat(42) + " Hot Drinks " + "-".repeat(43));
    System.out.println(" ".repeat(76) + "|   ml/l  " + "|  Price  |");

    while (iteratorHotDrink.hasNext()) {
      Item item = iteratorHotDrink.next();
      for (Item leastOrderedItem : leastOrderedItems) {
        if (item.getName().equals(leastOrderedItem.getName())) {
          iteratorHotDrink.remove();
        }
      }
    }
    for (Item item : restaurant.getMenu().getDrinks().getHot()) {

      System.out.printf("● %-73s | %-4.0fml. | %-5.2flv.|%n", item.getName(), item.getWeight(), item.getPrice());
    }
    System.out.println();

    System.out.println("-".repeat(42) + " Cold Drinks " + "-".repeat(43));
    System.out.println(" ".repeat(76) + "|   ml/l  " + "|  Price  |");

    while (iteratorColdDrink.hasNext()) {
      Item item = iteratorColdDrink.next();
      for (Item leastOrderedItem : leastOrderedItems) {
        if (item.getName().equals(leastOrderedItem.getName())) {
          iteratorColdDrink.remove();
        }
      }
    }

    for (Item item : restaurant.getMenu().getDrinks().getCold()) {
      System.out.printf("● %-73s | %-4.0fml. | %-5.2flv.|%n", item.getName(), item.getWeight(), item.getPrice());
    }
    System.out.println();
    System.out.println("*".repeat(45) + " FOOD " + "*".repeat(46));
    System.out.println("-".repeat(43) + " Starters " + "-".repeat(44));
    System.out.println(" ".repeat(76) + "| Weight  " + "|  Price  |");

    while (iteratorStarterFood.hasNext()) {
      Item item = iteratorStarterFood.next();
      for (Item leastOrderedItem : leastOrderedItems) {
        if (item.getName().equals(leastOrderedItem.getName())) {
          iteratorStarterFood.remove();
        }
      }
    }

    for (Item item : restaurant.getMenu().getFoods().getStarter()) {
      System.out.printf("● %-73s | %-4.0fgr. |%-6.2flv.|%n", item.getName(), item.getWeight(), item.getPrice());
    }
    System.out.println();
    System.out.println("-".repeat(45) + " Main " + "-".repeat(46));
    System.out.println(" ".repeat(76) + "| Weight  " + "|  Price  |");

    while (iteratorMainFood.hasNext()) {
      Item item = iteratorMainFood.next();
      for (Item leastOrderedItem : leastOrderedItems) {
        if (item.getName().equals(leastOrderedItem.getName())) {
          iteratorMainFood.remove();
        }
      }
    }

    for (Item item : restaurant.getMenu().getFoods().getMain()) {
      System.out.printf("● %-73s | %-4.0fgr. |%-6.2flv.|%n", item.getName(), item.getWeight(), item.getPrice());
    }
    System.out.println();
    System.out.println("-".repeat(43) + " Deserts " + "-".repeat(45));
    System.out.println(" ".repeat(76) + "| Weight  " + "|  Price  |");

    while (iteratorDesertFood.hasNext()) {
      Item item = iteratorDesertFood.next();
      for (Item leastOrderedItem : leastOrderedItems) {
        if (item.getName().equals(leastOrderedItem.getName())) {
          iteratorDesertFood.remove();
        }
      }
    }

    for (Item item : restaurant.getMenu().getFoods().getDesert()) {
      System.out.printf("● %-73s | %-4.0fgr. |%-6.2flv.|%n", item.getName(), item.getWeight(), item.getPrice());
    }
    System.out.println(ConsoleColor.RED_BACKGROUND + " ".repeat(97) + ConsoleColor.RESET);
  }

  private static void printRestaurantProperties(Restaurant restaurant)
  {
    System.out.println(ConsoleColor.RED_BACKGROUND + " ".repeat(80) + ConsoleColor.RESET);
    System.out.printf(ConsoleColor.RED_BACKGROUND + "   " + ConsoleColor.RESET + " Restaurant name: " + ConsoleColor.GREEN_BOLD + restaurant.getName() + ConsoleColor.RESET + "%41s" + ConsoleColor.RED_BACKGROUND + "   " + ConsoleColor.RESET + "\n", " ");
    System.out.printf(ConsoleColor.RED_BACKGROUND + "   " + ConsoleColor.RESET + " Capacity: " + ConsoleColor.GREEN_BOLD + restaurant.getCapacity() + "%61s" + ConsoleColor.RESET + ConsoleColor.RED_BACKGROUND + "   " + ConsoleColor.RESET + "\n", " ");
    System.out.printf(ConsoleColor.RED_BACKGROUND + "   " + ConsoleColor.RESET + " Start capital: " + ConsoleColor.GREEN_BOLD + restaurant.getStartCapital() + ConsoleColor.RESET + "%54s" + ConsoleColor.RED_BACKGROUND + "   " + ConsoleColor.RESET + "\n", " ");
    System.out.printf(ConsoleColor.RED_BACKGROUND + "   " + ConsoleColor.RESET + " Manager: " + ConsoleColor.GREEN_BOLD + "%-36s %s Salary: %.2f" + ConsoleColor.RESET + "%10s" + ConsoleColor.RED_BACKGROUND + "   " + ConsoleColor.RESET + "\n", (restaurant.getManager().getFirstName() + " " + restaurant.getManager().getLastName() + " " + restaurant.getManager().getAge() + " year old "), "|", restaurant.getManager().getSalary(), " ");

    for (Waiter waiter : restaurant.getWaiters()) {
      System.out.printf(ConsoleColor.RED_BACKGROUND + "   " + ConsoleColor.RESET + ConsoleColor.RESET + " Waiter: " + ConsoleColor.GREEN_BOLD + "%-37s %s Salary: %.2f" + ConsoleColor.RESET + "%10s" + ConsoleColor.RED_BACKGROUND + "   " + ConsoleColor.RESET + "\n", (waiter.getFirstName() + " " + waiter.getLastName() + " " + waiter.getAge() + " year old "), "|", waiter.getSalary(), " ");
    }

    for (Chef chef : restaurant.getChefs()) {
      System.out.printf(ConsoleColor.RED_BACKGROUND + "   " + ConsoleColor.RESET + " Chef: " + ConsoleColor.GREEN_BOLD + "%-39s %s Salary: %.2f" + ConsoleColor.RESET + "%10s" + ConsoleColor.RED_BACKGROUND + "   " + ConsoleColor.RESET + "\n", (chef.getFirstName() + "  " + chef.getLastName() + " " + chef.getAge() + " year old"), "|", chef.getSalary(), " ");
    }

    for (Bartender bartender : restaurant.getBartenders()) {
      System.out.printf(ConsoleColor.RED_BACKGROUND + "   " + ConsoleColor.RESET + " Bartender: " + ConsoleColor.GREEN_BOLD + "%-34s %s Salary: %.2f" + ConsoleColor.RESET + "%10s" + ConsoleColor.RED_BACKGROUND + "   " + ConsoleColor.RESET + "\n", (bartender.getFirstName() + " " + bartender.getLastName() + " " + bartender.getAge() + " year old"), "|", bartender.getSalary(), " ");
    }

    for (DishWasher dishWasher : restaurant.getDishwashers()) {
      System.out.printf(ConsoleColor.RED_BACKGROUND + "   " + ConsoleColor.RESET + " DishWasher: " + ConsoleColor.GREEN_BOLD + "%-33s %s Salary: %.2f" + ConsoleColor.RESET + "%10s" + ConsoleColor.RED_BACKGROUND + "   " + ConsoleColor.RESET + "\n", (dishWasher.getFirstName() + " " + dishWasher.getLastName() + " " + dishWasher.getAge() + " year old"), "|", dishWasher.getSalary(), " ");
    }
    System.out.println(ConsoleColor.RED_BACKGROUND + " ".repeat(80) + ConsoleColor.RESET);
    System.out.println();
  }

  private static void printStorageStats(Restaurant restaurant)
  {
    System.out.println(ConsoleColor.BLACK_BACKGROUND + " ".repeat(37) + ConsoleColor.RESET + " STORAGE " + ConsoleColor.BLACK_BACKGROUND + " ".repeat(36) + ConsoleColor.RESET);
    System.out.printf("  %-67s | %s |%n", "Product name", "Quantity");
    System.out.println("-".repeat(82));
    restaurant.getStorage().getProducts().forEach((k, v) -> {
      System.out.printf("◆ %-67s | %8.0f |%n", k, v);
    });
    System.out.println("-".repeat(82));
    System.out.println(ConsoleColor.BLACK_BACKGROUND + " ".repeat(82) + ConsoleColor.RESET);
    System.out.println();
  }

  private static void getWaiterStats(Restaurant restaurant)
  {
    System.out.println(ConsoleColor.GREEN_BACKGROUND_BRIGHT + " ".repeat(37) + ConsoleColor.RESET + " WAITERS " + ConsoleColor.GREEN_BACKGROUND_BRIGHT + " ".repeat(37) + ConsoleColor.RESET);
    System.out.printf("%-56s %s %s\n", "        Name", "|   Tips    |", " Turnover  |");
    System.out.println("-".repeat(83));
    for (Waiter waiter : restaurant.getWaiters()) {

      System.out.printf("◆ %-54s |%7slv. | %7slv. | %n", (waiter.getFirstName() + " " + waiter.getLastName()), df.format(waiter.getTips()), df.format(waiter.getTurnOver()));
    }
    System.out.println("-".repeat(83));
    System.out.println(ConsoleColor.GREEN_BACKGROUND_BRIGHT + " ".repeat(83) + ConsoleColor.RESET);
    System.out.println();
  }

  private static void getTurnoverStats(Restaurant restaurant)
  {
    restaurant.getManager().calculateProfit(restaurant);
    System.out.println(ConsoleColor.CYAN_BACKGROUND + " ".repeat(83) + ConsoleColor.RESET);
    System.out.println(ConsoleColor.CYAN_BACKGROUND + "   " + ConsoleColor.RESET + " ".repeat(77) + ConsoleColor.CYAN_BACKGROUND + "   " + ConsoleColor.RESET);
    System.out.println("                      " + ConsoleColor.BLACK_UNDERLINED + ConsoleColor.BLACK_BOLD + "Restaurant total profit:  " + df.format(restaurant.getTotalProfit()) + "lv." + ConsoleColor.RESET);
    System.out.println(ConsoleColor.CYAN_BACKGROUND + "   " + ConsoleColor.RESET + " ".repeat(77) + ConsoleColor.CYAN_BACKGROUND + "   " + ConsoleColor.RESET);
    System.out.print(ConsoleColor.CYAN_BACKGROUND + " ".repeat(83) + ConsoleColor.RESET);
  }

  private static int validateInput(Scanner scan, int minRange, int maxRange)
  {

    int input;
    while (true) {
      try {
        // if enter string throw NumberFormatException
        // if input out of range throw IllegalArgumentException
        input = Integer.parseInt(scan.nextLine());
        // if input is between min and max range break the while loop and return input
        if (input >= minRange && input <= maxRange) {
          break;
        }
        else {
          throw new IllegalArgumentException();
        }
      }
      catch (NumberFormatException e) {
        System.err.println("Enter digit, not a string");
      }
      catch (IllegalArgumentException e) {
        System.err.println("The number is out of range, range should be between " + minRange + " and " + maxRange);
      }
    }
    return input;
  }

  private static void dishWashing(Random random, Restaurant restaurant)
  {
    //while there are dirty dishes
    while (!restaurant.getDirtyDishes().isEmpty()) {

      //Randomly taking dishwasher wash dirty dish and push to the stack with clean dishes
      restaurant.getDishwashers().get(random.nextInt(restaurant.getDishwashers().size())).washDish(restaurant);
    }
  }

  private static void setClientAction(Restaurant restaurant)
  {
    for (Waiter waiter : restaurant.getWaiters()) {
      waiter.serveFood(restaurant);
      waiter.serveDrink(restaurant);

      for (Table table : waiter.getTables()) {
        for (Client client : table.getClients()) {

          client.eat();
          client.drink();
          waiter.setTurnOver(client.payBill());
          waiter.setTip(client.giveTips());
        }
      }
      // taking dirty dishes from the table and push to the stack with dirty dishes
      waiter.cleanTable(restaurant);
      //iterate throw all tables of the waiter and set tables free
      waiter.getTables().forEach(table -> {
        table.setFree(true);
      });
      //Removing tables from each waiter and set them back to the restaurant table list
      restaurant.getTables().addAll(waiter.getTables());
      waiter.getTables().clear();
    }
  }

  private static void getOrderOfClient(Restaurant restaurant)
  {
    for (int i = 0; i < restaurant.getWaiters().size(); i++) {

      restaurant.getWaiters().get(i).getOrder(restaurant);
    }
  }

  private static void setTableToWaiter(Restaurant restaurant)
  {
    Iterator<Table> tableIterator = restaurant.getTables().iterator();
    int counterWaiters = 0;
    //Iterate all tables
    while (tableIterator.hasNext()) {

      Table table = tableIterator.next();

      //Set one table to each waiter
      restaurant.getWaiters().get(counterWaiters).setTables(table);
      // Check if last waiter is reached
      if (counterWaiters == restaurant.getWaiters().size() - 1) {
        counterWaiters = 0;
      }
      else {
        counterWaiters++;
      }
      // Removing the table from the restaurant table list
      tableIterator.remove();
    }
  }


  private static void setClientToTable(Restaurant restaurant)
  {
    Iterator<Client> iteratorClients = restaurant.getClients().iterator();

    //Iterate all tables
    for (int i = 0; i < restaurant.getTables().size(); i++) {

      List<Client> clientsListOnTable = new ArrayList<>();
      // Set client to each seat
      for (int j = 0; j < restaurant.getTables().get(i).getSeats(); j++) {

        // While has clients, set client to the seat and removing from the list of unseated clients
        if (iteratorClients.hasNext()) {

          Client client = iteratorClients.next();
          clientsListOnTable.add(client);
          iteratorClients.remove();
        }
      }
      // when 4 clients are seated to the table, set table full
      if (clientsListOnTable.size() == 4) {

        restaurant.getTables().get(i).setFree(false);
      }
      // Set clients in to the table list (Each table has own list)
      restaurant.getTables().get(i).setClients(clientsListOnTable);
    }

    //After all capacity is full, checking if there are more client
    if (!restaurant.getClients().isEmpty()) {

      Random random1 = new Random();
      // Iterate to unseated clients
      while (iteratorClients.hasNext()) {
        iteratorClients.next();
        boolean isWaiting = random1.nextBoolean();
        //asking the client to stay or leave - randomly answer
        if (!isWaiting) {

          iteratorClients.remove();
        }
      }
    }
  }
}