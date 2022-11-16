package com.client.client.service.product;

import java.util.ArrayList;

import com.client.client.model.Product;

public interface IProductService {
    ArrayList<Product> findProductByRefAndDate(String ref, String date);
}
