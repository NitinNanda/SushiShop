package com.coding.sushi.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "sushi_order")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class SushiOrder implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "status_id")
	private Integer statusId;

	@Column(name = "sushi_id")
	private Integer sushiId;

	@Column(name = "createdAt")
	private LocalDateTime createdAt;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getStatusId() {
		return statusId;
	}

	public void setStatusId(Integer statusId) {
		this.statusId = statusId;
	}

	public Integer getSushiId() {
		return sushiId;
	}

	public void setSushiId(Integer sushiId) {
		this.sushiId = sushiId;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	@Override
	public String toString() {
		return "SushiOrder [id=" + id + ", statusId=" + statusId + ", sushiId=" + sushiId + ", createdAt=" + createdAt
				+ "]";
	}

}
