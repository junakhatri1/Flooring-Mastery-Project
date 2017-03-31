package com.juna.flooring.dao;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;
import com.juna.flooring.model.Taxes;
import com.juna.flooring.ui.Prompt;

public class TaxesDAO {

	Prompt consoleIo = new Prompt();
	ArrayList<Taxes> taxList = new ArrayList<>();
	private static final String PRODUCT_FILE = "taxes.txt";
	private static final String DELIMITER = ",";

	public ArrayList<Taxes> getAllTaxes(){
		ArrayList<Taxes>taxesInList = new ArrayList<>();
		taxesInList.addAll(taxList);
		return taxesInList;
	}

	public void loadTaxes() throws FileNotFoundException{
		Scanner sc = new Scanner(new BufferedReader(new FileReader(PRODUCT_FILE)));
		String currentLine;
		String[] currentTokens;
		while(sc.hasNext()){
			currentLine = sc.nextLine();
			currentTokens = currentLine.split(DELIMITER);

			Taxes taxes = new Taxes();
			taxes.setState(currentTokens[0]);
			taxes.setTaxRate(Double.parseDouble(currentTokens[1]));
			taxList.add(taxes);
		

		}
		sc.close();
	}
	
	
	public double getTaxRateByState(String state){
		ArrayList<Taxes> taxInList =  getAllTaxes();
		double getTax = 0d;
		for(int i =0; i< taxInList.size(); i++){
			if(state.equalsIgnoreCase(taxInList.get(i).getState())){
			getTax= taxInList.get(i).getTaxRate();
		}
		}
		return getTax;
	}

}
