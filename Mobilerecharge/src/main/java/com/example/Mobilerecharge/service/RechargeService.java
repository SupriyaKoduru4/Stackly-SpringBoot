package com.example.Mobilerecharge.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.Mobilerecharge.exception.ResourceNotFoundException;
import com.example.Mobilerecharge.model.RechargeModel;

// @Service tells Spring this is the business logic layer.Spring creates one object a Bean of it and injects it into the controller.
@Service
public class RechargeService {

	private final Map<Integer, RechargeModel> recharges = new LinkedHashMap<>();
	private int nextId = 1;

// POST -> new recharges
	public RechargeModel doRecharge(RechargeModel recharge) {
// nextId++ is post increment it uses the current value first and then add 1
		recharge.setRechargeId(nextId++);
// put save the recharges in the map.
		recharges.put(recharge.getRechargeId(), recharge);
		return recharge;
	}

	
// GET all
	public List<RechargeModel> getAllRecharges() {
		return new ArrayList<>(recharges.values());
	}


// GET by ID
	public RechargeModel getRechargeById(int id) {
		RechargeModel recharge = recharges.get(id);
		if (recharge == null) {
			throw new ResourceNotFoundException("Recharge not found with id: " + id);
		}
		return recharge;
	}

// update
	public RechargeModel updateRecharge(int id, RechargeModel newRecharge) {
		getRechargeById(id);
// sets the id from the url on the new object
		newRecharge.setRechargeId(id);
// put with an existing key replaces the old value, so the whole recharges is replaced.
		recharges.put(id, newRecharge);
		return newRecharge;
	}

	
// DELETE
	public void deleteRecharge(int id) {
		getRechargeById(id);
		recharges.remove(id);
	}
}