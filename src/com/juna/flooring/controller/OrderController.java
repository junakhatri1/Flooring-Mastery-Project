package com.juna.flooring.controller;

import java.io.FileNotFoundException;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.Set;


import com.juna.flooring.dao.OrderDAOImpl;
import com.juna.flooring.dao.ProductDAO;
import com.juna.flooring.dao.TaxesDAO;
import com.juna.flooring.model.Order;
import com.juna.flooring.model.Product;
import com.juna.flooring.model.Taxes;
import com.juna.flooring.ui.Prompt;


public class OrderController {
	private Prompt consoleIo = new Prompt();
	private OrderDAOImpl orderDAO = new OrderDAOImpl();
	private OrderCostCalculations costCalculation = new OrderCostCalculations();
	private ProductDAO productDAO = new ProductDAO();
	private TaxesDAO taxesDAO = new TaxesDAO();
	Product product = new Product();
	Taxes taxes = new Taxes();
	boolean changeOrder = false;
	public void run(){
		try {
			consoleIo.givenString("Welcome to the Flooring Program of SWC Corp" );
			productDAO.loadProduct();
			taxesDAO.loadTaxes();
			orderDAO.modeLoader();
			orderDAO.orderLoader();


			boolean condition = true;
			while(condition){
				menu();

				int userMenuOption = consoleIo.readValue("Please select numbers from 1 to 6 ", 1, 6);
				switch (userMenuOption) {
				case 1:
					consoleIo.givenString("Displaying Orders:");
					displayOrders();
					break;

				case 2:
					consoleIo.givenString("Adding Orders:");
					addOrders();
					break;

				case 3:
					consoleIo.givenString("Editing an Order:");
					editOrders();
					break;

				case 4:
					consoleIo.givenString("Removing an Order:");
					removeOrder();
					break;

				case 5:
					consoleIo.givenString("Saving an Order:");
					saveOrder();
					break;

				case 6:
					if(changeOrder== true){
						int userResponse = consoleIo.readValue("DO you want to save your order? Pres 1 for 'yes' or 2 for 'no'", 1, 2);
						String responseInString = "";
						if(userResponse ==1){
							saveOrder();
							consoleIo.givenString("Exiting......... ");
							consoleIo.givenString("Thanks for using SWC Corp Flooring Program");
						}
						else if(userResponse ==2){
							consoleIo.givenString("Exiting......... ");
							consoleIo.givenString("Thanks for using SWC Corp Flooring Program");
						}
						else if(responseInString ==Integer.toString(userResponse)){
							consoleIo.givenString("Your input is invalid");
						}
					}
					else{
						consoleIo.givenString("Exiting....");
						consoleIo.givenString("Thanks for using SWC Corp Flooring Program");
					}
					condition = false;
					break;

				default:
					consoleIo.givenString("Invalid input. Please select from the given range of numbers");
					break;
				}

			}

		} catch (FileNotFoundException e) {
			consoleIo.givenString("Product file wasnot found");

		}	
	}



	public void menu(){
		consoleIo.givenString("Here are your menu Options");
		consoleIo.givenString("");
		consoleIo.givenString("1. Display Orders");
		consoleIo.givenString("2. Add an Order");
		consoleIo.givenString("3. Edit an Order");
		consoleIo.givenString("4. Remove an Order");
		consoleIo.givenString("5. Save Current Work");
		consoleIo.givenString("6. Exit");

	}


