package com.juna.flooring.dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import com.juna.flooring.model.Order;
import com.juna.flooring.model.Product;

public class OrderDAOImpl implements OrderDAOInterface {
	private Map<String, Order> orderMap = new HashMap<>();
	Date date = new Date();
	SimpleDateFormat dateFormat = new SimpleDateFormat("MMddYYYY");
	private Integer orderNumber = 0;
	private String filePath = "C:\\Users\\JUNA\\Documents\\Programming\\WithPankaj\\guild\\guild\\OO concept\\Flooring Mastery Project\\";
	private static final String DELIMITER =",";
	String modeType ;

	@Override
	public Order addOrder(Order order) {
		order.setOrderNumber(dateFormat.format(date)+orderNumber);
		orderMap.put(order.getOrderNumber(), order);
		orderNumber++;
		return order;
	}

	@Override
	public Order editOrder(Order order) {
		String orderNumber = order.getOrderNumber();
		orderMap.put(orderNumber, order);
		return order;
	}

	@Override
	public Order removeOrder(String orderNumber) {
		Order order = orderMap.remove(orderNumber);
		return order;
	}

	@Override
	public void orderWriter() {
		if(modeType.equals( "prod")){

		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("MMddYYYY");
		String todayDate = dateFormat.format(date);
		ArrayList<String> uniqueDates = getDatesInList();
		
	try{
	for(int i =0; i < uniqueDates.size(); i++){
		if(!uniqueDates.get(i).equalsIgnoreCase(todayDate)){
			
	File fileName = new File( "Orders_" + uniqueDates.get(i)+ ".txt");
			PrintWriter writer = new PrintWriter(new FileWriter(fileName));
			String getDate = getDateByFileName(fileName);
		List<Order>order = getOrdersByDate(getDate);
			for(int j =0; j< order.size(); j++){
				writer.println(order.get(j).getDate()+ ","+order.get(j).getOrderNumber()+"," + order.get(j).getCustomerName()+ "," + order.get(j).getState()+ ","+order.get(j).getTaxRate()+"," 
							+order.get(j).getProductType()+ ","+ order.get(j).getProductArea()+","+ order.get(j).getCostPerSqFoot()+ "," +order.get(j).getLaborCostPerSqFoot()
							+"," + order.get(j).getMaterialCost() +"," +order.get(j).getLaborCost() +","+order.get(j).getTotalTax()+","+ order.get(j).getTotalCost());
					writer.flush();
				}
				writer.close();
		}	
			
			else{
				File newFile =new File( "Orders_" +todayDate+ ".txt");
				newFile.createNewFile();
				PrintWriter writer = new PrintWriter(new FileWriter(newFile));
				
				String getDate = getDateByFileName(newFile);
				List<Order> order = getOrdersByDate(getDate);
					
				for(int j =0; j<order.size(); j++){
					writer.println(order.get(j).getDate()+ ","+order.get(j).getOrderNumber()+"," + order.get(j).getCustomerName()+ "," + order.get(j).getState()+ ","+order.get(j).getTaxRate()+"," 
							+order.get(j).getProductType()+ ","+ order.get(j).getProductArea()+","+ order.get(j).getCostPerSqFoot()+ "," +order.get(j).getLaborCostPerSqFoot()
							+"," + order.get(j).getMaterialCost() +"," +order.get(j).getLaborCost() +","+order.get(j).getTotalTax()+","+ order.get(j).getTotalCost());
					writer.flush();
				}
				writer.close();
			}	
		}
		
		}catch(IOException e){
			e.printStackTrace();
		}
		}
	}
	

	@Override
	public void orderLoader() {

		try{
			File folder = new File(filePath);
			File[] listOfFiles = folder.listFiles();
			for(int i =0; i < listOfFiles.length; i++){
				if(listOfFiles[i].isFile()){
					String files = listOfFiles[i].getName();
					if(files.startsWith("Orders_" ) && files.endsWith(".txt")){


						Scanner sc  = new Scanner(new BufferedReader(new FileReader(listOfFiles[i])));
						String currentLine;
						String[] currentTokens;

						while(sc.hasNext()){
							currentLine = sc.nextLine();
							currentTokens = currentLine.split(DELIMITER);
							Order order = new Order();
							order.setDate(currentTokens[0]);
							order.setOrderNumber(currentTokens[1]);
							order.setCustomerName(currentTokens[2]);
							order.setState(currentTokens[3]);
							order.setTaxRate(Double.parseDouble(currentTokens[4]));
							order.setProductType(currentTokens[5]);
							order.setProductArea(Double.parseDouble(currentTokens[6]));
							order.setCostPerSqFoot(Double.parseDouble(currentTokens[7]));
							order.setLaborCostPerSqFoot(Double.parseDouble(currentTokens[8]));
							order.setMaterialCost(Double.parseDouble(currentTokens[9]));
							order.setLaborCost(Double.parseDouble(currentTokens[10]));
							order.setTotalTax(Double.parseDouble(currentTokens[11]));
							order.setTotalCost(Double.parseDouble(currentTokens[12]));
							orderMap.put(order.getOrderNumber(), order);

							Set<String> orderId = orderMap.keySet();
							ArrayList<String> idListInArray = new ArrayList<>();
							for(String ids : orderId){
								idListInArray.add(ids);
							}
							Integer maxOrderNumber = orderNumber;
							maxOrderNumber++;
							orderNumber = maxOrderNumber++;

						}	
						sc.close();
					}
				
				}
			}
		}catch(FileNotFoundException e){
			System.out.println("File not found");
		}
	}
	
	
	public void modeLoader(){
		try{
			Scanner sc = new Scanner(new BufferedReader(new FileReader("mode.txt")));
			String currentLine;
			
			while(sc.hasNext()){
				currentLine = sc.nextLine();
			modeType= currentLine;
			}
			sc.close();
		
	}
		catch(FileNotFoundException e){
			System.out.println("Mode file is not found");
		}
	
	}

	public Order getOrders(String orderNumber){
		return orderMap.get(orderNumber);

	}


	public String[] getAllOrderIds(){
		Set<String> keySet = orderMap.keySet();
		String[] keyArray = new String[keySet.size()];
		keyArray = keySet.toArray(keyArray);
		return keyArray;
	}
	
	
	public List<Order> getOrdersByDate(String date){
		String[] orderNumbers = getAllOrderIds();
		List<Order> ordersForDate= new ArrayList<>();
		for(String orderNumber: orderNumbers){
			String dateFromOrderNumber = getDate(orderNumber);
			if (date.equals(dateFromOrderNumber)) {
				ordersForDate.add(getOrders(orderNumber));				
			}
		}
		return ordersForDate;
		
	}


	public String  getDate(String orderNumber){
		String date = orderNumber.substring(0, 8);		
		return date;
		
	}
	
	public String getDateByFileName(File fileName){
		String name = fileName.getName();
		String date =name.substring(7, 15);
		return date;
	}
	
	
public ArrayList<String> getDatesInList(){
Set<String> dates = new HashSet<>();
ArrayList<String> uniqueDatesInArrayList = new ArrayList<>(dates);
String[] orderId = getAllOrderIds();
for(int i =0; i< orderId.length; i++){
	String 	 date = getDate(orderId[i]);
	dates.add(date);
	
	}
uniqueDatesInArrayList.addAll(dates);
			return uniqueDatesInArrayList;
	}
	
}
