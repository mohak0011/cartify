package com.mohak.cartify.services.ServicesImpl;

import com.mohak.cartify.dtos.ProductDto;
import com.mohak.cartify.exceptions.ProductNotFoundException;
import com.mohak.cartify.models.Category;
import com.mohak.cartify.models.Product;
import com.mohak.cartify.repositories.CategoryRepository;
import com.mohak.cartify.repositories.ProductRepository;
import com.mohak.cartify.services.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

   private  final ModelMapper modelMapper;
    private  final ProductRepository productrepo;
    private  final CategoryRepository categoryrepo;

    public ProductServiceImpl(ProductRepository productrepo, CategoryRepository categoryrepo,
                              ModelMapper modelMapper)
    {
     this.productrepo = productrepo;
     this.categoryrepo = categoryrepo;
     this.modelMapper = modelMapper;
    }


    @Override
    public ProductDto getProductById(Long productId) {
        Product product = this.productrepo.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Product", "productId", productId));
        return this.producttodto(product);
    }


      public List<ProductDto> getProducts()
      {
        List<Product>  products  = this.productrepo.findAll();
        List<ProductDto> productdtos = products.stream().map(product-> this.producttodto(product)).collect(Collectors.toList());
        return  productdtos;
      }

      public ProductDto addProduct(ProductDto productDto) {
        Product product = this.dtotoproduct(productDto);
          Category categoryfromDb = categoryrepo.findByTitle(productDto.getCategory());

                  if(categoryfromDb==null)
                  {
                      Category newcategory = new Category();
                      newcategory.setTitle(productDto.getCategory());
                      categoryfromDb = categoryrepo.save(newcategory);

                  }
                  product.setCategory(categoryfromDb);
              Product savedProduct = this.productrepo.save(product);
                 return producttodto(savedProduct);
      }

       public ProductDto deleteProduct(Long productId)
        {
            Product product = this.productrepo.findById(productId)
                    .orElseThrow(() -> new ProductNotFoundException("Product","productId",productId));

            this.productrepo.delete(product);
            return this.producttodto(product);
        }

    @Override
    public ProductDto updateProduct(ProductDto productDto, Long productId) {

        Product product = this.productrepo.findById(productId)
                .orElseThrow(()-> new ProductNotFoundException("Product","ProductId",productId));
        product.setTitle(productDto.getTitle());
        product.setPrice(productDto.getPrice());
        product.setDescription(productDto.getDescription());
        product.setImageUrl(productDto.getImageUrl());

        Category category = categoryrepo.findByTitle(productDto.getCategory());
        if (category == null) {
            category = new Category();
            category.setTitle(productDto.getCategory());
            category = categoryrepo.save(category);
        }
        product.setCategory(category);
        Product updatedProduct = this.productrepo.save(product);
        ProductDto productDto1 = this.producttodto(updatedProduct);
        return  productDto1;

    }

    public List<ProductDto> getCategoryByCategory(String category) {
        Category categorytitle = this.categoryrepo.findByTitle(category);
        List<Product> products = this.productrepo.findByCategory(categorytitle);
        List<ProductDto> productDtos = new ArrayList<>();
        for (Product product : products) {
            productDtos.add(producttodto(product));
        }
        return productDtos;
    }

  public  List<String> GetCategories()
  {
      List<Category> categories = this.categoryrepo.findAll();
      return  categories.stream().map(category -> category.getTitle()).collect(Collectors.toList());
  }



    public Product dtotoproduct(ProductDto productDto) {

        Product product = this.modelMapper.map(productDto, Product.class);
        return  product;
    }


    public ProductDto producttodto(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setTitle(product.getTitle());
        productDto.setDescription(product.getDescription());
        productDto.setPrice(product.getPrice());
        productDto.setImageUrl(product.getImageUrl());

        if (product.getCategory() != null) {
            productDto.setCategory(product.getCategory().getTitle());
        } else {
            productDto.setCategory(null);
        }

        return productDto;
    }
}





