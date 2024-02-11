package com.sapient.productCatalogue.service;

import com.sapient.productCatalogue.model.Product;
import com.sapient.productCatalogue.model.Seller;
import com.sapient.productCatalogue.repository.ProductRepository;
import com.sapient.productCatalogue.repository.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepo;

    @Autowired
    private SellerRepository sellerRepo;

    public Product saveProduct(Product product) {
        return productRepo.save(product);
    }

    public long getProductQuantity(String sellerName, int productId) {
        List<Seller> seller = sellerRepo.findBySellerNameAndProductId(sellerName, productId);
        long count = 0;
        count = seller.stream().map(s -> s.getQuantity()).reduce(0, Integer::sum);
        return count;
    }

    public long getInventory(int productId) {
        long count = 0;
        List<Seller> seller = sellerRepo.findByProductId(productId);
        count = seller.stream().map(s -> s.getQuantity()).reduce(0, Integer::sum);
        return count;
    }

    public List<Product> getProductByBrand(String brand) {
        return productRepo.findByBrand(brand);
    }

    public List<Product> getProductByColor(String color) {
        return productRepo.findByColor(color);
    }

    public List<Product> getProductBySize(Integer size) {
        return productRepo.findBySize(size);
    }

    public List<Product> getProductBySku(String sku) {
        return productRepo.findBySku(sku);
    }

    public List<Product> getProductByPrice(Double price) {
        return productRepo.findByPrice(price);
    }

    public void deleteProduct(Integer id) {
        productRepo.deleteById(id);
    }
}
