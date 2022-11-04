package firstRealProject.classResourses.objects;

public class Dish
{
  private boolean isClean;
  private boolean isEmpty;

  public Dish() {
    this.isClean = true;
    this.isEmpty = true;
  }

  public boolean isEmpty() {
    return isEmpty;
  }

  public void setEmpty(boolean empty) {
    isEmpty = empty;
  }

  public boolean isClean()
  {
    return isClean;
  }

  public void setClean(boolean clean)
  {
    isClean = clean;
  }
}