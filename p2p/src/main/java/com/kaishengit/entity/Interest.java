package com.kaishengit.entity;

public class Interest {

	//已领取
	public static final int INTEREST_STATE_HAD_SEND = 1;
	//未到期
	public static final int INTEREST_STATE_NOT_DATE = 2;
	//未领取
	public static final int INTEREST_STATE_NOT_SEND = 3;
	
	private int id;

	private int investId;
	private int custId;
	private int employeeId;
	private String interestsendday;
	private double interrestmoney;
	private int state = 2;
	private String sendtime;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public String getInterestsendday() {
		return interestsendday;
	}

	public void setInterestsendday(String interestsendday) {
		this.interestsendday = interestsendday;
	}

	public double getInterrestmoney() {
		return interrestmoney;
	}

	public void setInterrestmoney(double interrestmoney) {
		this.interrestmoney = interrestmoney;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getSendtime() {
		return sendtime;
	}

	public void setSendtime(String sendtime) {
		this.sendtime = sendtime;
	}

	public int getInvestId() {
		return investId;
	}

	public void setInvestId(int investId) {
		this.investId = investId;
	}

	public int getCustId() {
		return custId;
	}

	public void setCustId(int custId) {
		this.custId = custId;
	}
}
