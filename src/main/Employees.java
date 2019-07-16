package main;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "employees")
public class Employees implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id")
    private int employee_id;

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    @Column(name = "age")
    private int age;

    @Column(name = "salary")
    private double salary;


    public void setEmployee_id(int employee_id) {
        this.employee_id = employee_id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public int getEmployee_id() {
        return employee_id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public int getAge() {
        return age;
    }

    public double getSalary() {
        return salary;
    }

    public Employees() {
    }

    public Employees(String name, String address, int age, double salary) {
        this.name = name;
        this.address = address;
        this.age = age;
        this.salary = salary;
    }
    
    @Override
    public String toString() {
        return "ID:" + employee_id + "\n" + name + " ," + address + " ," + age + " ," + salary;

    }

}
