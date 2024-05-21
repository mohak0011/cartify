package com.mohak.cartify.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Category extends BaseModel {

    private String title;
    @OneToMany(mappedBy = "category")
    @JsonIgnoreProperties("category")
    List<Product> products;
}
