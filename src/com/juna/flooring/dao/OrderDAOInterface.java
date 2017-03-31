package com.juna.flooring.dao;




import com.juna.flooring.model.Order;

public interface OrderDAOInterface {


	public Order addOrder(Order order);
	public Order editOrder(Order order);
	public Order removeOrder(String orderNumber);
	public void orderLoader();
	public void orderWriter();


}
