package com.Assessment.BookingAssessment.ServiceImpl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Assessment.BookingAssessment.Dao.BookingApplicationDao;
import com.Assessment.BookingAssessment.Model.GroceryItem;
import com.Assessment.BookingAssessment.Model.ResponseMessage;
import com.Assessment.BookingAssessment.Service.BookingApplicationService;

@Service
public class BookingApplicationServiceImpl implements BookingApplicationService {

	private static final Logger logger = LoggerFactory.getLogger(BookingApplicationServiceImpl.class);

	@Autowired
	BookingApplicationDao bookingDao;

	@Override
	public ResponseMessage addGroceryItem(GroceryItem item) {
		logger.info("Adding new grocery item: {}", item);
		ResponseMessage message = bookingDao.addGroceryItem(item);
		logger.info("Grocery item added successfully");
		return message;
	}

	@Override
	public List<GroceryItem> viewGroceryItems() {
		logger.info("Viewing all grocery items");
		List<GroceryItem> items = bookingDao.viewGroceryItems();
		logger.info("Found {} grocery items", items.size());
		return items;
	}

	@Override
	public ResponseMessage removeGroceryItem(String itemId) {
		logger.info("Removing grocery item with ID: {}", itemId);
		ResponseMessage message = bookingDao.removeGroceryItem(itemId);
		logger.info("Grocery item removed successfully");
		return message;

	}

	@Override
	public ResponseMessage updateGroceryItem(String itemId, GroceryItem item) {
		logger.info("Updating grocery item with ID: {}", itemId);
		ResponseMessage message = bookingDao.updateGroceryItem(itemId, item);
		logger.info("Grocery item updated successfully");
		return message;
	}

	@Override
	public ResponseMessage manageInventory(String itemId, Map<String, Integer> inventory) {
		int quantity = inventory.get("quantity");
		logger.info("Managing inventory for grocery item with ID: {}. New quantity: {}", itemId, quantity);
		ResponseMessage message = bookingDao.manageInventory(itemId, quantity);
		logger.info("Inventory managed successfully");
		return message;
	}

}
