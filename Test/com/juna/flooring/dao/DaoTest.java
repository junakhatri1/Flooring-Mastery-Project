package com.juna.flooring.dao;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.juna.flooring.model.Order;

public class DaoTest {
	OrderDAOImpl orderDAO;
	Order order1;
	Order order2;
	Order order3;
	
@Before
public void setUp(){
	orderDAO = new OrderDAOImpl();
	order1 = new Order();
	order1.setCustomerName("Pankaj");
	order1.setProductType("Books");
	order1.setState("OH");
	order1.setTaxRate(7.5);
	order1.setCostPerSqFoot(2.5);
	order1.setLaborCost(3.0);
	order1.setProductArea(5.0);
	
	
	order2 = new Order();
	order1.setCustomerName("Juna");
	order1.setProductType("Clothing");
	order1.setState("CO");
	order1.setTaxRate(6.0);
	order1.setCostPerSqFoot(2.5);
	order1.setLaborCost(3.0);
	order1.setProductArea(5.0);
	
	
	order3 = new Order();
	order1.setCustomerName("Rojina");
	order1.setProductType("Wood");
	order1.setState("MI");
	order1.setTaxRate(6.5);
	order1.setCostPerSqFoot(2.5);
	order1.setLaborCost(3.0);
	order1.setProductArea(5.0);
}
	
	
	@Test
	
	public void addOrderTest(){
		//AAA
		//Arrange
		Order order = orderDAO.addOrder(order1);
		//Act
		Order result = orderDAO.getOrders(order.getOrderNumber());
		//Assert
		Assert.assertEquals(order1, result);
		
	}
	@Test
	public void editOrderTest() {
		//AAA
		//Arrange
		Order order = orderDAO.addOrder(order1);
		
		//Act
		order.setCustomerName("Sajan");
		order.setState("CA");
		order.setProductType("Accessories");
		Order result = orderDAO.editOrder(order1);
		//Assert
		Assert.assertEquals(order1, result);
	}
	
	@Test
	public void removeOrderTest(){
		//AAA
		//Arrange
		orderDAO.addOrder(order1);
		orderDAO.addOrder(order2);
		//Act
		Order order = orderDAO.removeOrder("033020170");
		Order result = orderDAO.removeOrder(order.getOrderNumber());
		//Assert
		Assert.assertEquals(null, result);
		
	}
	
	@Test
	public void getOrderTest(){
		//AAA
		//Arrange
		orderDAO.addOrder(order1);
		//Arrange
		Order order = orderDAO.getOrders("033020170");
		//Act
		Order result = orderDAO.getOrders(order.getOrderNumber());
		//Assert
		Assert.assertEquals(order1, result);
		
	}
	@Test
	public void getAllOrderIdsTest(){
		//AAA
		//Arrange
		orderDAO.addOrder(order1);
		orderDAO.addOrder(order2);
		orderDAO.addOrder(order3);
		//Arrange
		 String[] orderNumbers = orderDAO.getAllOrderIds();
		 int ordersLength = orderNumbers.length;
		 //Assert
		 Assert.assertEquals(3, ordersLength);
	}
	@Test
	
	public void getOrdersByDateTest(){
		//AAA
		//Arrange
		orderDAO.addOrder(order1);
		orderDAO.addOrder(order2);
		orderDAO.addOrder(order3);
		//Act
		List<Order> getOrder = orderDAO.getOrdersByDate("03302017");
	int orderSize =	getOrder.size();
	//	List<Order> result = orderDAO.getOrdersByDate(order.getDate());
		
		//Assert
		Assert.assertEquals(3, orderSize);
		
	}
	@Test
	public void getDateTest(){
		//AAA
		//Arrange
		orderDAO.addOrder(order1);
		//Arrange
		String date = orderDAO.getDate("033020170");
		String result = orderDAO.getDate(order1.getOrderNumber());
		//Assert
		Assert.assertEquals(date, result);
		
	}
	@Test
	public void getDateByFileNameTest(){
		//AAA
		//Arrange
		orderDAO.addOrder(order1);
		File file = new File("Orders_03292017.txt");
		//Act
		String date = orderDAO.getDateByFileName(file);
		String  expectedDate = "03292017";
		//Assert
		Assert.assertEquals(expectedDate, date);
		
	}@Test
	public void getDatesTest(){
		//AAAA
		//Arrange
		orderDAO.addOrder(order1);
		orderDAO.addOrder(order2);
		orderDAO.addOrder(order3);
		//Act
		ArrayList<String> uniqueDates = orderDAO.getDatesInList();
		int uniqueDatesLength = uniqueDates.size();
		//Assert
		Assert.assertEquals(1, uniqueDatesLength);
		
	}
	@Test
	public void saveOrderLoaderTest(){
		///AAA
		//Arrange
		orderDAO.addOrder(order1);
		orderDAO.addOrder(order2);
		orderDAO.addOrder(order3);
		//Act
		OrderDAOImpl dao = new OrderDAOImpl();
		
		orderDAO.orderWriter();
			//Assert.fail();
		orderDAO.modeLoader();
			dao.orderLoader();
		//	Assert.fail();
			//Assert
			Order orderDAO1 = dao.getOrders(order1.getOrderNumber());
			Order orderDAO2 = dao.getOrders(order2.getOrderNumber());
			Order orderDAO3 = dao.getOrders(order3.getOrderNumber());
			//Assert
			assertEquals(order1, orderDAO1);
			assertEquals(order2, orderDAO2);
			assertEquals(order3, orderDAO3);
	}
		

}
