package com.Assessment.BookingAssessment.Controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Assessment.BookingAssessment.Model.GroceryItem;
import com.Assessment.BookingAssessment.Model.ResponseMessage;
import com.Assessment.BookingAssessment.Service.BookingApplicationService;

@RestController
@RequestMapping("/grocery")
public class BookingApplicationController {

	private static final Logger logger = LoggerFactory.getLogger(BookingApplicationController.class);

	@Autowired
	BookingApplicationService bookingService;

	@PostMapping("/addGroceryItem")
	// Adding Grocery Item in Grocery List
	public ResponseEntity<ResponseMessage> addGroceryItem(@RequestBody GroceryItem item) {
		logger.info("Adding new grocery item: {}", item);
		ResponseMessage message = bookingService.addGroceryItem(item);
		logger.info("Exiting BookingApplicationController--> addGroceryItem");
		return ResponseEntity.status(HttpStatus.CREATED).body(message);
	}

	@GetMapping("/viewGroceryItems")
	// Getting all the data in the grocery list
	public ResponseEntity<List<GroceryItem>> viewGroceryItems() {
		logger.info("Viewing all grocery items");
		List<GroceryItem> items = bookingService.viewGroceryItems();
		logger.info("Exiting BookingApplicationController--> viewGroceryItems");
		return ResponseEntity.ok().body(items);
	}

	@DeleteMapping("/{itemId}")
	public ResponseEntity<ResponseMessage> removeGroceryItem(@PathVariable String itemId) {
		logger.info("Removing grocery item with ID: {}", itemId);
		ResponseMessage message = bookingService.removeGroceryItem(itemId);
		logger.info("Exiting BookingApplicationController--> removeGroceryItem");
		return ResponseEntity.ok().body(message);
	}

	@PutMapping("/{itemId}")
	public ResponseEntity<ResponseMessage> updateGroceryItem(@PathVariable String itemId,
			@RequestBody GroceryItem item) {
		logger.info("Updating grocery item with ID: {}", itemId);
		ResponseMessage message = bookingService.updateGroceryItem(itemId, item);
		logger.info("Exiting BookingApplicationController--> updateGroceryItem");
		return ResponseEntity.ok().body(message);
	}

	@PutMapping("/{itemId}/inventory")
	public ResponseEntity<ResponseMessage> manageInventory(@PathVariable String itemId,
			@RequestBody Map<String, Integer> inventory) {
		int quantity = inventory.get("quantity");
		logger.info("Managing inventory for grocery item with ID: {}. New quantity: {}", itemId, quantity);
		ResponseMessage message = bookingService.manageInventory(itemId, inventory);
		logger.info("Exiting BookingApplicationController--> manageInventory");
		return ResponseEntity.ok().body(message);
	}
}
