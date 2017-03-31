package com.juna.flooring.model;

public class Product {
	private String productType;
	private double costPerSqFoot;
	private double laborCostPerSqFoot;

	public String getProductType() {
		return productType;
	}
	public void setProductType(String productType) {
		this.productType = productType;
	}
	public double getCostPerSqFoot() {
		return costPerSqFoot;
	}
	public void setCostPerSqFoot(double costPerSqFoot) {
		this.costPerSqFoot = costPerSqFoot;
	}
	public double getLaborCostPerSqFoot() {
		return laborCostPerSqFoot;
	}
	public void setLaborCostPerSqFoot(double laborCostPerSqFoot) {
		this.laborCostPerSqFoot = laborCostPerSqFoot;
	}

}
