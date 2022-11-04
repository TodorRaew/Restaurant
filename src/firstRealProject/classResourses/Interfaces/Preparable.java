package firstRealProject.classResourses.Interfaces;

import firstRealProject.classResourses.objects.Client;
import firstRealProject.classResourses.objects.Restaurant;

public interface Preparable
{
  void prepareDish(Client client, Restaurant restaurant);
  void getProductsFromStorage(Client client, Restaurant restaurant);
}