package com.hospital.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity
public class Patient {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	@NotNull(message = "name shouldn't be null")
	@NotBlank(message = "name shouldn't be blank")
	private String name;
	@NotNull(message = "age shouldn't be null")
    private int age;
	@NotNull(message = "gender shouldn't be null")
	@NotBlank(message = "gender shouldn't be blank")
	private String gender;
	@NotNull(message = "mobile shouldn't be null")
	@NotBlank(message = "mobile shouldn't be blank")
	@Pattern(regexp = "^\\d{10}$",message = "invalid mobile number entered ")
	private String mobile;
	
	public Patient() {
		
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
}
