/**
 * 
 */
package com.gubs.interviewquestions;

import java.util.HashMap;
import java.util.Map;

/**
 * @author gubs
 *
 */
public enum EnumWeekDays {
	SUNDAY(0, "SUNDAY"),
	MONDAY(1, "MONDAY"),
	TUESDAY(2, "TUESDAY");
	
	private int weekId;
	private String weekDay;
	
	// Static block initialize / load when the class first execute
	private static Map<Integer, EnumWeekDays> weekDaysMap = new HashMap<Integer, EnumWeekDays>();
	
	static {
		for (EnumWeekDays weekDay : EnumWeekDays.values()) {
			weekDaysMap.put(weekDay.getWeekId(), weekDay);
		}
	}
	EnumWeekDays(Integer weekId, String weekDay) {
		this.weekDay = weekDay;
		this.weekId = weekId;
	}

	public String getWeekDays() {
		return weekDay;
	}

	public void setWeekDays(String weekDays) {
		this.weekDay = weekDays;
	}

	public int getWeekId() {
		return weekId;
	}

	public void setWeekId(int weekId) {
		this.weekId = weekId;
	}
	
	// Declare static method, so class can call directly 
	public static EnumWeekDays getByWeekId(Integer weekId) {
		return weekDaysMap.get(weekId);
	}
	
}
