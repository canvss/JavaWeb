package canvs.pojo;

import java.sql.Date;

public class Customer {
    private int id;
    private String name;
    private String email;
    private Date birth;
    private double salary;

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public Customer(String name, String email, Date birth, double salary) {
        this.name = name;
        this.email = email;
        this.birth = birth;
        this.salary = salary;
    }

    public Customer(int id, String name, String email, Date birth, double salary) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.birth = birth;
        this.salary = salary;
    }

    public Customer() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    @Override
    public String toString() {
        return "Customer [id=" + id + ", name=" + name + ", email=" + email + ", birth=" + birth + ", salary="+salary+"]";
    }
}
