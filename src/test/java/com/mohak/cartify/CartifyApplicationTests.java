package com.mohak.cartify;

import com.mohak.cartify.models.Product;
import com.mohak.cartify.repositories.CategoryRepository;
import com.mohak.cartify.repositories.ProductRepository;
import com.mohak.cartify.repositories.projections.ProductProjections;
import com.mohak.cartify.repositories.projections.ProductWithIdAndTitle;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class CartifyApplicationTests {

	@Autowired
	private ProductRepository productrepo;

	@Autowired
	private CategoryRepository categoryrepo;
	@Test
	void contextLoads() {
	}

	@Test
	void testDataJpadeclaredJoin()
	{
      List<Product> products = this.productrepo.findAllByCategory_Title("electronics");
	  for(Product product : products)
	  {
		  System.out.println(product.getTitle());
	  }
	}

	@Test
	void testHQL()
	{
		List<Product> products = this.productrepo.getProductWithCategoryName("jewelery");
		for(Product product : products)
		{
			System.out.println(product.getTitle());
			System.out.println(product.getCategory().getTitle());
			System.out.println(product.getPrice());
		}
	}

	@Test
	void testSpecificfields()
	{
		List<String> productTitles = this.productrepo.someTitleMethod("men's clothing");
		for(String productTitle : productTitles)
		{
			System.out.println(	productTitle);
		}
	}

	@Test
	void testProjections()
	{
		List<ProductWithIdAndTitle> products  = this.productrepo.someMethod1("men's clothing");
		for(ProductWithIdAndTitle product : products)
		{
			System.out.println(	product.getTitle());
			System.out.println(	product.getId());
		}

		List<ProductProjections> proudctProjections = this.productrepo.someMethod2("men's clothing");
		for(ProductProjections p : proudctProjections)
		{
			System.out.println(	p.getTitle());
			System.out.println(	p.getId());
			System.out.println(	p.getId());
		}
	}

	@Test
	void testNativeSql() {

		Product product = productrepo.someNativeSql(1L);
		System.out.println(product.getTitle());

	}
}
