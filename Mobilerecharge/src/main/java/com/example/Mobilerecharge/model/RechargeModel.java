package com.example.Mobilerecharge.model;

//imports bring in the validation annotations
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class RechargeModel {

// unique ID for each recharges.
	private Integer rechargeId;

	@NotBlank(message = "Mobile number is required")
	@Pattern(regexp = "[0-9]{10}", message = "Mobile number must contain 10 digits")
	private String mobileNumber;

	@NotBlank(message = "Operator is required")
	private String operator;

	@Min(value = 10, message = "Minimum recharge amount is 10")
	private int amount;

// plan type 
	private String planType;

	public RechargeModel() {
	}

	public Integer getRechargeId() {
		return rechargeId;
	}

	public void setRechargeId(Integer rechargeId) {
		this.rechargeId = rechargeId;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getPlanType() {
		return planType;
	}

	public void setPlanType(String planType) {
		this.planType = planType;
	}
}