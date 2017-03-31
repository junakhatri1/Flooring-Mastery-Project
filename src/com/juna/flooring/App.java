package com.juna.flooring;

import com.juna.flooring.controller.OrderController;

public class App {
	
	public static void main(String[] args){
		OrderController controller = new OrderController();
		controller.run();
		
	}
}

