package firstRealProject.classResourses.accsessories;

import firstRealProject.classResourses.objects.Dish;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class Item
{
  private String                        name;
  private LinkedHashMap<String, Double> ingredients;
  private double                        weight;
  private BigDecimal                    price;
  private int                           counter;
  private Dish                          dish;
  private String                        foodType;

  public Item(String name, double weight, BigDecimal price, LinkedHashMap<String, Double> ingredients)
  {

    this.name = name;
    this.weight = weight;
    this.price = price;
    this.ingredients = ingredients;
  }

  public String getFoodType()
  {
    return foodType;
  }

  public void setFoodType(String foodType)
  {
    this.foodType = foodType;
  }

  public Dish getDish()
  {
    return dish;
  }

  public void setDish(Dish dish)
  {
    this.dish = dish;
  }

  public String getName()
  {
    return name;
  }

  public void setName(String name)
  {
    this.name = name;
  }

  public LinkedHashMap<String, Double> getIngredians()
  {
    return ingredients;
  }

  public void setIngredians(LinkedHashMap<String, Double> ingredians)
  {
    this.ingredients = ingredians;
  }

  public double getWeight()
  {
    return weight;
  }

  public void setWeight(double weight)
  {
    this.weight = weight;
  }

  public BigDecimal getPrice()
  {
    return price;
  }

  public void setPrice(BigDecimal price)
  {
    this.price = price;
  }

  public int getCounter()
  {
    return counter;
  }

  public void setCounter(int counter)
  {
    this.counter = counter;
  }

  @Override
  public String toString()
  {
    return "Item{" +
        "name='" + name + '\'' +
        ", counter=" + counter +
        '}';
  }
}