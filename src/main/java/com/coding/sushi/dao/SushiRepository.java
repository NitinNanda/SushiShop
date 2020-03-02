package com.coding.sushi.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.coding.sushi.model.Sushi;

@Repository
public interface SushiRepository extends JpaRepository<Sushi, Integer> {

	@Query(value = "SELECT * FROM sushi ;", nativeQuery = true)
	List<Sushi> getAll();

//	@Query(value = "INSERT into sushi_order(status_id, sushi_id) VALUES (?1,?2)", nativeQuery = true)
//	SushiOrder saveSushiOrder(int i, int j);

}
