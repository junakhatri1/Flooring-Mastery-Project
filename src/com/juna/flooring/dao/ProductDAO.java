package com.juna.flooring.dao;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

import com.juna.flooring.model.Product;
import com.juna.flooring.ui.Prompt;

public class ProductDAO {

	Prompt consoleIo = new Prompt();
	ArrayList<Product> productList = new ArrayList<>();
	private static final String PRODUCT_FILE = "product.txt";
	private static final String DELIMITER = ",";

	public ArrayList<Product> getAllProducts(){
		ArrayList<Product>productsInList = new ArrayList<>();
		productsInList.addAll(productList);
		return productsInList;
	}


	public void loadProduct() throws FileNotFoundException{
		Scanner sc = new Scanner(new BufferedReader(new FileReader(PRODUCT_FILE)));
		String currentLine;
		String[] currentTokens;
		while(sc.hasNext()){
			currentLine = sc.nextLine();
			currentTokens = currentLine.split(DELIMITER);

			Product product = new Product();
			product.setProductType(currentTokens[0]);
			product.setCostPerSqFoot(Double.parseDouble(currentTokens[1]));
			product.setLaborCostPerSqFoot(Double.parseDouble(currentTokens[2]));
			productList.add(product);
			

		}
		sc.close();

	}
	public Double getCostByProductType(String productType){
		double	costPerSqFoot = 0d;
		ArrayList<Product>productsInList = getAllProducts();
		for(int i =0; i< productsInList.size(); i++){
			if(productType.equalsIgnoreCase(productsInList.get(i).getProductType())){
		costPerSqFoot =  productsInList.get(i).getCostPerSqFoot();
			}
		}
		return costPerSqFoot;
		
	}
	
	
	public Double getLaborCostByProductType(String productType){
		double	laborCostPerSqFoot = 0d;
		ArrayList<Product>productsInList = getAllProducts();
		for(int i =0; i< productsInList.size(); i++){
			if(productType.equalsIgnoreCase(productsInList.get(i).getProductType())){
		laborCostPerSqFoot =  productsInList.get(i).getLaborCostPerSqFoot();
			}
		}
		return laborCostPerSqFoot;
		
	}
	
	

}
