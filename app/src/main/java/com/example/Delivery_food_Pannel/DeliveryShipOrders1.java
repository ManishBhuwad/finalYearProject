package com.example.Delivery_food_Pannel;

public class DeliveryShipOrders1 {

    private String Address, MessId, MessName, GrandTotalPrice, MobileNumber, Name, RandomUID, Status, UserId;

    public DeliveryShipOrders1(String address, String messId, String messName, String grandTotalPrice, String mobileNumber, String name, String randomUID, String status, String userId) {
        Address = address;
        MessId = messId;
        MessName = messName;
        GrandTotalPrice = grandTotalPrice;
        MobileNumber = mobileNumber;
        Name = name;
        RandomUID = randomUID;
        Status = status;
        UserId = userId;
    }

    public DeliveryShipOrders1() {
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getMessId() {
        return MessId;
    }

    public void setMessId(String messId) {
        MessId = messId;
    }

    public String getMessName() {
        return MessName;
    }

    public void setMessName(String messName) {
        MessName = messName;
    }

    public String getGrandTotalPrice() {
        return GrandTotalPrice;
    }

    public void setGrandTotalPrice(String grandTotalPrice) {
        GrandTotalPrice = grandTotalPrice;
    }

    public String getMobileNumber() {
        return MobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        MobileNumber = mobileNumber;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getRandomUID() {
        return RandomUID;
    }

    public void setRandomUID(String randomUID) {
        RandomUID = randomUID;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }
}
