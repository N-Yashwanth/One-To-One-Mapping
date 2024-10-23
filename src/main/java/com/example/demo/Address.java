package com.example.demo;

import java.util.Objects;

import jakarta.persistence.*;

@Entity
public class Address {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String city;
	public Address(int id, String city) {
		super();
		this.id = id;
		this.city = city;
	}
	public Address() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	@Override
	public boolean equals(Object obj) {
		if(this==obj) {
			return true;
		}
		if(obj==null || getClass()!=obj.getClass()) {
			return false;
		}
		Address address=(Address)obj;
		return Objects.equals(id, address.id);
	}
	@Override
	public int hashCode() {
		return Objects.hashCode(id);
	}
}
