package com.sapient.productCatalogue.service;

import com.sapient.productCatalogue.model.Product;
import com.sapient.productCatalogue.model.Seller;
import com.sapient.productCatalogue.repository.ProductRepository;
import com.sapient.productCatalogue.repository.SellerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.Extensions;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class ProductServiceTest {

    @InjectMocks
    private ProductService productService;
    
    @Mock
    private ProductRepository productRepo;

    @Mock
    private SellerRepository sellerRepo;

    Product product = new Product();

    Seller seller1 = new Seller();
    List<Product> productList = new ArrayList<>();

    @BeforeEach
    public void init() {
//        MockitoAnnotations.initMocks(ProductService.class);
        product.setBrand("HP");
        product.setProductId(1);
        product.setProductName("Laptop X23uy");
        product.setColor("Blue");
        product.setSize(15);
        product.setPrice(43000);
        product.setSku("sku1");
        productList.add(product);

        seller1.setId(1);
        seller1.setSellerName("Soft tech pvt ltd");
        seller1.setProductId(1);
        seller1.setQuantity(5);
    }

    @Test
    public void when_saveProduct_return_product() {
        when(productRepo.save(Mockito.any())).thenReturn(product);
        Product saveProduct = productService.saveProduct(product);
        assertEquals(1, saveProduct.getProductId());
        assertEquals("HP", saveProduct.getBrand());
        assertEquals("Blue", saveProduct.getColor());
    }

    @Test
    public void when_getProductQuantity_return_count() {
        List<Seller> sellerList = new ArrayList<>();
        sellerList.add(seller1);
        when(sellerRepo.findBySellerNameAndProductId(anyString(), anyInt())).thenReturn(sellerList);
        long countOfQuantity = productService.getProductQuantity("abc", 1);
        assertEquals(5, countOfQuantity);
    }

    @Test
    public void getInventory() {
        List<Seller> sellerList = new ArrayList<>();
        sellerList.add(seller1);
        when(sellerRepo.findByProductId(anyInt())).thenReturn(sellerList);
        long inventoryCount = productService.getInventory(1);
        assertEquals(5, inventoryCount);
    }
    
    @Test
    public void when_getProductByBrand_return_brand() {
        when(productRepo.findByBrand(anyString())).thenReturn(productList);
        List<Product> products = productService.getProductByBrand("HP");
        assertEquals(1, products.size());

    }

    @Test
    public void when_getProductByColor_return_brand() {
        when(productRepo.findByColor(anyString())).thenReturn(productList);
        List<Product> products = productService.getProductByColor("Blue");
        assertEquals(1, products.size());

    }

    @Test
    public void when_getProductBySize_return_brand() {
        when(productRepo.findBySize(anyInt())).thenReturn(productList);
        List<Product> products = productService.getProductBySize(15);
        assertEquals(1, products.size());

    }
    @Test
    public void when_getProductBySku_return_brand() {
        when(productRepo.findBySku(anyString())).thenReturn(productList);
        List<Product> products = productService.getProductBySku("sku1");
        assertEquals(1, products.size());

    }

    @Test
    public void when_getProductByPrice_return_brand() {
        when(productRepo.findByPrice(Mockito.anyDouble())).thenReturn(productList);
        List<Product> products = productService.getProductByPrice(43000d);
        assertEquals(1, products.size());

    }

}