package ru.korchinskiy.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "product")
@Data
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String author;
    private Double cost;
    private Integer amount;
    private String description;
    private Date date;

    @Column(name = "md_photo")
    private String photoMd;

    @Column(name = "sm_photo")
    private String photoSm;

    @ManyToMany
    @JoinTable(
            name = "product_category",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private List<Category> categories;

    public Product(String title, String author, Double cost, Integer amount, String description, Date date, String photoMd, String photoSm) {
        this.title = title;
        this.author = author;
        this.cost = cost;
        this.amount = amount;
        this.description = description;
        this.date = date;
        this.photoMd = photoMd;
        this.photoSm = photoSm;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id.equals(product.id) &&
                title.equals(product.title) &&
                Objects.equals(amount, product.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", cost=" + cost +
                ", amount=" + amount +
                '}';
    }
}
