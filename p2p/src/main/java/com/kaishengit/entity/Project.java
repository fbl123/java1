package com.kaishengit.entity;

import java.sql.Timestamp;
import java.util.Date;

public class Project {
	/*项目状态 正常*/
	public static final int PROJECT_STATE_NORMAL = 1;
	
	/*项目状态 完成*/
	public static final int PROJECT_STATE_COMPLETE = 2;
	
	private int id;
	private String projectName;
	private double repayRate;
	private double sumMoney;
	private double remainMoney;
	private int month;
	private String startDate;
	private String endDate;
	private Timestamp createTime;
	private int state = 1;
	private String remark;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public double getRepayRate() {
		return repayRate;
	}

	public void setRepayRate(double repayRate) {
		this.repayRate = repayRate;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public double getRemainMoney() {
		return remainMoney;
	}

	public void setRemainMoney(double remainMoney) {
		this.remainMoney = remainMoney;
	}

	public double getSumMoney() {
		return sumMoney;
	}

	public void setSumMoney(double sumMoney) {
		this.sumMoney = sumMoney;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
