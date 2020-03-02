package com.coding.sushi.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coding.sushi.model.CustomGetOrderObject;
import com.coding.sushi.model.CustomResponseObject;
import com.coding.sushi.model.SimpleResponseObject;
import com.coding.sushi.model.SushiOrder;
import com.coding.sushi.service.SushiService;

@RestController
@RequestMapping("/api")
public class SushiController {

	@Autowired
	private SushiService sushiService;

	@PostMapping(value = "/orders")
	private ResponseEntity<CustomResponseObject> submitOrder(@RequestBody Map<String, String> map) {
		return sushiService.submitOrder(map);
	}

	@PutMapping("/orders/cancel/{order_id}")
	private ResponseEntity<SimpleResponseObject> cancelOrder(@PathVariable("order_id") Integer orderId) {
		return sushiService.cancelOrder(orderId);
	}

	@PutMapping("/orders/pause/{order_id}")
	private ResponseEntity<SimpleResponseObject> pauseOrder(@PathVariable("order_id") Integer orderId) {

		return sushiService.pauseOrder(orderId);
	}

	@PutMapping("/orders/resume/{order_id}")
	private ResponseEntity<SimpleResponseObject> resumeOrder(@PathVariable("order_id") Integer orderId) {

		return sushiService.resumeOrder(orderId);
	}

	@GetMapping(value = "/orders/status")
	private CustomGetOrderObject getAllOrders() {
		return sushiService.getAllOrders();
	}
}
