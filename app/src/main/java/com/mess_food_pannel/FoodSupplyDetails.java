package com.mess_food_pannel;

public class FoodSupplyDetails {
    public String Dishes,Quantity,Price,Description,ImageURL,RandomUID,MessId;

    public FoodSupplyDetails(String dishes, String quantity, String price, String description, String imageURL,String randomUID,String messId) {
        Dishes = dishes;
        Quantity = quantity;
        Price = price;
        Description = description;
        ImageURL = imageURL;
        RandomUID=randomUID;
        MessId=messId;
    }
}
