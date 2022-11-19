package com.client.client.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;
import lombok.NoArgsConstructor;

@XmlRootElement
@Data
@NoArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
public class Products {
    private String code;

    @XmlElement(name = "product")
    private ArrayList<Product> listProduct = new ArrayList<Product>();

    public Products(String c, ArrayList<Product> arr) {
        super();

        code = c;
        listProduct = arr;
    }

    public List<Product> getProducts() {
        return listProduct;
    }

    public String getCode() {
        return code;
    }
}
