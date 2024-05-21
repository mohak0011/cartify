package com.mohak.cartify.dtos;

import com.mohak.cartify.models.BaseModel;
import com.mohak.cartify.models.Category;
import com.mohak.cartify.models.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
@NoArgsConstructor


public class ProductDto  {

    private  Long id;
    private String title;
    private double price;
    private String category;
    private String description;
    private String imageUrl;




}
