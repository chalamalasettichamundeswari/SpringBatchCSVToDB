package com.springboot.batch.model;

public class Student {
	private int id;
	private String name;
	private double testscore;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getTestscore() {
		return testscore;
	}

	public void setTestscore(double testscore) {
		this.testscore = testscore;
	}

}
