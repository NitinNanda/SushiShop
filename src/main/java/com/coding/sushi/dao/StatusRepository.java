package com.coding.sushi.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.coding.sushi.model.Status;

@Repository
public interface StatusRepository extends JpaRepository<Status, Integer> {

	@Query(value = "SELECT * FROM Status;", nativeQuery = true)
	List<Status> getAll();

//	@Query(value = "INSERT into sushi_order(status_id, sushi_id) VALUES (?1,?2)", nativeQuery = true)
//	SushiOrder saveSushiOrder(int i, int j);

}
