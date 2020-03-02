package com.coding.sushi.serviceImpl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.coding.sushi.dao.SushiOrderRepository;
import com.coding.sushi.dao.SushiRepository;
import com.coding.sushi.model.CustomGetOrderObject;
import com.coding.sushi.model.CustomResponseObject;
import com.coding.sushi.model.OrderStatus;
import com.coding.sushi.model.SimpleResponseObject;
import com.coding.sushi.model.Sushi;
import com.coding.sushi.model.SushiOrder;
import com.coding.sushi.service.SushiService;

/*
 * This SushiService class is being used for  
 */
@Service
public class SushiServiceImpl implements SushiService {

	@Autowired
	private SushiRepository sushiRepository;

	@Autowired
	private SushiOrderRepository sushiOrderRepository;

	// @Autowired
	// private Worker worker;

	@Autowired
	private Worker2 worker2;

	@Override
	public CustomGetOrderObject getAllOrders() {
		CustomGetOrderObject obj = new CustomGetOrderObject();
		List<SushiOrder> orderList = sushiOrderRepository.findAll();
		List<OrderStatus> osList = new ArrayList<OrderStatus>();

		for (SushiOrder so : orderList) {
			OrderStatus os = new OrderStatus();
			Sushi s = new Sushi();
			s = sushiRepository.getOne(sushiOrderRepository.getOne(so.getSushiId()).getId());
			os.setOrderId(so.getId());
			os.setTimeSpent(s.getTimeToMake());

			if (so.getStatusId() == 2) {
				osList = obj.getInProgress();
				osList.add(os);
				obj.setInProgress(osList);
			}
//			else if (so.getStatusId() == 2) {
//				osList = obj.getPending();
//				osList.add(os);
//				obj.setPending(osList);
//			} 
//			else if (so.getStatusId() == 3) {
//				osList = obj.getPaused();
//				osList.add(os);
//				obj.setPaused(osList);
//			}
			else if (so.getStatusId() == 4) {
				osList = obj.getCompleted();
				osList.add(os);
				obj.setCompleted(osList);
			} else if (so.getStatusId() == 5) {
				osList = obj.getCancelled();
				osList.add(os);
				obj.setCancelled(osList);
			}
		}

		return obj;
	}

	List<Thread> threadList = new ArrayList<Thread>();

	@Override
	public ResponseEntity<CustomResponseObject> submitOrder(Map<String, String> map) {
		SushiOrder sushiOrder = null;
		Sushi sushi = null;
		CustomResponseObject jsonResponse = null;

		// Checking if Sushi with requested name exists
		List<Sushi> sushiList = sushiRepository.getAll();
		for (Sushi s : sushiList) {
			if (map.get("sushi_name").equalsIgnoreCase(s.getName())) {
				sushiOrder = new SushiOrder();
				sushi = s;
			}
		}

		// this sushi exists
		if (sushiOrder != null) {
			sushiOrder.setStatusId(1);
			sushiOrder.setSushiId(sushi.getId());
			sushiOrder.setCreatedAt(LocalDateTime.now());

			SushiOrder tempSushiOrder = sushiOrderRepository.save(sushiOrder);
			Sushi tempSushi = sushi;
			new Thread() {
				public void run() {
					addNewOrder(tempSushiOrder, tempSushi);
				}
			}.start();

			jsonResponse = getCustomJsonResponse(tempSushiOrder,
					"The order of " + sushi.getName() + " has been placed.", 0);

			return new ResponseEntity<CustomResponseObject>(jsonResponse, HttpStatus.OK);
		} else {
			// Sushi with this name does not exists in Db
			jsonResponse = getCustomJsonResponse(new SushiOrder(),
					"The order of " + map.get("sushi_name") + " was unsuccessful.", -1);
			return new ResponseEntity<CustomResponseObject>(jsonResponse, HttpStatus.OK);

		}

	}

