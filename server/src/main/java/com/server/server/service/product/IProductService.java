package com.server.server.service.product;

import java.util.ArrayList;

import com.server.server.model.Product;

public interface IProductService {
    ArrayList<Product> findProductByRefAndDate(String ref, String date);
}
