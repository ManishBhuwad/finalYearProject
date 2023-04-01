package com.User_food_Pannel;

public class Cart {
    private String MessId,DishID,DishName,DishQuantity,Price,Totalprice;

    public Cart(String messId, String dishID, String dishName, String dishQuantity, String price, String totalprice) {
        MessId = messId;
        DishID = dishID;
        DishName = dishName;
        DishQuantity = dishQuantity;
        Price = price;
        Totalprice = totalprice;
    }

    public Cart() {
    }

    public String getMessId() {
        return MessId;
    }

    public void setMessId(String messId) {
        MessId = messId;
    }

    public String getDishID() {
        return DishID;
    }

    public void setDishID(String dishID) {
        DishID = dishID;
    }

    public String getDishName() {
        return DishName;
    }

    public void setDishName(String dishName) {
        DishName = dishName;
    }

    public String getDishQuantity() {
        return DishQuantity;
    }

    public void setDishQuantity(String dishQuantity) {
        DishQuantity = dishQuantity;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getTotalprice() {
        return Totalprice;
    }

    public void setTotalprice(String totalprice) {
        Totalprice = totalprice;
    }
}
