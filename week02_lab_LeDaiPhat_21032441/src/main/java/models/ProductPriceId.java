package models;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Objects;

@Embeddable
public class ProductPriceId implements Serializable {
    private static final long serialVersionUID = 2679308155622362486L;
    @NotNull
    @Column(name = "price_date_time", nullable = false)
    private LocalDateTime priceDateTime;

    @NotNull
    @Column(name = "product_id", nullable = false)
    private Long productId;

    public LocalDateTime getPriceDateTime() {
        return priceDateTime;
    }

    public void setPriceDateTime(LocalDateTime priceDateTime) {
        this.priceDateTime = priceDateTime;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ProductPriceId entity = (ProductPriceId) o;
        return Objects.equals(this.priceDateTime, entity.priceDateTime) &&
                Objects.equals(this.productId, entity.productId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(priceDateTime, productId);
    }

}