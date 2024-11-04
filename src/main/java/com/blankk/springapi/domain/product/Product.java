package com.blankk.springapi.domain.product;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="product")
@Entity(name="product")
@EqualsAndHashCode(of = "id")
public class Product {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private Float price;

    public Product(ProductDTO productDTO) {
        this.name = productDTO.name();
        this.price = productDTO.price();
    }
}
