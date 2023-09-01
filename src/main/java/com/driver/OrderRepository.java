package com.driver;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
@Repository
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

    public Integer getOrdersLeftAfterGivenTimeByPartnerId(int total, String partnerId) {

        Integer count = 0;
        if(OrdersPerDeliveryPartner.containsKey(partnerId)){
            List<String> or = OrdersPerDeliveryPartner.get(partnerId);
            for(String x:or){
                Order o = Orders.get(x);
                if(o.getDeliveryTime()>total){
                    count++;
                }
            }
        }

        return count;
    }

    public String getLastDeliveryTimeByPartnerId(String partnerId) {
        String time="00:00";
        int max=0;
        if(OrdersPerDeliveryPartner.containsKey(partnerId))
        {
            for(String order:OrdersPerDeliveryPartner.get(partnerId))
            {
                if(max<Orders.get(order).getDeliveryTime())
                {
                    max=Orders.get(order).getDeliveryTime();
                    time=Orders.get(order).getStringDeliveryTime();
                }
            }
        }

        return time;
    }

    public void deletePartnerById(String partnerId) {
       if(DeliveryPartners.containsKey(partnerId))
       {
           DeliveryPartners.remove(partnerId);
       }
       if(OrdersPerDeliveryPartner.containsKey(partnerId))
       {
           List<String> orders=OrdersPerDeliveryPartner.get(partnerId);
           OrdersPerDeliveryPartner.remove(partnerId);
           for(String order:orders)
           {
               if(Orders.containsKey(order))
               {
                   OrderPartnerPairs.remove(order);
               }
           }
       }
    }

    public void deleteOrderById(String orderId) {
        if(Orders.containsKey(orderId)){
            Orders.remove(orderId);
        }
        if(OrderPartnerPairs.containsKey(orderId)){
            String temp = OrderPartnerPairs.get((orderId));
            OrderPartnerPairs.remove(orderId);
            OrdersPerDeliveryPartner.get(temp).remove(orderId);
            DeliveryPartner dp=DeliveryPartners.get(temp);
            dp.setNumberOfOrders(dp.getNumberOfOrders()-1);
        }
    }
}
