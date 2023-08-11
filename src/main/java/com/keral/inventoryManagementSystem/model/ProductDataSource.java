package com.keral.inventoryManagementSystem.model;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

import java.util.List;

public class ProductDataSource implements JRDataSource {

    private List<Product> products;
    private int index = -1;

    public ProductDataSource(List<Product> products) {
        this.products = products;
    }

    @Override
    public boolean next() throws JRException {
        index++;
        return index < products.size();
    }

    @Override
    public Object getFieldValue(JRField field) throws JRException {
        if (field.getName().equals("product_id")) {
            return products.get(index).getProductId();
        } else if (field.getName().equals("product_name")) {
            return products.get(index).getProductName();
        }
        // Handle other fields similarly
        return null;
    }
}
