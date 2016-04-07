/***************************************************************************
 * 							tungtt							               *    
 **************************************************************************/
package com.chotoxautinh;

import java.time.LocalDate;
import java.util.List;

/**
 *  Author : tungtt         
 * Apr 7, 2016
 */
public class test {
	public enum Gender {
		MALE, FEMALE
	}
	
	String name;
	LocalDate birthday;
	Gender gender;
	String email;
	
	public int getAge() {
		return 1;
	}
	
	public void printPerson(String name) {
		System.out.println(name);
	}
	
	public static void printPersonOlder(List<test> person, int age) {
		for(test p:person) {
			if(p.getAge() >= age) {
				p.printPerson("abc");
			}
		}
	}
}
