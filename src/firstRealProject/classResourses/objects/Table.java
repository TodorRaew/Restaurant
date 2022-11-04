package firstRealProject.classResourses.objects;

import java.util.ArrayList;
import java.util.List;

public class Table
{
  private        List<Client> clients;
  private        int          seats;
  private        boolean      isFree;
  private final  int          tableNumber;
  private static int          counter = 0;

  public Table()
  {
    counter++;
    tableNumber = counter;

    this.clients = new ArrayList<>();
    this.seats=4;
    this.isFree = true;
  }

  public List<Client> getClients()
  {
    return clients;
  }

  public void setClients(List<Client> clients)
  {
    this.clients = clients;
  }

  public int getSeats()
  {
    return seats;
  }
  
  public boolean isFree()
  {
    return isFree;
  }

  public void setFree(boolean free)
  {
    isFree = free;
  }

  @Override
  public String toString()
  {
    return "Table{" +
        "clients=" + clients +
        ", seats=" + seats +
        ", isFree=" + isFree +
        ", tableNumber=" + tableNumber + "\n" +
        '}';
  }
}