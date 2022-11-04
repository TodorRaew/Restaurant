package firstRealProject.classResourses.objects;

import firstRealProject.classResourses.accsessories.Item;
import firstRealProject.classResourses.accsessories.Menu;

import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Restaurant
{
  private final String           name = "Pizza place 101";
  private       Manager          manager;
  private       List<Client>     clients;
  private       List<Table>      tables;
  private       List<Waiter>     waiters;
  private       List<Chef>       chefs;
  private       List<Bartender>  bartenders;
  private       List<DishWasher> dishwashers;
  private       int              capacity;
  private       BigDecimal       startCapital;
  private       BigDecimal       totalProfit;
  private       Menu             menu;
  private       Storage          storage;
  private       Stack<Dish>      cleanDishes;
  private       Stack<Dish>      dirtyDishes;
  private       int              maxNumberOfClients;
  private       int              maxNumberOfChefs;
  private       int              maxNumberOfBartenders;
  private       int              maxNumberOfTables;
  private       int              maxNumberOfWaiters;
  private       int              maxNumberOfDishwashers;


  public Restaurant(int maxNumberOfClients, int maxNumberOfChefs, int maxNumberOfBartenders, int maxNumberOfTables, int maxNumberOfWaiters, int maxNumberOfDishwashers) throws IOException
  {
    this.maxNumberOfClients = maxNumberOfClients;
    this.maxNumberOfChefs = maxNumberOfChefs;
    this.maxNumberOfBartenders = maxNumberOfBartenders;
    this.maxNumberOfTables = maxNumberOfTables;
    this.maxNumberOfWaiters = maxNumberOfWaiters;
    this.maxNumberOfDishwashers = maxNumberOfDishwashers;

    this.clients = new ArrayList<>();
    this.tables = new ArrayList<>();
    this.waiters = new ArrayList<>();
    this.chefs = new ArrayList<>();
    this.bartenders = new ArrayList<>();
    this.dishwashers = new ArrayList<>();
    this.startCapital = BigDecimal.valueOf(1000);
    this.totalProfit = BigDecimal.valueOf(0);
    this.menu = new Menu();
    this.storage = new Storage();
    this.cleanDishes = new Stack<>();
    this.dirtyDishes = new Stack<>();
    loadRestaurant();
    this.capacity = this.getTables().size() * 4;
    setCleanDishes();
  }

  private void loadRestaurant() throws IOException
  {
    Random random = new Random();

    // Set table counter in range 4 - 15
    int tableCounter = random.nextInt(4, maxNumberOfTables + 1);


    String path = "data_base\\Clients.txt";
    Methods.generateClients(this.clients, path, maxNumberOfClients);
    path = "data_base\\Chefs.txt";
    Methods.generateChefs(this.chefs, path, maxNumberOfChefs);
    path = "data_base\\Waiters.txt";
    Methods.generateWaiters(this.waiters, path, maxNumberOfWaiters);
    path = "data_base\\Bartender.txt";
    Methods.generateBartender(this.bartenders, path, maxNumberOfBartenders);
    path = "data_base\\Dishwasher.txt";
    Methods.generateDishwasher(this.dishwashers, path, maxNumberOfDishwashers);
    String pathManager = "data_base\\Manager.txt";
    Methods.generateManager(this, pathManager);

    path = "data_base\\menu_drinks_cold.txt";
    String path1 = "data_base\\menu_drinks_hot.txt";
    String path2 = "data_base\\menu_food_desert.txt";
    String path3 = "data_base\\menu_food_starter.txt";
    String path4 = "data_base\\menu_food_main.txt";
    String path5 = "data_base\\Products.txt";

    menu.setFoods(Methods.generateItems(path3, 5, 11, 22), Methods.generateItems(path4, 10, 20, 52),
        Methods.generateItems(path2, 3, 6, 6));
    menu.setDrinks(Methods.generateItems(path1, 3, 6, 6), Methods.generateItems(path, 5, 10, 28));
    storage.setProducts(Methods.generateProducts(path5));

    for (int i = 0; i < tableCounter; i++) {
      tables.add(new Table());
    }

    this.setTables(tables);
    this.setClients(clients);
    this.setBartenders(bartenders);
    this.setWaiters(waiters);
    this.setChefs(chefs);
    this.setMenu(menu);
  }

  public void setCleanDishes()
  {
    for (int i = 0; i < capacity*5; i++) {
      this.cleanDishes.push(new Dish());
    }

  }

  public String getName()
  {
    return name;
  }

  public Manager getManager()
  {
    return manager;
  }

  public Storage getStorage()
  {
    return storage;
  }

  public void setStorage(Storage storage)
  {
    this.storage = storage;
  }

  public void setManager(Manager manager)
  {
    this.manager = manager;
  }

  public List<Client> getClients()
  {
    return clients;
  }

  public void setClients(List<Client> clients)
  {
    this.clients = clients;
  }

  public List<Table> getTables()
  {
    return tables;
  }

  public void setTables(List<Table> tables)
  {
    this.tables = tables;
  }

  public List<Waiter> getWaiters()
  {
    return waiters;
  }

  public void setWaiters(List<Waiter> waiters)
  {
    this.waiters = waiters;
  }

  public List<Chef> getChefs()
  {
    return chefs;
  }

  public void setChefs(List<Chef> chefs)
  {
    this.chefs = chefs;
  }

  public List<Bartender> getBartenders()
  {
    return bartenders;
  }

  public void setBartenders(List<Bartender> bartenders)
  {
    this.bartenders = bartenders;
  }

  public List<DishWasher> getDishwashers()
  {
    return dishwashers;
  }

  public void setDishwashers(List<DishWasher> dishwashers)
  {
    this.dishwashers = dishwashers;
  }

  public int getCapacity()
  {
    return capacity;
  }

  public void setCapacity(int capacity)
  {
    this.capacity = capacity;
  }

  public BigDecimal getStartCapital()
  {
    return startCapital;
  }

  public void setStartCapital(BigDecimal startCapital)
  {
    this.startCapital = startCapital;
  }

  public BigDecimal getTotalProfit()
  {
    return totalProfit;
  }

  public void setTotalProfit(BigDecimal totalProfit)
  {
    this.totalProfit = this.totalProfit.add(totalProfit);
  }

  public Stack<Dish> getCleanDishes()
  {
    return cleanDishes;
  }

  public Stack<Dish> getDirtyDishes()
  {
    return dirtyDishes;
  }

  public void setDirtyDishes(Dish dish)
  {
    this.dirtyDishes.push(dish);
  }

  public Menu getMenu()
  {
    return menu;
  }

  public void setMenu(Menu menu)
  {
    this.menu = menu;
  }

  public static class Methods
  {
    public static List<Item> generateItems(String path, int lower, int upper, int limit) throws IOException
    {
      List<Item> itemList = new ArrayList<>();
      //Taking path of the text file
      Path path1 = Paths.get(path);

      Random random = new Random();
      int count = random.nextInt(lower, upper+1);
      List<Integer> pickUps = new ArrayList<>();
      for (int i = 0; i < count; i++) {
        int pickUp;
        while (true) {
          pickUp = random.nextInt(1, limit+1);
          //Check for duplicate elements
          if (!pickUps.contains(pickUp)) {
            pickUps.add(pickUp);
            break;
          }
        }
        //Transfer data from text file to string
        BufferedReader reader = Files.newBufferedReader(path1);
        // each line = one row from the text file
        List<String> line = reader.lines().toList();
        for (String t : line) {

          LinkedHashMap<String, Double> ingredients = new LinkedHashMap<>();
          //split line on two parts with delimiter " | "
          List<String> input1 = List.of(t.split("\\s+\\|\\s+"));
          //split last element from input1 with delimiter " - ", that way we get ingredients
          List<String> input2 = List.of(input1.get(1).split("\\s+-\\s+"));

          for (String line2 : input2) {
            //split each ingredient with delimiter " @ ", and get name(0) and quantity(1)
            List<String> x = List.of(line2.split("\\s+@\\s+"));

            ingredients.put(x.get(0), Double.parseDouble(x.get(1)));
          }

          //split first element from input1 with delimiter " ", that way we get item properties (Item name, price and weight)
          List<String> input = List.of(input1.get(0).split("\\s+"));
          int id = Integer.parseInt(input.get(0));
          double price = Double.parseDouble(input.get(input.size() - 2));
          double weight = Double.parseDouble(input.get(input.size() - 1));
          String name = String.join(" ", input.subList(1, input.size() - 2));
          //That way we pick up unique item from the text file
          if (pickUp == id) {
            itemList.add(new Item(name, weight, BigDecimal.valueOf(price), ingredients));
          }
        }
      }
      return itemList;
    }

    public static void generateClients(List<Client> clientList, String paths, int upper) throws IOException
    {
      //Taking path of the text file
      Path path = Paths.get(paths);

      Random random = new Random();
      int count = random.nextInt(15, upper + 1);
      List<Integer> pickUps = new ArrayList<>();
      for (int i = 0; i < count; i++) {
        int pickUp;
        while (true) {
          pickUp = random.nextInt(1, 121);
          //Check for duplicate elements
          if (!pickUps.contains(pickUp)) {
            pickUps.add(pickUp);
            break;
          }
        }
        //Transfer data from text file to string
        BufferedReader reader = Files.newBufferedReader(path);
        // each line = one row from the text file
        List<String> line = reader.lines().toList();
        for (String t : line) {
          //split line on three parts with delimiter " "
          String[] input = t.split("\\s+");
          int id = Integer.parseInt(input[0]);
          //That way we pick up unique client from the text file
          if (pickUp == id) {
            clientList.add(new Client(input[1], input[2], random.nextInt(18, 60),
                BigDecimal.valueOf(random.nextDouble(40, 100))));
            break;
          }
        }
      }
    }

    public static void generateChefs(List<Chef> chefList, String paths, int upper) throws IOException
    {
      //Taking path of the text file
      Path path = Paths.get(paths);

      Random random = new Random();
      int count = random.nextInt(1, upper + 1);
      List<Integer> pickUps = new ArrayList<>();
      for (int i = 0; i < count; i++) {

        int pickUp;
        //Check for duplicate elements
        while (true) {
          pickUp = random.nextInt(1, 41);
          if (!pickUps.contains(pickUp)) {
            pickUps.add(pickUp);
            break;
          }
        }
        //Transfer data from text file to string
        BufferedReader reader = Files.newBufferedReader(path);
        // each line = one row from the text file
        List<String> line = reader.lines().toList();
        for (String t : line) {
          //split line on three parts with delimiter " "
          String[] input = t.split("\\s+");
          int id = Integer.parseInt(input[0]);
          //That way we pick up unique chef from the text file
          if (pickUp == id) {
            chefList.add(new Chef(input[1], input[2], random.nextInt(18, 60), BigDecimal.valueOf(random.nextDouble(1500.0, 3000.0))));
          }
        }
      }
    }

    public static void generateBartender(List<Bartender> bartenderList, String paths, int upper) throws IOException
    {
      //Taking path of the text file
      Path path = Paths.get(paths);

      Random random = new Random();
      int count = random.nextInt(1, upper + 1);
      List<Integer> pickUps = new ArrayList<>();
      for (int i = 0; i < count; i++) {
        int pickUp;
        while (true) {
          pickUp = random.nextInt(1, 40);
          //Check for duplicate elements
          if (!pickUps.contains(pickUp)) {
            pickUps.add(pickUp);
            break;
          }
        }
        //Transfer data from text file to string
        BufferedReader reader = Files.newBufferedReader(path);
        // each line = one row from the text file
        List<String> line = reader.lines().toList();
        for (String t : line) {
          //split line on three parts with delimiter " "
          String[] input = t.split("\\s+");
          int id = Integer.parseInt(input[0]);
          //That way we pick up unique bartender from the text file
          if (pickUp == id) {
            bartenderList.add(new Bartender(input[1], input[2], random.nextInt(18, 60), BigDecimal.valueOf(random.nextDouble(1500.0, 2000.0))));
          }
        }
      }
    }

    public static void generateWaiters(List<Waiter> waiterList, String paths, int upper) throws IOException
    {
      //Taking path of the text file
      Path path = Paths.get(paths);

      Random random = new Random();
      int count = random.nextInt(1, upper + 1);
      List<Integer> pickUps = new ArrayList<>();
      for (int i = 0; i < count; i++) {
        int pickUp;
        while (true) {
          pickUp = random.nextInt(1, 41);
          //Check for duplicate elements
          if (!pickUps.contains(pickUp)) {
            pickUps.add(pickUp);
            break;
          }
        }
        //Transfer data from text file to string
        BufferedReader reader = Files.newBufferedReader(path);
        // each line = one row from the text file
        List<String> line = reader.lines().toList();
        for (String t : line) {
          //split line on three parts with delimiter " "
          String[] input = t.split("\\s+");
          int id = Integer.parseInt(input[0]);
          //That way we pick up unique waiter from the text file
          if (pickUp == id) {

            waiterList.add(new Waiter(input[1], input[2], random.nextInt(18, 60), BigDecimal.valueOf(random.nextDouble(1000.0, 1500.0))));
          }
        }
      }
    }

    public static void generateManager(Restaurant restaurant, String pathManager) throws IOException
    {
      //Taking path of the text file
      Path path = Paths.get(pathManager);

      Random random = new Random();
      int pickUp = random.nextInt(1, 7);
//Transfer data from text file to string
      BufferedReader reader = Files.newBufferedReader(path);
      // each line = one row from the text file
      List<String> line = reader.lines().toList();
      for (String t : line) {
        //split line on three parts with delimiter " "
        String[] input = t.split("\\s+");
        int id = Integer.parseInt(input[0]);
        //That way we pick up unique manager from the text file
        if (pickUp == id) {
          restaurant.setManager((new Manager(input[1], input[2], random.nextInt(18, 60),
              BigDecimal.valueOf(random.nextDouble(3000, 5000.0)))));
          break;
        }
      }
    }

    public static void generateDishwasher(List<DishWasher> dishWashers, String paths, int upper) throws IOException
    {
      //Taking path of the text file
      Path path = Paths.get(paths);

      Random random = new Random();
      int count = random.nextInt(1, upper + 1);
      List<Integer> pickUps = new ArrayList<>();
      for (int i = 0; i < count; i++) {
        int pickUp;
        while (true) {
          pickUp = random.nextInt(1, 6);
          //Check for duplicate elements
          if (!pickUps.contains(pickUp)) {
            pickUps.add(pickUp);
            break;
          }
        }
        //Transfer data from text file to string
        BufferedReader reader = Files.newBufferedReader(path);
        // each line = one row from the text file
        List<String> line = reader.lines().toList();
        for (String t : line) {
          String[] input = t.split("\\s+");
          int id = Integer.parseInt(input[0]);
          //That way we pick up unique manager from the dishwasher file
          if (pickUp == id) {

            dishWashers.add(new DishWasher(input[1], input[2], random.nextInt(18, 60), BigDecimal.valueOf(random.nextDouble(1000.0, 1100.0))));
          }
        }
      }
    }

    public static LinkedHashMap<String, Double> generateProducts(String path) throws IOException
    {
      LinkedHashMap<String, Double> productList = new LinkedHashMap<>();
      //Taking path of the text file
      Path path1 = Paths.get(path);

      for (int i = 0; i < 55; i++) {
        //Transfer data from text file to string
        BufferedReader reader = Files.newBufferedReader(path1);
        // each line = one row from the text file
        List<String> line = reader.lines().toList();

        for (String t : line) {
          LinkedHashMap<String, Double> ingredients = new LinkedHashMap<>();
          //split with delimiter " | " to get product name and quantity
          List<String> input = List.of(t.split("\\s+\\|\\s+"));
          productList.put(input.get(0), Double.parseDouble(input.get(1)));
        }
      }
      return productList;
    }
  }
}