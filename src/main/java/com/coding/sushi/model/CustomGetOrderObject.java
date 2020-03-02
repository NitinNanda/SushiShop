package com.coding.sushi.model;

import java.util.ArrayList;
import java.util.List;

public class CustomGetOrderObject {

	private List<OrderStatus> inProgress = new ArrayList<OrderStatus>();
	private List<OrderStatus> pending = new ArrayList<OrderStatus>();
	private List<OrderStatus> paused = new ArrayList<OrderStatus>();
	private List<OrderStatus> cancelled = new ArrayList<OrderStatus>();
	private List<OrderStatus> completed = new ArrayList<OrderStatus>();

	public List<OrderStatus> getInProgress() {
		return inProgress;
	}

	public void setInProgress(List<OrderStatus> inProgress) {
		this.inProgress = inProgress;
	}

	public List<OrderStatus> getPending() {
		return pending;
	}

	public void setPending(List<OrderStatus> pending) {
		this.pending = pending;
	}

	public List<OrderStatus> getPaused() {
		return paused;
	}

	public void setPaused(List<OrderStatus> paused) {
		this.paused = paused;
	}

	public List<OrderStatus> getCancelled() {
		return cancelled;
	}

	public void setCancelled(List<OrderStatus> cancelled) {
		this.cancelled = cancelled;
	}

	public List<OrderStatus> getCompleted() {
		return completed;
	}

	public void setCompleted(List<OrderStatus> completed) {
		this.completed = completed;
	}

}
