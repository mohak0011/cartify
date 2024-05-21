package com.mohak.cartify.repositories.projections;

import com.mohak.cartify.models.Category;

import java.math.BigDecimal;

public interface ProductProjections {
    Long getId();
    String getTitle();
    String getDescription();
    BigDecimal getPrice();
    Category getCategory();
    String getImageUrl();
}
