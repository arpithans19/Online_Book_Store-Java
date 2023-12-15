package com.inventory.service;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.inventory.entity.DBSequence;
import com.inventory.entity.Inventory;
import com.inventory.exception.BookNotFoundInStockException;
import com.inventory.repository.InventoryRepository;

@Service
public class InventoryServiceImpl implements InventoryService {

	@Autowired
	private InventoryRepository inventRepo;

	@Autowired
	private MongoOperations mongoOperations;

	@Override
	public Inventory saveInventoryDetails(Inventory inventory) {

		inventory.setInventoryId(getSequenceNumber(Inventory.SEQUENCE_NAME));
		return inventRepo.save(inventory);
	}

	@Override
	public Inventory updateInventoryDetails(Inventory inventory) {
		Optional<Inventory> optionalStock = inventRepo.findById(inventory.getInventoryId());
		if (optionalStock.isEmpty()) {
			throw new BookNotFoundInStockException("this book is not available at the moment!!!");
		}
		return inventRepo.save(inventory);
	}

	@Override
	public void deleteInventoryDetailsById(int inventoryId) {
		Optional<Inventory> optionalStock = inventRepo.findById(inventoryId);
		if (optionalStock.isEmpty()) {
			throw new BookNotFoundInStockException("this book is not available at the moment!!!");
		}
		inventRepo.deleteById(inventoryId);
	}

	@Override
	public List<Inventory> listAllofInventoryStocks() {
		List<Inventory> listOfAllBookStocks= inventRepo.findAll();
		if (listOfAllBookStocks.isEmpty()) {
			throw new BookNotFoundInStockException("this book is not available at the moment!!!");
		}
		return listOfAllBookStocks;
	}

	@Override
	public Inventory getInventoryDetailsById(int inventoryId) {
		Optional<Inventory> optionalStock = inventRepo.findById(inventoryId);
		if (optionalStock.isEmpty()) {
			throw new BookNotFoundInStockException("Book not found with this id!!!");
		}
		return optionalStock.get();
	}

	@Override
	public int getSequenceNumber(String sequenceName) {
		//generate sequence no
				Query query=new Query(Criteria.where("id").is(sequenceName));
				//update the sequence no
				Update update=new Update().inc("seq",1);
				//modify in document
				DBSequence counter=mongoOperations.findAndModify(query,update, options().returnNew(true).upsert(true),DBSequence.class);
				return !Objects.isNull(counter)? counter.getSeq():1;
	}

}
