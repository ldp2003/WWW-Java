package models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "product_price", schema = "mydb_week2")
public class ProductPrice {
    @EmbeddedId
    private ProductPriceId id;

    @MapsId("productId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Size(max = 255)
    @Column(name = "note")
    private String note;

    @Column(name = "price")
    private Double price;

    public ProductPriceId getId() {
        return id;
    }

    public void setId(ProductPriceId id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

}