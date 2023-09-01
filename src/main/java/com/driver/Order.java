package com.driver;

public class Order {

    private String id;
    private int deliveryTime;
    private String stringDeliveryTime;

    public Order(String id, String deliveryTime) {

        // The deliveryTime has to converted from string to int and then stored in the attribute
        //deliveryTime  = HH*60 + MM
        this.stringDeliveryTime=deliveryTime;
        String[] temp = deliveryTime.split(":");
        this.id = id;
        this.deliveryTime = (Integer.parseInt(temp[0])*60)+Integer.parseInt(temp[1]);
    }

    public String getStringDeliveryTime() {
        return stringDeliveryTime;
    }

    public void setStringDeliveryTime(String stringDeliveryTime) {
        this.stringDeliveryTime = stringDeliveryTime;
    }

    public String getId() {
        return id;
    }

    public int getDeliveryTime() {return deliveryTime;}


}
