package com.example.Mobilerecharge.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Mobilerecharge.model.RechargeModel;
import com.example.Mobilerecharge.service.RechargeService;

import jakarta.validation.Valid;

// marks this class as a rest API
@RestController
@RequestMapping("/recharges")
public class RechargeController {

	private final RechargeService service;

// Spring creates the service object and passes it in through the constructor. This is called Constructor-based Dependency Injection.
	public RechargeController(RechargeService service) {
		this.service = service;
	}

// POST method handles post the data
	@PostMapping
//@RequestBody converts the JSON body into a Recharge object.
	public ResponseEntity<RechargeModel> doRecharge(@Valid @RequestBody RechargeModel recharge) {
		RechargeModel saved = service.doRecharge(recharge);
		return ResponseEntity.status(HttpStatus.CREATED).body(saved);
	}

// GET method returns a list of data
	@GetMapping
	public List<RechargeModel> getAllRecharges() {
		return service.getAllRecharges();
	}

// GET by Id
	@GetMapping("/{id}")
	public RechargeModel getRechargeById(@PathVariable("id") int id) {
		return service.getRechargeById(id);
	}

// Update
	@PutMapping("/{id}")
	public RechargeModel updateRecharge(@PathVariable("id") int id, @Valid @RequestBody RechargeModel recharge) {
		return service.updateRecharge(id, recharge);
	}

// DELETE
	@DeleteMapping("/{id}")
	public String deleteRecharge(@PathVariable("id") int id) {
		service.deleteRecharge(id);
		return "Recharge deleted successfully with id: " + id;
	}
}