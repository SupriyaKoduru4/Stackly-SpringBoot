package com.example.Foodordersystem.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Foodordersystem.model.FoodOrder;
import com.example.Foodordersystem.service.FoodOrderService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/orders")
public class FoodOrderController {

	    private final FoodOrderService service;

	    public FoodOrderController(FoodOrderService service) {
	        this.service = service;
	    }

	    // get all orders
	    @GetMapping
	    public List<FoodOrder> getAllOrders() {
	        return service.getAllOrders();
	    }

	    // get by id
	    @GetMapping("/{id}")
	    public FoodOrder getOrderById(@PathVariable int id) {
	        return service.getOrderById(id);
	    }

	    //post order
	    @PostMapping
	    public ResponseEntity<FoodOrder> addOrder(@Valid @RequestBody FoodOrder order) {
	        FoodOrder saved = service.addOrder(order);
	        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
	    }

	    // update orders
	    @PutMapping("/{id}")
	    public FoodOrder updateOrder(@PathVariable int id, @Valid @RequestBody FoodOrder order) {
	        return service.updateOrder(id, order);
	    }

	    // delete order
	    @DeleteMapping("/{id}")
	    public String deleteOrder(@PathVariable int id) {
	        service.deleteOrder(id);
	        return "Order deleted successfully with id: " + id;
	    }

	    // partial update
	    @PatchMapping("/{id}")
	    public FoodOrder patchOrder(@PathVariable int id, @RequestBody Map<String, Object> updates) {
	        return service.patchOrder(id, updates);
	    }

}
