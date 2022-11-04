package firstRealProject.classResourses.accsessories;

import java.util.List;

public class Menu
{
  private Drinks drinks;
  private Foods  foods;

  public Menu()
  {

  }

  public Drinks getDrinks()
  {
    return drinks;
  }

  public Foods getFoods()
  {
    return foods;
  }

  public void setDrinks(List<Item> hot, List<Item> cold)
  {
    this.drinks = new Drinks(hot, cold);
  }

  public void setFoods(List<Item> starter, List<Item> main, List<Item> desert)
  {
    this.foods = new Foods(starter, main, desert);
  }

  //Nested class
  public static class Drinks
  {
    private List<Item> hot;
    private List<Item> cold;

    public Drinks(List<Item> hot, List<Item> cold)
    {
      this.hot = hot;
      this.cold = cold;

      this.hot.forEach(item -> {
        item.setFoodType("hot");
      });

      this.cold.forEach(item -> {
        item.setFoodType("cold");
      });
    }

    public List<Item> getHot()
    {
      return hot;
    }

    public List<Item> getCold()
    {
      return cold;
    }
  }

  //Nested class
  public static class Foods
  {
    private List<Item> starter;
    private List<Item> main   ;
    private List<Item> desert ;

    public Foods(List<Item> starter, List<Item> main, List<Item> desert)
    {
      this.starter = starter;
      this.main = main;
      this.desert = desert;

      this.starter.forEach(item -> {
        item.setFoodType("starter");
      });

      this.main.forEach(item -> {
        item.setFoodType("main");
      });

      this.desert.forEach(item -> {
        item.setFoodType("desert");
      });
    }

    public List<Item> getStarter()
    {
      return starter;
    }

    public List<Item> getMain()
    {
      return main;
    }

    public List<Item> getDesert()
    {
      return desert;
    }
  }
}