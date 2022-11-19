package com.server.server.service.product;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.server.server.model.Product;
import com.server.server.repository.ProductRepository;

@Service
public class ProductService implements IProductService {

    @Autowired
    ProductRepository pRepo;

    @Override
    public ArrayList<Product> findProductByRefAndDate(String ref, String date) {
        return pRepo.findProductByRefAndDate(ref, date);
    }

    @Override
    public ArrayList<Product> findProductByRefAndBrand(String ref, String brand) {
        return pRepo.findProductByRefAndBrand(ref, brand);
    }
}
