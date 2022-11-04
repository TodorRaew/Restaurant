package firstRealProject.classResourses.objects;

import java.util.LinkedHashMap;
import java.util.Map;

public class Storage
{
  private LinkedHashMap<String, Double> products;

  public Storage() {
    this.products = new LinkedHashMap<>();
  }

  public LinkedHashMap<String, Double> getProducts()
  {
    return products;
  }

  public void setProducts(LinkedHashMap<String, Double> products)
  {
    this.products = products;
  }
}