	public void displayOrders(){	

		Set<String> uniqueDates = getUniqueDates();
		String[] uniqueDatesArray = uniqueDates.toArray(new String[uniqueDates.size()]);
		consoleIo.givenString("Date");
		for(int i=1;i<=uniqueDatesArray.length;i++){
			consoleIo.givenString(i+")"+ uniqueDatesArray[i-1]);
		}

		int inputDateOption = consoleIo.readValue("Please choose the number for the order date you would like to view.",1, uniqueDatesArray.length);
		String orderDateToGetOrders = "";
		for(int i =1; i<=uniqueDatesArray.length; i++){
			if(inputDateOption == i)
				orderDateToGetOrders = uniqueDatesArray[i-1];
		}
		List<Order> ordersByDate = orderDAO.getOrdersByDate(orderDateToGetOrders);
		consoleIo.givenString( "   Order No:      Customer Name:   Product Type: " );
		for(int i =0; i< ordersByDate.size(); i++){
			consoleIo.givenString( i+1 + ": " + ordersByDate.get(i).getOrderNumber() +"         "  +ordersByDate.get(i).getCustomerName() + "             " + ordersByDate.get(i).getProductType());

		}
		int orderNumberOption = consoleIo.readValue("Please choose the given number to view orders ", 1, ordersByDate.size());
		String orderNumberToGetOrder = "";
		for(int i =1; i<=ordersByDate.size(); i++){
			if(orderNumberOption == i){
				orderNumberToGetOrder = ordersByDate.get(i-1).getOrderNumber();
			}
		}
		Order order = orderDAO.getOrders(orderNumberToGetOrder);
		consoleIo.givenString("Order No: "+ order.getOrderNumber());
		consoleIo.givenString("Customer Name: " + order.getCustomerName());
		consoleIo.givenString("Product Type: " + order.getProductType());
		consoleIo.givenString("Product Area: " + order.getProductArea()+ "Sq Foot");
		consoleIo.givenString("State: "+ order.getState());
		consoleIo.givenString("Tax Rate: " + order.getTaxRate()+ "%");
		consoleIo.givenString("LaborCost: " + order.getLaborCost()+ "$");
		consoleIo.givenString("Material Cost " + order.getMaterialCost() + "$");
		consoleIo.givenString("Total Cost: " + order.getTotalCost() + "$");
		consoleIo.givenString("");
	}


	public void addOrders(){
		Order order = new Order();
		Date today = new Date();
		SimpleDateFormat myformat = new SimpleDateFormat("MMddYYYY");
		order.setDate(myformat.format(today));

			String name =  consoleIo.readString("Please enter the customer's name");
			order.setCustomerName(name);

		ArrayList<Taxes> taxList = taxesDAO.getAllTaxes();
		consoleIo.printInSameLine("State: ");
		for(int i =0; i<taxList.size(); i++){
			consoleIo.printInSameLine(i+1 + ":"+ taxList.get(i).getState() + "     " );
		}
		consoleIo.givenString("");
		consoleIo.printInSameLine("Tax :  ");

		for(int i =0; i<taxList.size(); i++){
			consoleIo.printInSameLine(taxList.get(i).getTaxRate() + "%" + "    ");
		}

		consoleIo.givenString("");
		int userStateOption = consoleIo.readValue("Please enter the initials of your state", 1, taxList.size());
		String userState = "";
		for(int i =0; i< taxList.size(); i++){
			if(userStateOption == i+1){
				userState = taxList.get(i).getState();

			}
		}

		order.setState(userState);
		double tax = taxesDAO.getTaxRateByState(userState);
		order.setTaxRate(tax);
		consoleIo.givenString("Tax Rate for the state " + order.getState()+ " is " + order.getTaxRate()+ "%");

		ArrayList<Product> productList = productDAO.getAllProducts();
		consoleIo.givenString("Product Type: ");
		for(int i=0; i< productList.size(); i++){
			consoleIo.printInSameLine(i+1  + ": "+ productList.get(i).getProductType() + "   ");
		}
		consoleIo.givenString("");
		int userProductTypeOption = consoleIo.readValue("Please enter the number for the product Type", 1, productList.size());

		String productType ="";
		for(int i =0; i< productList.size(); i++){
			if(userProductTypeOption == i+1){
				productType =productList.get(i).getProductType();
				break;
			}
		}
		order.setProductType(productType);
		double costPerSqFoot = productDAO.getCostByProductType(productType);
		double laborCostPerSqFoot = productDAO.getLaborCostByProductType(productType);

		order.setCostPerSqFoot(costPerSqFoot);
		order.setLaborCostPerSqFoot(laborCostPerSqFoot);
		consoleIo.givenString("The Cost perSqFoot for the "+ order.getProductType() +  " is "+ order.getCostPerSqFoot()+ "$" );
		consoleIo.givenString("The Laborcost perSqFoot for the "+ order.getProductType() +  " is "+ order.getLaborCostPerSqFoot() + "$");


		boolean validProductArea = true;
		while(validProductArea){
			try{
				String productArea = consoleIo.readString("Please enter the productArea in sq foot");
				
				Double.parseDouble(productArea);
				order.setProductArea(Double.parseDouble(productArea));
				validProductArea = false;
				
				
			}catch(NumberFormatException e){
				
			} 
		} 
		
	
		

		double materialCost = costCalculation.getMaterialCost(order.getProductArea(), order.getCostPerSqFoot());
		order.setMaterialCost(materialCost);

		double laborCost = costCalculation.getLaborCost(order.getProductArea(), order.getLaborCostPerSqFoot());
		order.setLaborCost(laborCost);

		double totalCostBeforeTax = order.getMaterialCost() + order.getLaborCost();

		double totalTax = costCalculation.getTax(order.getTaxRate(), totalCostBeforeTax);
		order.setTotalTax(totalTax);

		double totalCost = costCalculation.getTotalCost(order.getMaterialCost(), order.getLaborCost(), order.getTotalTax());
		order.setTotalCost(totalCost);

		boolean addingOrderResponse = true;
		while(addingOrderResponse){
			String userResponse = consoleIo.readString("Do you want to add this order to the list? Enter 'yes'  or 'no'");
			if(userResponse.equalsIgnoreCase("yes")){
				orderDAO.addOrder(order);
				consoleIo.givenString("New Order Added.");
				addingOrderResponse = false;
			}
			else if(userResponse.equalsIgnoreCase("no")){
				consoleIo.givenString("No order is added to the list. Going back to the main menu");
				addingOrderResponse = false;
			}
			else{
				consoleIo.givenString("Your input is invalid");
			}
		}
		changeOrder =true;

	}


