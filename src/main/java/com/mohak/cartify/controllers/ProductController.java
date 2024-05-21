package com.mohak.cartify.controllers;

import com.mohak.cartify.dtos.ProductDto;
import com.mohak.cartify.models.Product;
import com.mohak.cartify.services.ServicesImpl.ProductServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
public class ProductController {

    private ProductServiceImpl productService;

    public ProductController(ProductServiceImpl productService)
    {
        this.productService = productService;
    }


    @GetMapping("/products/{id}")
    public ResponseEntity<ProductDto> getProduct(@PathVariable Long id) {

        ProductDto productDtos = this.productService.getProductById(id);
      return new  ResponseEntity<>(productDtos,HttpStatus.OK);
    }

    @GetMapping("/products")
    public  ResponseEntity<List<ProductDto>> getAllProducts() {

        List<ProductDto> proudctdtos = this.productService.getProducts();
        return  new ResponseEntity<>(proudctdtos,HttpStatus.OK);
    }

      @PostMapping("/products")
      public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto productDto)
      {
         ProductDto createdProduct = productService.addProduct(productDto);
         return  new ResponseEntity<>(createdProduct,HttpStatus.CREATED);
      }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<ProductDto> deleteProduct(@PathVariable Long id) {

        ProductDto deletedProduct = productService.deleteProduct(id);
        return new  ResponseEntity<>(deletedProduct,HttpStatus.OK);
    }

    @PutMapping("/products/{id}")
    public  ResponseEntity<ProductDto> updateProduct(@Valid @RequestBody ProductDto productDto,@PathVariable("id")Long id)
    {
      ProductDto updatedProduct = this.productService.updateProduct(productDto,id);
      return new ResponseEntity<>(updatedProduct,HttpStatus.OK);
    }


    @GetMapping("/products/category/{category}")
    public ResponseEntity<List<ProductDto>> getProductsByCategory(@PathVariable String category) {
        List<ProductDto> product = productService.getCategoryByCategory(category);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @GetMapping("/products/categories")
    public  ResponseEntity<List<String>> GetCategories()
    {
        List<String> categories = this.productService.GetCategories();
        return  new ResponseEntity<>(categories,HttpStatus.OK);
    }



}
