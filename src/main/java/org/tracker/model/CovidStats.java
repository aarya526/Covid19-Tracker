package org.tracker.model;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class CovidStats implements Comparable<CovidStats> {

	private String state;
	private String country;
	private int totalCases;
	private int changesSinceLastDay;

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public int getTotalCases() {
		return totalCases;
	}

	public void setTotalCases(int totalCases) {
		this.totalCases = totalCases;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public int getChangesSinceLastDay() {
		return changesSinceLastDay;
	}

	public void setChangesSinceLastDay(int changesSinceLastDay) {
		this.changesSinceLastDay = changesSinceLastDay;
	}

	public String toString() {

		return "[ Province/State : " + state + ", Country/Region : " + country + ", Total Cases : " + totalCases
				+ ", Changes Since Last Day : " + changesSinceLastDay + " ]";

	}

	@Override
	public int compareTo(CovidStats o) {

		if (this.totalCases == o.getTotalCases()) {

			return 0;
		} else if (this.totalCases < o.getTotalCases()) {
			return 1;

		} else {
			return -1;
		}
	}

}