	public void editOrders(){

		Set<String> uniqueDates = getUniqueDates();
		String[] uniqueDatesArray = uniqueDates.toArray(new String[uniqueDates.size()]);
		consoleIo.givenString("Date");
		for(int i=1;i<=uniqueDatesArray.length;i++){
			consoleIo.givenString(i+")"+ uniqueDatesArray[i-1]);
		}

		int inputDateOption = consoleIo.readValue("Please choose the number for the order date you would like to edit.",1, uniqueDatesArray.length);
		String orderDateToGetOrder = "";
		for(int i =0; i<uniqueDatesArray.length; i++){
			if(inputDateOption == i+1)
				orderDateToGetOrder = uniqueDatesArray[i];
		}

		List<Order> ordersByDate = orderDAO.getOrdersByDate(orderDateToGetOrder);
		consoleIo.givenString( "   Order No:      Customer Name:   Product Type: " );
		for(int i =0; i< ordersByDate.size(); i++){
			consoleIo.givenString( i+1 + ": " + ordersByDate.get(i).getOrderNumber() +"         "  +ordersByDate.get(i).getCustomerName() + "             " + ordersByDate.get(i).getProductType());

		}
		int orderNumberOption = consoleIo.readValue("Please choose from the given number to edit orders ", 1, ordersByDate.size());
		String orderNumberToEditOrder = "";
		for(int i =0; i<=ordersByDate.size(); i++){
			if(orderNumberOption == i+1){
				orderNumberToEditOrder = ordersByDate.get(i).getOrderNumber();
			}
		}
		Order order = orderDAO.getOrders(orderNumberToEditOrder);
		consoleIo.givenString("Order No: "+ order.getOrderNumber());
		consoleIo.givenString("Customer Name: " + order.getCustomerName());
		consoleIo.givenString("Product Type: " + order.getProductType());
		consoleIo.givenString("Product Area: " + order.getProductArea()+ "Sq Foot");
		consoleIo.givenString("State: "+ order.getState());
		consoleIo.givenString("Total Cost: " + order.getTotalCost() + "$");
		consoleIo.givenString("");


		String name = consoleIo.readString("Please enter the customer's name:  " + "(" +order.getCustomerName()+ ")");
		if(!name.isEmpty()){
			order.setCustomerName(name);
		}else{
			order.setCustomerName(order.getCustomerName());
		}

		ArrayList<Taxes> taxList = taxesDAO.getAllTaxes();
		consoleIo.printInSameLine("State: ");
		for(int i =0; i<taxList.size(); i++){
			consoleIo.printInSameLine("" + taxList.get(i).getState() + "        " );
		}
		consoleIo.givenString("");
		consoleIo.printInSameLine("Tax:  ");

		for(int i =0; i<taxList.size(); i++){
			consoleIo.printInSameLine(taxList.get(i).getTaxRate() + "%" + "     ");
		}
		consoleIo.givenString("");
		boolean condition = true;
		while(condition){
			String state= consoleIo.readString("Please enter the state from the given options:  " +"(" +order.getState()+ ")");
			if(state.isEmpty()) {
				order.setState(order.getState());
				condition = false;
				break;
			}

			else if(!state.isEmpty()){
				for(int i =0; i< taxList.size(); i++){
					if(state.equalsIgnoreCase(taxList.get(i).getState())){
						order.setState(state);
						double tax = taxesDAO.getTaxRateByState(state);
						order.setTaxRate(tax);
						condition = false;
					}
				}

			} else{
				consoleIo.givenString("Your input is invalid");	
			}

		}



		ArrayList<Product> productList = productDAO.getAllProducts();
		consoleIo.givenString("Product Type: ");
		for(int i=0; i< productList.size(); i++){
			consoleIo.printInSameLine(  productList.get(i).getProductType() + "   ");
		}
		consoleIo.givenString("");

		boolean askProductType = true;
		while(askProductType){
			String productType = consoleIo.readString("Please select  the product type from the given options:  " +"("+order.getProductType()+")");

			if(productType.isEmpty()){
				order.setProductType(order.getProductType());
				askProductType = false;
			}

			else if(!productType.isEmpty()){
				for(int i = 0; i <productList.size(); i++){
					if(productType.equalsIgnoreCase(productList.get(i).getProductType())){
						order.setProductType(productType);
						double costPerSqFoot = productDAO.getCostByProductType(productType);
						double laborCostPerSqFoot = productDAO.getLaborCostByProductType(productType);
						order.setCostPerSqFoot(costPerSqFoot);
						order.setLaborCostPerSqFoot(laborCostPerSqFoot);
						askProductType = false;
					}
				}

			}
			else{
				consoleIo.givenString("Your input is invalid");
			}
		}

		boolean isProductArea = true;
		while(isProductArea){
			consoleIo.givenString("Enter the product Area in sq Foot:  " + "("+order.getProductArea()+")");
			Scanner sc = new Scanner(System.in);
			if(sc.hasNextLine()){

				order.setProductArea(order.getProductArea());
				isProductArea = false;

			}
			else if(sc.hasNextDouble()){
				order.setProductArea(sc.nextDouble());
				isProductArea = false;

			}

			else{

				consoleIo.givenString("Your input is invalid");
			}
		}
		
		


		double materialCost = costCalculation.getMaterialCost(order.getProductArea(), order.getCostPerSqFoot());
		order.setMaterialCost(materialCost);

		double laborCost = costCalculation.getLaborCost(order.getProductArea(), order.getLaborCostPerSqFoot());
		order.setLaborCost(laborCost);

		double totalCostBeforeTax = order.getMaterialCost() + order.getLaborCost();

		double tax = costCalculation.getTax(order.getTaxRate(), totalCostBeforeTax);
		order.setTotalTax(tax);

		double totalCost = costCalculation.getTotalCost(order.getMaterialCost(), order.getLaborCost(), order.getTotalTax());
		order.setTotalCost(totalCost);

		orderDAO.editOrder(order);
		consoleIo.givenString("The Order is edited. Your updated order is :  ");
		consoleIo.givenString("Order No: "+ order.getOrderNumber());
		consoleIo.givenString("Customer Name: " + order.getCustomerName());
		consoleIo.givenString("Product Type: " + order.getProductType());
		consoleIo.givenString("Product Area: " + order.getProductArea()+ "Sq Foot");
		consoleIo.givenString("State: "+ order.getState());
		consoleIo.givenString("Tax Rate: " + order.getTaxRate()+ "%");
		consoleIo.givenString("LaborCost: " + order.getLaborCost()+ "$");
		consoleIo.givenString("Material Cost " + order.getMaterialCost() + "$");
		consoleIo.givenString("Total Cost: " + order.getTotalCost() + "$");
		consoleIo.givenString("");
		changeOrder = true;
	}


