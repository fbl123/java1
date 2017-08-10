package com.kaishengit;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class Test {

	
	public static void main(String[] args) {
	/*	DateTimeFormatter matter = DateTimeFormat.forPattern("yyyy-MM-dd");
		DateTime dt = DateTime.parse("2017-06-10");
		DateTime dt2 = dt.plusMonths(1);
		System.out.println(dt2.toString(matter));
		
		DateTime dt3 = new DateTime("2017-06-02");
		System.out.println(dt3.toString("yyyy-MM-dd"));
		int days = Days.daysBetween(dt, dt3).getDays();
		System.out.println(days);
		
		DateTime dt4 = new DateTime();
		int days1 = Days.daysBetween(dt3,dt4).getDays();
		System.out.println(days1);*/
		
		/*DateTime dt = new DateTime();//当前日期2017-06-26
		DateTime dt2 = new DateTime("2017-05-24");
		int days = Days.daysBetween(dt2,dt).getDays();
		System.out.println(days);*/
		
		// /emp/list/list/list
		String regex = "(/{1}\\w+)+";
		System.out.println("/emp/list".matches(regex));
		System.out.println("/emp/list/123".matches(regex));
		System.out.println("//emp/list".matches(regex));
		System.out.println("///emp/list".matches(regex));
		System.out.println("/emp/list/".matches(regex));
		
		
	}
}
