package models;

import enums.EmployeeStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "employee", schema = "mydb_week2")
@NamedQueries({
        @NamedQuery(name = "Employee.findAll", query = "select e from Employee e where e.status = :Status"),
        @NamedQuery(name = "Employee.findById", query = "select e from Employee e where e.id = :id")
})
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "emp_id", nullable = false)
    private Long id;

    @Size(max = 250)
    @Column(name = "address", length = 250)
    private String address;

    @Column(name = "dob")
    private LocalDateTime dob;

    @Size(max = 150)
    @Column(name = "email", length = 150)
    private String email;

    @Size(max = 150)
    @Column(name = "full_name", length = 150)
    private String fullName;

    @Size(max = 15)
    @Column(name = "phone", length = 15)
    private String phone;

    @Column(name = "status")
    private EmployeeStatus status;

    @OneToMany(mappedBy = "employee")
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

    public LocalDateTime getDob() {
        return dob;
    }

    public void setDob(LocalDateTime dob) {
        this.dob = dob;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public EmployeeStatus getStatus() {
        return status;
    }

    public void setStatus(EmployeeStatus status) {
        this.status = status;
    }

}