	private void addNewOrder(SushiOrder tempSushiOrder, Sushi sushi) {

		Integer i = worker2.execute();
		Worker worker = new Worker(i, sushi.getTimeToMake());
		threadList.add(new Thread(worker, String.valueOf(tempSushiOrder.getId())));

		Thread t = null;
		if (i != 0) {
			for (Thread th : threadList) {
				if (th.getName().equals(String.valueOf(i))) {
					th.start();
					try {
						System.out.println("Order id " + tempSushiOrder.getId() + " is in progress.");
						th.join();
						System.out.println("Order id " + tempSushiOrder.getId() + " is complete.");
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					tempSushiOrder.setStatusId(4);
					sushiOrderRepository.save(tempSushiOrder);
					break;
				}
			}
		}

	}

	@Override
	public ResponseEntity<SimpleResponseObject> cancelOrder(Integer orderId) {
		SushiOrder sushiOrder = null;
		SimpleResponseObject jsonResponse = null;

		try {
			// Checking if Order with requested orderId exists
			sushiOrder = sushiOrderRepository.getOne(orderId);

			sushiOrder.setStatusId(5);

			cancelThread(orderId);
			sushiOrderRepository.save(sushiOrder);

			jsonResponse = getSimpleJsonResponse("The order with id: " + orderId + " has been cancelled.", 0);
			return new ResponseEntity<SimpleResponseObject>(jsonResponse, HttpStatus.OK);
		} catch (Exception e) {

			// Order with this orderId does not exist
			jsonResponse = getSimpleJsonResponse("The order with id: " + orderId + " does not exist.", -1);
			return new ResponseEntity<SimpleResponseObject>(jsonResponse, HttpStatus.OK);
		}

	}

	private void cancelThread(Integer orderId) {
		for (Thread t : threadList) {
			if (Integer.valueOf(t.getName()) == orderId && t.isAlive()) {
				t.stop();
				System.out.println("The order with id: " + orderId + " has been cancelled.");
			}
		}

	}

	@Override
	public ResponseEntity<SimpleResponseObject> pauseOrder(Integer orderId) {
		SushiOrder sushiOrder = null;
		SimpleResponseObject jsonResponse = null;

		try {
			// Checking if Order with requested orderId exists
			sushiOrder = sushiOrderRepository.getOne(orderId);

			pauseThread(orderId);
			sushiOrder.setStatusId(3);
			sushiOrderRepository.save(sushiOrder);

			jsonResponse = getSimpleJsonResponse("The order with id: " + orderId + " has been paused.", 0);
			return new ResponseEntity<SimpleResponseObject>(jsonResponse, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			// Order with this orderId does not exist
			jsonResponse = getSimpleJsonResponse("The order with id: " + orderId + " does not exist.", -1);
			return new ResponseEntity<SimpleResponseObject>(jsonResponse, HttpStatus.OK);
		}
	}

	private void pauseThread(Integer orderId) {

		for (Thread th : threadList) {
			if (Integer.valueOf(th.getName()) == orderId) {
				if (sushiOrderRepository.getOne(orderId).getStatusId() == 2) {
					try {
						th.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

				}
			}
		}

	}

	@Override
	public ResponseEntity<SimpleResponseObject> resumeOrder(Integer orderId) {
		SushiOrder sushiOrder = null;
		SimpleResponseObject jsonResponse = null;

		try {
			// Checking if Order with requested orderId exists
			sushiOrder = sushiOrderRepository.getOne(orderId);

			sushiOrder.setStatusId(2);
			sushiOrderRepository.save(sushiOrder);

			jsonResponse = getSimpleJsonResponse("The order with id: " + orderId + " has been resumed.", 0);
			return new ResponseEntity<SimpleResponseObject>(jsonResponse, HttpStatus.OK);
		} catch (Exception e) {

			// Order with this orderId does not exist
			jsonResponse = getSimpleJsonResponse("The order with id: " + orderId + " does not exist.", -1);
			return new ResponseEntity<SimpleResponseObject>(jsonResponse, HttpStatus.OK);
		}
	}

	private CustomResponseObject getCustomJsonResponse(SushiOrder tempSushiOrder, String msg, Integer code) {
		CustomResponseObject jsonResponse;
		jsonResponse = new CustomResponseObject();
		jsonResponse.setOrder(tempSushiOrder);
		jsonResponse.setMsg(msg);
		jsonResponse.setCode(code);
		return jsonResponse;
	}

	private SimpleResponseObject getSimpleJsonResponse(String msg, Integer code) {
		SimpleResponseObject jsonResponse;
		jsonResponse = new SimpleResponseObject();
		jsonResponse.setMsg(msg);
		jsonResponse.setCode(code);
		return jsonResponse;
	}

	public List<SushiOrder> getAllSushiOrder() {
		return sushiOrderRepository.findAll();
	}

}
