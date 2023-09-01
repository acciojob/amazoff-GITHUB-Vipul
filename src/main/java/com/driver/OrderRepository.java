package com.driver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
public class OrderRepository {
    HashMap<String,Order> Orders=new HashMap<>();
    HashMap<String,DeliveryPartner> DeliveryPartners=new HashMap<>();

    HashMap<String,String> OrderPartnerPairs=new HashMap<>();

    HashMap<String,List<String>> OrdersPerDeliveryPartner=new HashMap<>();
    public void addOrder(Order order) {

        Orders.put(order.getId(),order);
    }

    public void addPartner(String partnerId) {
        DeliveryPartners.put(partnerId, new DeliveryPartner(partnerId));
    }

    public Order getOrderById(String orderId) {
        return Orders.get(orderId);
    }

    public DeliveryPartner getPartnerById(String partnerId) {
        return DeliveryPartners.get(partnerId);
    }

    public void addOrderPartnerPair(String orderId, String partnerId) {
        if(!OrderPartnerPairs.containsKey(orderId))
        {
            OrderPartnerPairs.put(orderId,partnerId);
            DeliveryPartners.get(partnerId).setNumberOfOrders(DeliveryPartners.get(partnerId).getNumberOfOrders()+1);
            List<String> orders=new ArrayList<>();
            orders.add(orderId);
            OrdersPerDeliveryPartner.put(partnerId,orders);
        }
        else {
            DeliveryPartners.get(partnerId).setNumberOfOrders(DeliveryPartners.get(partnerId).getNumberOfOrders()+1);
            OrdersPerDeliveryPartner.get(partnerId).add(orderId);
        }

    }


    public Integer getOrderCountPartnerId(String partnerId) {
        return DeliveryPartners.get(partnerId).getNumberOfOrders();
    }

    public List<String> getAllOrders() {
        return new ArrayList<>(Orders.keySet());
    }

    public List<String> getOrderByPartnerId(String partnerId) {
        return OrdersPerDeliveryPartner.get(partnerId);
    }

    public Integer getCountOfUnassignedOrders() {
        Integer count=0;
        for(String order:Orders.keySet())
        {
            if(!OrderPartnerPairs.containsKey(order))
            {
                count++;
            }
        }
        return count;
    }

    public Integer getOrdersLeftAfterGivenTimeByPartnerId(String time, String partnerId) {
        System.out.println(time);
        return 0;
    }
}
