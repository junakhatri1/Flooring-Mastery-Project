package com.juna.flooring.ui;

import java.util.Scanner;

public class Prompt {

	Scanner sc = new Scanner(System.in);

	public  int userInput(String prompt){
		System.out.println(prompt);
		int userInput = sc.nextInt();
		return userInput;

	}

	public String userInputString(String prompt){
		System.out.println(prompt);
	String userInput = sc.nextLine();
		return userInput;
	}


	public int  value(String prompt, int max, int min){

		int userInput =0;

		System.out.println(prompt);
		System.out.println("The maximum value is " + max); 
		System.out.println("The minimum value is " + min);
		while(userInput > min && userInput < max){
			System.out.println("Please input a number within the given range");
			userInput = sc.nextInt();



		}
		return userInput;
	}

	public String readString(String prompt){
		System.out.println(prompt);
		Scanner sc = new Scanner(System.in);
		String message = sc.nextLine();

		return message;
	}

	public float floatValue(String prompt ){
		System.out.println(prompt);
		Scanner sc = new Scanner(System.in);
		float value = sc.nextFloat();
		return value;
	}
	public float MaxMin(String prompt, float max, float min){
		float userInput = 0.0f;
		System.out.println(prompt);
		System.out.println("The maximum float value is "+ max);
		System.out.println("The minimum float value is " + min);
		while(userInput> min && userInput< max){
			System.out.println("Input a float number between the given range");
			Scanner sc = new Scanner(System.in);
			userInput = sc.nextFloat();
		}
		return userInput;

	}
	public double returningDouble(String prompt){
		Scanner sc = new Scanner(System.in);
		System.out.println(prompt);
		return  sc.nextDouble();
	
	}

	public double ReadDouble( String prompt, double max, double min){
		System.out.println(prompt);
		System.out.println("The maximum double value is " + max);
		System.out.println("The minimum double value is " + min);
		double userInput = 0.0d;
		while(userInput> min && userInput < max){
			System.out.println("Input a double value from the given range");
			userInput = sc.nextDouble();


		}
		return userInput;
	}
	public void givenString(String prompt){
		System.out.println(prompt);
	}


	public int readValue(String prompt, int min, int max){

		int result;
		do {
			result = userInput(prompt);
		} while (result < min || result > max);

		return result;
	}
	
	public void printIntWithoutNewLine(int i){
		System.out.println(i);
	}
	
	public void printInSameLine(String prompt){
		System.out.print(prompt);
	}

}
