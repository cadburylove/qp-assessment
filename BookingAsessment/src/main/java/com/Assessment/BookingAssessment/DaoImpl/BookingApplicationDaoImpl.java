package com.Assessment.BookingAssessment.DaoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.Assessment.BookingAssessment.Dao.BookingApplicationDao;
import com.Assessment.BookingAssessment.Model.GroceryItem;
import com.Assessment.BookingAssessment.Model.ResponseMessage;
import com.Assessment.BookingAssessment.common.Constants;

@Repository
public class BookingApplicationDaoImpl implements BookingApplicationDao {

	private static final Logger logger = LoggerFactory.getLogger(BookingApplicationDaoImpl.class);

	@Autowired
	DataSource dataSource;

	@Override
	public ResponseMessage addGroceryItem(GroceryItem item) {
		ResponseMessage message = new ResponseMessage();
		String sql = "INSERT INTO GROCERY_ITEM (NAME, PRICE, QUANTITY) VALUES (?, ?, ?)";
		try (Connection conn = dataSource.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, item.getName());
			pstmt.setDouble(2, item.getPrice());
			pstmt.setInt(3, item.getQuantity());
			pstmt.executeUpdate();
			message.setCode(Constants.ERROR_CODES.NO_ERROR);
			message.setMessage("Grocery item added successfully ");
			logger.info("Grocery item added successfully: {}");
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("Error adding grocery item: {}", e.getMessage());
			message.setCode(Constants.ERROR_CODES.EXCEPTION_OCCURED);
			message.setMessage("Error adding grocery item: " + e.getMessage());
		}
		return message;
	}

	@Override
	public ResponseMessage manageInventory(String itemId, int quantity) {
		ResponseMessage message = new ResponseMessage();
		String sql = "UPDATE GROCERY_ITEM SET QUANTITY = ? WHERE ID = ?";
		try (Connection conn = dataSource.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, quantity);
			pstmt.setString(2, itemId);
			int affectedRows = pstmt.executeUpdate();
			if (affectedRows > 0) {
				message.setCode(Constants.ERROR_CODES.NO_ERROR);
				message.setMessage(
						"Inventory managed successfully for item ID " + itemId + ": New quantity: " + quantity);
				logger.info("Inventory managed successfully for item ID {}: New quantity: {}", itemId, quantity);
			} else {
				message.setCode(Constants.ERROR_CODES.INVALID);
				message.setMessage("Item with ID " + itemId + " not found");
				logger.error("Item with ID {} not found", itemId);
			}
		} catch (SQLException e) {
			logger.error("Error managing inventory for item ID {}: {}", itemId, e.getMessage());
			message.setCode(Constants.ERROR_CODES.EXCEPTION_OCCURED);
			message.setMessage("Error managing inventory for item ID " + itemId + ": " + e.getMessage());
		}
		return message;
	}

	@Override
	public ResponseMessage updateGroceryItem(String itemId, GroceryItem item) {
		ResponseMessage message = new ResponseMessage();
		String sql = "UPDATE GROCERY_ITEM SET NAME = ?, PRICE = ?, QUANTITY = ? WHERE ID = ?";
		try (Connection conn = dataSource.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, item.getName());
			pstmt.setDouble(2, item.getPrice());
			pstmt.setInt(3, item.getQuantity());
			pstmt.setString(4, itemId);
			int affectedRows = pstmt.executeUpdate();
			if (affectedRows > 0) {
				message.setCode(Constants.ERROR_CODES.NO_ERROR);
				message.setMessage("Grocery item updated successfully");
				logger.info("Grocery item updated successfully: {}", item);
			} else {
				message.setCode(Constants.ERROR_CODES.INVALID);
				message.setMessage("Item with ID " + itemId + " not found");
				logger.error("Item with ID {} not found", itemId);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("Error updating grocery item: {}", e.getMessage());
			message.setCode(Constants.ERROR_CODES.EXCEPTION_OCCURED);
			message.setMessage("Error updating grocery item: " + e.getMessage());
		}
		return message;
	}

	@Override
	public ResponseMessage removeGroceryItem(String itemId) {
		ResponseMessage message = new ResponseMessage();
		String sql = "DELETE FROM GROCERY_ITEM WHERE ID = ?";
		try (Connection conn = dataSource.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, itemId);
			int affectedRows = pstmt.executeUpdate();
			if (affectedRows > 0) {
				message.setCode(Constants.ERROR_CODES.NO_ERROR);
				message.setMessage("Grocery item with ID " + itemId + " removed successfully");
				logger.info("Grocery item with ID {} removed successfully", itemId);
			} else {
				message.setCode(Constants.ERROR_CODES.INVALID);
				message.setMessage("Item with ID " + itemId + " not found");
				logger.error("Item with ID {} not found", itemId);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("Error removing grocery item with ID {}: {}", itemId, e.getMessage());
			message.setCode(Constants.ERROR_CODES.EXCEPTION_OCCURED);
			message.setMessage("Error removing grocery item with ID " + itemId + ": " + e.getMessage());
		}
		return message;
	}

	@Override
	public List<GroceryItem> viewGroceryItems() {
		List<GroceryItem> items = new ArrayList<>();
		String sql = "SELECT * FROM GROCERY_ITEM";
		try (Connection conn = dataSource.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()) {
			while (rs.next()) {
				GroceryItem item = new GroceryItem();
				item.setId(rs.getString("id"));
				item.setName(rs.getString("name"));
				item.setPrice(rs.getDouble("price"));
				item.setQuantity(rs.getInt("quantity"));
				items.add(item);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("Error viewing grocery items: {}", e.getMessage());
		}
		return items;
	}

}
