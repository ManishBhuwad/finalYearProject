package com.mess_food_pannel;

public class UpdateDishModel {

    String Dishes,RandomUID,Description,Quantity,Price,ImageURL,MessId;

    public UpdateDishModel(String dishes, String randomUID, String description, String quantity, String price, String imageURL, String messId) {
        Dishes = dishes;
        RandomUID = randomUID;
        Description = description;
        Quantity = quantity;
        Price = price;
        ImageURL = imageURL;
        MessId = messId;
    }

    public UpdateDishModel()
    {

    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getQuantity() {
        return Quantity;
    }

    public void setQuantity(String quantity) {
        Quantity = quantity;
    }

    public String getImageURL() {
        return ImageURL;
    }

    public void setImageURL(String imageURL) {
        ImageURL = imageURL;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getRandomUID() {

        return RandomUID;
    }

    public void setRandomUID(String randomUID) {

        RandomUID = randomUID;
    }

    public String getDishes()
    {
        return Dishes;
    }

    public void setDishes(String dishes) {

        Dishes = dishes;
    }


    public String getMessId() {
        return MessId;
    }

    public void setMessId(String messId) {
        MessId = messId;
    }
}
