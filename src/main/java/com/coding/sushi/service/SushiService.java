package com.coding.sushi.service;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.coding.sushi.model.CustomGetOrderObject;
import com.coding.sushi.model.CustomResponseObject;
import com.coding.sushi.model.SimpleResponseObject;
import com.coding.sushi.model.SushiOrder;

public interface SushiService {
	
    CustomGetOrderObject getAllOrders();

	ResponseEntity<CustomResponseObject> submitOrder(Map<String, String> map);

	ResponseEntity<SimpleResponseObject> cancelOrder(Integer orderId);

	ResponseEntity<SimpleResponseObject> pauseOrder(Integer orderId);

	ResponseEntity<SimpleResponseObject> resumeOrder(Integer orderId);

}
