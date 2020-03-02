package com.coding.sushi.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.coding.sushi.model.SushiOrder;


@Repository
public interface SushiOrderRepository extends JpaRepository<SushiOrder, Integer>{
	
	@Query(value = "SELECT * FROM sushi_order;", nativeQuery = true)
	List<SushiOrder> getAll();

	@Modifying
	@Transactional
	@Query(value = "INSERT into sushi_order(status_id, sushi_id) VALUES (?1,?2)", nativeQuery = true)
	Integer saveSushiOrder(int i, int j);

	 
}
