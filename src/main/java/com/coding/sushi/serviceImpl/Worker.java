package com.coding.sushi.serviceImpl;

import org.springframework.context.annotation.Scope;

@Scope(value = "prototype")
public class Worker implements Runnable {

	private Integer id;
	private Integer totalTime;
	private Integer remainingTime;

	public Worker(Integer id, Integer totalTime) {
		this.id = id;
		this.totalTime = totalTime;
	}

	@Override
	public void run() {
		Integer counter = 0;
		boolean flag = true;

		while (flag) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			counter++;
			if (counter == totalTime) {
				flag = false;
			}
		}
	}

}
