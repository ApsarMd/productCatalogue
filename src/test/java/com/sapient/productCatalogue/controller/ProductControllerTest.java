package com.sapient.productCatalogue.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sapient.productCatalogue.model.Product;
import com.sapient.productCatalogue.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(SpringExtension.class)
@WebMvcTest(value = ProductController.class)
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;
    Product product = new Product();
    private String productExpectedResponse;
    private String productExpectedResponseList;

    @BeforeEach
    public void init() {
        product.setBrand("HP");
        product.setProductId(1);
        product.setProductName("Laptop X23uy");
        product.setColor("Blue");
        product.setSize(15);
        product.setPrice(43000);
        product.setSku("sku1");
        productExpectedResponse = asJsonString(product);
        productExpectedResponseList = "[" + productExpectedResponse + "]";
    }

    @Test
    public void when_saveProduct_return_success() throws Exception {
        Mockito.when(productService.saveProduct(Mockito.any(Product.class))).thenReturn(product);

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/product/save")
                .content(asJsonString(product))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        System.out.println(result.getResponse().getContentAsString());
        JSONAssert.assertEquals(productExpectedResponse, result.getResponse().getContentAsString(), true);
    }

    @Test
    public void when_getQuantityBySeller_return_products() throws Exception {
        Mockito.when(productService.getProductQuantity(Mockito.anyString(), Mockito.anyInt())).thenReturn(1L);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.
                get("/product/abc/1").accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        System.out.println(result.getResponse().getContentAsString());
        JSONAssert.assertEquals("{\"count\" : " + 1 + "}", result.getResponse().getContentAsString(), true);
    }

    @Test
    public void when_getInventory_return_count() throws Exception {
        Mockito.when(productService.getInventory(Mockito.anyInt())).thenReturn(1L);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.
                get("/product/inventory/1").accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        System.out.println(result.getResponse().getContentAsString());

        JSONAssert.assertEquals("{\"count\" : " + 1 + "}", result.getResponse().getContentAsString(), true);
    }

    public String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            final String jsonContent = mapper.writeValueAsString(obj);
            return jsonContent;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void when_getProductByBrand_return_products() throws Exception {
        List<Product> productList = new ArrayList<>();
        productList.add(product);
        Mockito.when(productService.getProductByBrand(Mockito.anyString())).thenReturn(productList);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.
                get("/product/brand/HP").accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        System.out.println(result.getResponse().getContentAsString());

        JSONAssert.assertEquals(productExpectedResponseList, result.getResponse().getContentAsString(), true);
    }

    @Test
    public void when_getProductByColor_return_products() throws Exception {
        List<Product> productList = new ArrayList<>();
        productList.add(product);
        Mockito.when(productService.getProductByColor(Mockito.anyString())).thenReturn(productList);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.
                get("/product/color/Blue").accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        System.out.println(result.getResponse().getContentAsString());

        JSONAssert.assertEquals(productExpectedResponseList, result.getResponse().getContentAsString(), true);
    }

    @Test
    public void when_getProductBySize_return_products() throws Exception {
        List<Product> productList = new ArrayList<>();
        productList.add(product);
        Mockito.when(productService.getProductBySize(Mockito.anyInt())).thenReturn(productList);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.
                get("/product/size/1").accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        System.out.println(result.getResponse().getContentAsString());

        JSONAssert.assertEquals(productExpectedResponseList, result.getResponse().getContentAsString(), true);
    }

    @Test
    public void when_getProductByPrice_return_products() throws Exception {
        List<Product> productList = new ArrayList<>();
        productList.add(product);
        Mockito.when(productService.getProductByPrice(Mockito.anyDouble())).thenReturn(productList);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.
                get("/product/price/43000").accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        System.out.println(result.getResponse().getContentAsString());

        JSONAssert.assertEquals(productExpectedResponseList, result.getResponse().getContentAsString(), true);
    }

    @Test
    public void when_getProductBySku_return_products() throws Exception {
        List<Product> productList = new ArrayList<>();
        productList.add(product);
        Mockito.when(productService.getProductBySku(Mockito.anyString())).thenReturn(productList);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.
                get("/product/sku/sku1").accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        System.out.println(result.getResponse().getContentAsString());

        JSONAssert.assertEquals(productExpectedResponseList, result.getResponse().getContentAsString(), true);
    }

    @Test
    public void when_deleteProduct_return_Success() throws Exception {
        Mockito.doNothing().when(productService).deleteProduct(Mockito.anyInt());
        RequestBuilder requestBuilder = MockMvcRequestBuilders.
                delete("/product/delete/1").accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        System.out.println(result.getResponse().getContentAsString());

        JSONAssert.assertEquals("{\"result\": \"Product Deleted Successfully\"}", result.getResponse().getContentAsString(), true);
    }

}