	public void removeOrder(){

		Set<String> uniqueDates = getUniqueDates();
		String[] uniqueDatesArray = uniqueDates.toArray(new String[uniqueDates.size()]);
		consoleIo.givenString("Date");
		for(int i=1;i<=uniqueDatesArray.length;i++){
			consoleIo.givenString(i+")"+ uniqueDatesArray[i-1]);
		}

		int inputDateOption = consoleIo.readValue("Please choose the number for the order date you would like to delete.",1, uniqueDatesArray.length);
		String orderDateToGetOrder = "";
		for(int i =0; i<uniqueDatesArray.length; i++){
			if(inputDateOption == i+1)
				orderDateToGetOrder = uniqueDatesArray[i];

		}

		List<Order> ordersByDate = orderDAO.getOrdersByDate(orderDateToGetOrder);
		consoleIo.givenString( "   Order No:      Customer Name:   Product Type: " );
		for(int i =0; i< ordersByDate.size(); i++){
			consoleIo.givenString( i+1 + ": " + ordersByDate.get(i).getOrderNumber() +"         "  +ordersByDate.get(i).getCustomerName() + "             " + ordersByDate.get(i).getProductType());

		}

		int orderNumberOption = consoleIo.readValue("Please choose the given number to delete orders ", 1, ordersByDate.size());
		String orderNumberToDeleteOrder = "";
		for(int i =1; i<=ordersByDate.size(); i++){
			if(orderNumberOption == i){
				orderNumberToDeleteOrder = ordersByDate.get(i-1).getOrderNumber();
			}
		}

		Order order = orderDAO.getOrders(orderNumberToDeleteOrder);
		consoleIo.givenString("Order No: "+ order.getOrderNumber());
		consoleIo.givenString("Customer Name: " + order.getCustomerName());
		consoleIo.givenString("Product Type: " + order.getProductType());
		consoleIo.givenString("Total Cost: " + order.getTotalCost() + "$");
		consoleIo.givenString("");

		String userAnswer =	consoleIo.readString("Are you sure you want to delete this? Enter 'yes' or 'no' ");
		if(userAnswer.equalsIgnoreCase("yes")){
			orderDAO.removeOrder(orderNumberToDeleteOrder);
			consoleIo.givenString("Order with " + orderNumberToDeleteOrder + " order number is deleted");

		}
		else if(userAnswer.equalsIgnoreCase("no")){
			consoleIo.givenString("Order is not deleted");
		}
		else{
			consoleIo.givenString("Your input is invalid" );
		}
		changeOrder = true;
	}

	public void saveOrder(){
		orderDAO.orderWriter();
		consoleIo.givenString("Your order is saved in a file");


	}


	public String  getDate(String orderNumber){
		String date = orderNumber.substring(0, 8);		
		return date;
	}


	public Set<String> getUniqueDates(){
		Set<String> uniqueDates = new HashSet<>();

		String[] orderId = orderDAO.getAllOrderIds();

		for(int i =0; i< orderId.length; i++){
			String 	 date = getDate(orderId[i]);
			uniqueDates.add(date);
		}
		return uniqueDates;
	}





}
