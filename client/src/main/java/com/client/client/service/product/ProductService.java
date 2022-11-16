package com.client.client.service.product;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.client.client.model.Product;
import com.client.client.repository.ProductRepository;

@Service
public class ProductService implements IProductService {

    @Autowired
    ProductRepository pRepo;

    @Override
    public ArrayList<Product> findProductByRefAndDate(String ref, String date) {
        return pRepo.findProductByRefAndDate(ref, date);
    }
}
