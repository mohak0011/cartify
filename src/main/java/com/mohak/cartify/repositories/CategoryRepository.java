package com.mohak.cartify.repositories;

import com.mohak.cartify.dtos.ProductDto;
import com.mohak.cartify.models.Category;
import com.mohak.cartify.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category,Long> {

    Category save(Category category);
    Category findByTitle(String name);

}
