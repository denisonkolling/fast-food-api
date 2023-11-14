package com.fastfood.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "PRODUCTS")
public class Product {

    @Id
    private Integer number;

    @Enumerated(EnumType.STRING)
    private Category category;

    @Column(length = 200, nullable = false)
    private String name;

    private Float price;


    public Product(Integer number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return String.format("Cod: %d - Categoria: %s - Desc: %s - Preço: R$ %.2f",
                number, category, name, price);
    }
}
