package com.example.Foodordersystem.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.Foodordersystem.exception.ResourceNotFoundException;
import com.example.Foodordersystem.model.FoodOrder;

@Service
public class FoodOrderService {
	
	private final Map<Integer, FoodOrder> orders = new LinkedHashMap<>();
    private int nextId = 1;
    
    // get all orders
    public List<FoodOrder> getAllOrders() {
        return new ArrayList<>(orders.values());
    }
    
    // get by id
    public FoodOrder getOrderById(int id) {
        FoodOrder order = orders.get(id);
        if (order == null) {
            throw new ResourceNotFoundException("Order not found with id: " + id);
        }
        return order;
    }
    
    // post 
    public FoodOrder addOrder(FoodOrder order) {
        order.setOrderId(nextId++);
        orders.put(order.getOrderId(), order);
        return order;
    }
    
    // put
    public FoodOrder updateOrder(int id, FoodOrder newOrder) {
        getOrderById(id);
        newOrder.setOrderId(id);
        orders.put(id, newOrder);
        return newOrder;
    }
    
    // delete
    public void deleteOrder(int id) {
        getOrderById(id);
        orders.remove(id);
    }
    
    // patch
    public FoodOrder patchOrder(int id, Map<String, Object> updates) {
        FoodOrder existing = getOrderById(id);

        for (Map.Entry<String, Object> entry : updates.entrySet()) {
            String field = entry.getKey();
            Object value = entry.getValue();

            switch (field) {
                case "customerName":
                    if (value == null || value.toString().isBlank())
                        throw new IllegalArgumentException("Customer name is required");
                    existing.setCustomerName(value.toString());
                    break;

                case "foodItem":
                    if (value == null || value.toString().isBlank())
                        throw new IllegalArgumentException("Food item cannot be empty");
                    existing.setFoodItem(value.toString());
                    break;

                case "quantity":
                    if (!(value instanceof Number) || ((Number) value).intValue() < 1)
                        throw new IllegalArgumentException("Quantity must be at least 1");
                    existing.setQuantity(((Number) value).intValue());
                    break;

                case "price":
                    if (!(value instanceof Number))
                        throw new IllegalArgumentException("Price is mandatory");
                    existing.setPrice(((Number) value).doubleValue());
                    break;

                default:
                    throw new IllegalArgumentException("Invalid field: " + field);
            }
        }
        return existing;
    }
   

}
