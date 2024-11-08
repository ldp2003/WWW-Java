package models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "customer", schema = "mydb_week2")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cust_id", nullable = false)
    private Long id;

    @Size(max = 250)
    @Column(name = "address", length = 250)
    private String address;

    @Size(max = 150)
    @Column(name = "email", length = 150)
    private String email;

    @Size(max = 150)
    @Column(name = "cust_name", length = 150)
    private String custName;

    @Size(max = 15)
    @Column(name = "phone", length = 15)
    private String phone;

    @OneToMany(mappedBy = "cust")
    private Set<Order> orders = new LinkedHashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

}