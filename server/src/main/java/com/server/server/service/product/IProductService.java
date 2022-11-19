package com.server.server.service.product;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.server.server.model.Product;

@Service
public interface IProductService {
    ArrayList<Product> findProductByRefAndDate(String ref, String date);

    ArrayList<Product> findProductByRefAndBrand(String ref, String brand);
}
