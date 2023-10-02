package com.trendyol.shipment;

import java.util.List;

public class Basket {

    private List<Product> products;

    public ShipmentSize getShipmentSize() {
        if (products == null || products.isEmpty()) {
            return null;
    }

        int productCount = products.size();
        int[] sizeCounts = new int[ShipmentSize.values().length];

        for (Product product : products) {
            ShipmentSize size = product.getSize();
            sizeCounts[size.ordinal()]++;
        }

        if (sizeCounts[ShipmentSize.X_LARGE.ordinal()] == productCount) {
            return ShipmentSize.X_LARGE;
        }

        if (productCount < 3) {
            return findLargestSize(sizeCounts);
        }

        for (ShipmentSize size : ShipmentSize.values()) {
            if (sizeCounts[size.ordinal()] >= 3) {
                return getOneUpperSize(size);
            }
        }

        return findLargestSize(sizeCounts);
    }

    private ShipmentSize findLargestSize(int[] sizeCounts) {
        int largestSizeIndex = -1;
        for (int i = 0; i < sizeCounts.length; i++) {
            if (sizeCounts[i] > 0) {
                largestSizeIndex = i;
            }
        }
        return ShipmentSize.values()[largestSizeIndex];
    }

    private ShipmentSize getOneUpperSize(ShipmentSize size) {
        switch (size) {
            case SMALL:
                return ShipmentSize.MEDIUM;
            case MEDIUM:
                return ShipmentSize.LARGE;
            case LARGE:
                return ShipmentSize.X_LARGE;
            default:
                return size;
        }
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
