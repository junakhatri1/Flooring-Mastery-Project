package com.juna.flooring.controller;



public class OrderCostCalculations {

	public double getMaterialCost(double productArea, double costPerSqFoot){
		double materialCost = productArea * costPerSqFoot;
		return Math.round(materialCost*100)/100D;

	}

	public double getLaborCost(double productArea, double laborCostPerSqFoot){
		double laborCost = productArea *laborCostPerSqFoot;
		return Math.round(laborCost*100)/100D;

	}

	public double getTax(double taxRate, double costBeforeTax){
		double tax = taxRate * costBeforeTax;
		return Math.round(tax*100)/100D;

	}
	public double getTotalCost(double materialCost, double laborCost, double tax){
		double totalCost= materialCost + laborCost + tax;
		return Math.round(totalCost *100)/100D;
	}
}
