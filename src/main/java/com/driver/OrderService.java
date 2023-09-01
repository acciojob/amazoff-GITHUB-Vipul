package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

@Autowired
static OrderRepository orderrepository=new OrderRepository();

    public  DeliveryPartner getPartnerById(String partnerId) {
        return orderrepository.getPartnerById(partnerId);
    }


    public void addOrder(Order order) {
    orderrepository.addOrder(order);
    }

    public void addPartner(String partnerId) {
      orderrepository.addPartner(partnerId);
    }


    public Order getOrderById(String orderId) {
        return orderrepository.getOrderById(orderId);
    }

    public void addOrderPartnerPair(String orderId, String partnerId) {
        orderrepository.addOrderPartnerPair(orderId,partnerId);
    }

    public Integer getOrderCountPartnerId(String partnerId) {
        return orderrepository.getOrderCountPartnerId(partnerId);
    }

    public List<String> getAllOrders() {
        return orderrepository.getAllOrders();
    }

    public List<String> getOrderByPartnerId(String partnerId) {
        return orderrepository.getOrderByPartnerId(partnerId);
    }

    public Integer getCountOfUnassignedOrders() {
        return orderrepository.getCountOfUnassignedOrders();
    }

    public Integer getOrdersLeftAfterGivenTimeByPartnerId(String time, String partnerId) {
        return orderrepository.getOrdersLeftAfterGivenTimeByPartnerId(time,partnerId);
    }

    public String getLastDeliveryTimeByPartnerId(String partnerId) {
        return orderrepository.getLastDeliveryTimeByPartnerId(partnerId);
    }

    public void deletePartnerById(String partnerId) {
        orderrepository.deletePartnerById(partnerId);
    }

    public void deleteOrderById(String orderId) {
        orderrepository.deleteOrderById(orderId);
    }
}
