package firstRealProject.classResourses.models;

import java.math.BigDecimal;

public abstract class Staff extends Person
{
  private BigDecimal salary;

  public Staff(String firstName, String lastName, int age, BigDecimal salary)
  {
    super(firstName, lastName, age);
    this.salary = salary;
  }


  public BigDecimal getSalary()
  {
    return salary;
  }

  public void setSalary(BigDecimal salary)
  {
    this.salary = salary;
  }
}