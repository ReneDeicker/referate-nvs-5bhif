package at.htl.model;

import java.time.LocalDateTime;

public class Activity {

    private Long id;

    private LocalDateTime time;
    private int cashDesk;

    private Product product;

    private Cashier cashier;

    private Customer customer;

    private Store store;

    public Activity() {
    }

    public Activity(LocalDateTime time, int cashDesk, Product product, Cashier cashier, Customer customer, Store store) {
        this.time = time;
        this.cashDesk = cashDesk;
        this.product = product;
        this.cashier = cashier;
        this.customer = customer;
        this.store = store;
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public int getCashDesk() {
        return cashDesk;
    }

    public void setCashDesk(int cashDesk) {
        this.cashDesk = cashDesk;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Cashier getCashier() {
        return cashier;
    }

    public void setCashier(Cashier cashier) {
        this.cashier = cashier;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }
}
