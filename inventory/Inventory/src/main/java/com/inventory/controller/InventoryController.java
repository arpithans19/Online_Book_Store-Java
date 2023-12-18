package com.inventory.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inventory.entity.Inventory;
import com.inventory.service.InventoryServiceImpl;

@RestController
@RequestMapping("/inventory")
@CrossOrigin(origins = "http://localhost:3000")

public class InventoryController {

	@Autowired
	private InventoryServiceImpl inventoryServiceImpl;

	@GetMapping("/getAllStocks")

	public List<Inventory> getAllBooksStocks() {
		List<Inventory> allStocks = inventoryServiceImpl.listAllofInventoryStocks();
		return allStocks;

	}

	@GetMapping("/get/{inventoryId}")
	public ResponseEntity<Object> fetchInventoryById(@PathVariable("inventoryId") int inventoryId) {

		ResponseEntity<Object> responseEntity = null;
		Inventory inventory = inventoryServiceImpl.getInventoryDetailsById(inventoryId);
		responseEntity = new ResponseEntity<>(inventory, HttpStatus.OK);
		return responseEntity;
	}
	@PostMapping("/add")
	public ResponseEntity<Inventory> addBookStocksDetails( @Validated @RequestBody Inventory inventory) {
		Inventory newInventory  = inventoryServiceImpl.saveInventoryDetails(inventory);
		ResponseEntity<Inventory> responseEntity = new ResponseEntity<>(newInventory, HttpStatus.OK);
		return responseEntity;

	}
    @DeleteMapping("/delete/{inventoryId}")
	public ResponseEntity<String> removeStockDetails(@PathVariable("inventoryId") int inventoryId) {
    	inventoryServiceImpl.deleteInventoryDetailsById(inventoryId);
		ResponseEntity<String> responseEntity = new ResponseEntity<>("Details deleted Successfully!!", HttpStatus.OK);
		return responseEntity;
	}

	@PutMapping("/update")
	public ResponseEntity<Inventory> updateStockDetails(@RequestBody Inventory inventory) {
		Inventory updatedInventory = inventoryServiceImpl.updateInventoryDetails(inventory);
		ResponseEntity<Inventory> responseEntity = new ResponseEntity<>(updatedInventory, HttpStatus.OK);
		return responseEntity;
	}
	

}
