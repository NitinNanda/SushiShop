package com.coding.sushi.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "sushi")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Sushi implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "time_to_make")
	private Integer timeToMake;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getTimeToMake() {
		return timeToMake;
	}

	public void setTimeToMake(Integer timeToMake) {
		this.timeToMake = timeToMake;
	}

	@Override
	public String toString() {
		return "Sushi [id=" + id + ", name=" + name + ", timeToMake=" + timeToMake + "]";
	}

}
