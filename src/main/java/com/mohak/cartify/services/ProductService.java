package com.mohak.cartify.services;

import com.mohak.cartify.dtos.ProductDto;
import com.mohak.cartify.models.Product;

import java.util.List;

public interface ProductService
{
    ProductDto getProductById(Long productId);
    List<ProductDto> getProducts();
    ProductDto addProduct(ProductDto product);
    ProductDto deleteProduct(Long productId);
    List<ProductDto> getCategoryByCategory(String category);
    ProductDto updateProduct(ProductDto productDto, Long productId);
    List<String> GetCategories();


}
