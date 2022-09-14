package com.example.orchestrator.model;

public class Product {

    private Long id;

    private String name;

    private int price;

    private String category;

    private String subCategory;

    private String material;

    private String size;

    private int rate;

    public Product(){}

    public Product(String name, int price, String category, String subCategory, String material, String size, int rate) {
        this.name = name;
        this.price = price;
        this.category = category;
        this.subCategory = subCategory;
        this.material = material;
        this.size = size;
        this.rate = rate;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", category='" + category + '\'' +
                ", subCategory='" + subCategory + '\'' +
                ", material='" + material + '\'' +
                ", size='" + size + '\'' +
                ", rate=" + rate +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(String subCategory) {
        this.subCategory = subCategory;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }
}
