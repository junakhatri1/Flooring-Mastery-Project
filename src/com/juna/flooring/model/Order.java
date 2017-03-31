package com.juna.flooring.model;



public class Order {
	private String date;
	private String orderNumber;
	private String customerName;
	private String state;
	private double taxRate;
	private String productType;
	private double productArea;
	private double costPerSqFoot;
	private double laborCostPerSqFoot;
	private double totalTax;
	private double materialCost;
	private double laborCost;
	private double totalCost;
	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public double getTaxRate() {
		return taxRate;
	}
	public void setTaxRate(double taxRate) {
		this.taxRate = taxRate;
	}
	public String getProductType() {
		return productType;
	}
	public void setProductType(String productType) {
		this.productType = productType;
	}
	public double getProductArea() {
		return productArea;
	}
	public void setProductArea(double productArea) {
		this.productArea = productArea;
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
	public double getTotalTax() {
		return totalTax;
	}
	public void setTotalTax(double totalTax) {
		this.totalTax = totalTax;
	}
	public double getMaterialCost() {
		return materialCost;
	}
	public void setMaterialCost(double materialCost) {
		this.materialCost = materialCost;
	}
	public double getLaborCost() {
		return laborCost;
	}
	public void setLaborCost(double laborCost) {
		this.laborCost = laborCost;
	}
	public double getTotalCost() {
		return totalCost;
	}
	public void setTotalCost(double totalCost) {
		this.totalCost = totalCost;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(costPerSqFoot);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((customerName == null) ? 0 : customerName.hashCode());
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		temp = Double.doubleToLongBits(laborCost);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(laborCostPerSqFoot);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(materialCost);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((orderNumber == null) ? 0 : orderNumber.hashCode());
		temp = Double.doubleToLongBits(productArea);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((productType == null) ? 0 : productType.hashCode());
		result = prime * result + ((state == null) ? 0 : state.hashCode());
		temp = Double.doubleToLongBits(taxRate);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(totalCost);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(totalTax);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		if (Double.doubleToLongBits(costPerSqFoot) != Double.doubleToLongBits(other.costPerSqFoot))
			return false;
		if (customerName == null) {
			if (other.customerName != null)
				return false;
		} else if (!customerName.equals(other.customerName))
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (Double.doubleToLongBits(laborCost) != Double.doubleToLongBits(other.laborCost))
			return false;
		if (Double.doubleToLongBits(laborCostPerSqFoot) != Double.doubleToLongBits(other.laborCostPerSqFoot))
			return false;
		if (Double.doubleToLongBits(materialCost) != Double.doubleToLongBits(other.materialCost))
			return false;
		if (orderNumber == null) {
			if (other.orderNumber != null)
				return false;
		} else if (!orderNumber.equals(other.orderNumber))
			return false;
		if (Double.doubleToLongBits(productArea) != Double.doubleToLongBits(other.productArea))
			return false;
		if (productType == null) {
			if (other.productType != null)
				return false;
		} else if (!productType.equals(other.productType))
			return false;
		if (state == null) {
			if (other.state != null)
				return false;
		} else if (!state.equals(other.state))
			return false;
		if (Double.doubleToLongBits(taxRate) != Double.doubleToLongBits(other.taxRate))
			return false;
		if (Double.doubleToLongBits(totalCost) != Double.doubleToLongBits(other.totalCost))
			return false;
		if (Double.doubleToLongBits(totalTax) != Double.doubleToLongBits(other.totalTax))
			return false;
		return true;
	}


}
