package com.Assessment.BookingAssessment.Dao;

import java.util.List;

import com.Assessment.BookingAssessment.Model.GroceryItem;
import com.Assessment.BookingAssessment.Model.OrderItem;
import com.Assessment.BookingAssessment.Model.ResponseMessage;


public interface BookingApplicationDao {

	ResponseMessage addGroceryItem(GroceryItem item);

	ResponseMessage manageInventory(String itemId, int quantity);

	ResponseMessage updateGroceryItem(String itemId, GroceryItem item);

	ResponseMessage removeGroceryItem(String itemId);

	List<GroceryItem> viewGroceryItems();

}
