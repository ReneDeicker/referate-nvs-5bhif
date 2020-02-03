package at.htl.model;


import java.time.LocalDate;

public class Cashier extends Person {
    private Long id;

    private LocalDate begin_date;
    private double salary;

    private Store store;

    //@OneToOne
    //private Person person;

    public Cashier() {
    }

    public Cashier(String firstname, String lastname, LocalDate birthdate, String mobilePhone, String email,
                   LocalDate begin_date, double salary, Store store) {
        super(firstname, lastname, birthdate, mobilePhone, email);
        this.begin_date = begin_date;
        this.salary = salary;
        this.store = store;
        //this.person = person;
    }

    public Long getId() {
        return id;
    }

    public LocalDate getBegin_date() {
        return begin_date;
    }

    public void setBegin_date(LocalDate begin_date) {
        this.begin_date = begin_date;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }
}
