package com.coding.sushi.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.coding.sushi.dao.SushiOrderRepository;
import com.coding.sushi.dao.SushiRepository;
import com.coding.sushi.model.Sushi;
import com.coding.sushi.model.SushiOrder;

@Service
@Scope(value = "prototype")
public class Worker2 {
	@Autowired
	private SushiOrderRepository sushiOrderRepository;

	@Autowired
	private SushiRepository sushiRepository;

	Integer countInProgress = 0;

	public Integer execute() {

		List<SushiOrder> sushiOrderList = sushiOrderRepository.findAll();
		for (SushiOrder s : sushiOrderList) {
			if (s.getStatusId().intValue() == 2) {
				countInProgress++;
			}
		}
		if (countInProgress <= 3) {
			SushiOrder tempOrder = null;
			Sushi tempSushi = null;

			// First, the paused order(if present) should be finished
			for (SushiOrder s : sushiOrderList) {
				if (s.getStatusId().intValue() == 3) {
					tempOrder = s;
					tempOrder.setStatusId(2);
					sushiOrderRepository.save(tempOrder);
					tempSushi = sushiRepository.getOne(s.getSushiId());
					return tempOrder.getId();
				}
			}

			// If no paused order is present, then start a new created order
			for (SushiOrder s : sushiOrderList) {
				if (s.getStatusId().intValue() == 1) {
					tempOrder = s;
					tempOrder.setStatusId(2);
					sushiOrderRepository.save(tempOrder);
					tempSushi = sushiRepository.getOne(s.getSushiId());
					return tempOrder.getId();
				}
			}
			return 0;
		} else {
			return 0;
		}
	}

	public Integer getCountInProgress() {
		return countInProgress;
	}

	public void setCountInProgress(Integer countInProgress) {
		this.countInProgress = countInProgress;
	}

}
