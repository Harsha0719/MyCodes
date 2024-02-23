package com.flm.HibernateCRUD;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Student {
	@Id
	private String idno;
	private String name;
	private int age;
	private int year;
	private String branch;
	private char section;
	public String getIdno() {
		return idno;
	}
	public void setIdno(String idno) {
		this.idno = idno;
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
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}
	public char getSection() {
		return section;
	}
	public void setSection(char section) {
		this.section = section;
	}
	@Override
	public String toString() {
		return "Student [idno=" + idno + ", name=" + name + ", age=" + age + ", year=" + year + ", branch=" + branch
				+ ", section=" + section + "]";
	}
	
	
	
	
	
	
}