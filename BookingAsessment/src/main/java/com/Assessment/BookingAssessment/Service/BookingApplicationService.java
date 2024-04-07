package com.Assessment.BookingAssessment.Service;

import java.util.List;
import java.util.Map;

import com.Assessment.BookingAssessment.Model.GroceryItem;
import com.Assessment.BookingAssessment.Model.OrderItem;
import com.Assessment.BookingAssessment.Model.ResponseMessage;

public interface BookingApplicationService {

	public ResponseMessage addGroceryItem(GroceryItem item);

	public List<GroceryItem> viewGroceryItems();

	public ResponseMessage removeGroceryItem(String itemId);

	public ResponseMessage updateGroceryItem(String itemId, GroceryItem item);

	public ResponseMessage manageInventory(String itemId, Map<String, Integer> inventory);

}
