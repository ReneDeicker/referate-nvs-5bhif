package at.htl.supermarket.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NamedQueries({
        @NamedQuery(name = "Activity.getAll", query = "select a from Activity a"),
        @NamedQuery(name = "Activity.getByBrand", query = "select a from Activity a where a.product.brand = :brand"),
        @NamedQuery(name = "Activity.getByCustomerLastname", query = "select a from Activity a where a.customer.lastname = :name")
})
public class Activity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime time;
    private int cashDesk;

    @OneToOne
    private Product product;
    @OneToOne
    private Cashier cashier;
    @OneToOne
    private Customer customer;
    @OneToOne
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
