package com.inventory.service;

import java.util.List;

import com.inventory.entity.Inventory;

public interface InventoryService {

	Inventory saveInventoryDetails(Inventory inventory);

	Inventory updateInventoryDetails(Inventory inventory);

	void deleteInventoryDetailsById(int inventoryId);

	List<Inventory> listAllofInventoryStocks();

	Inventory getInventoryDetailsById(int inventoryId);

	public int getSequenceNumber(String sequenceName);

//	Inventory getInventoryDetailsByBookName(String bookName);
//
//	Inventory getInventoryDetailsByAuthor(String author);
	
	

}
