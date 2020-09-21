package org.tracker.model;

import java.util.Map;

public class CovidModel implements Comparable<CovidModel> {

	private String country;

	private Map<Integer, Integer> confirmed;
	private Map<Integer, Integer> deaths;
	private Map<Integer, Integer> recovered;
	private double recoveryRate = 0;

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Map<Integer, Integer> getConfirmed() {
		return confirmed;
	}

	public void setConfirmed(Map<Integer, Integer> confirmed) {
		this.confirmed = confirmed;
	}

	public Map<Integer, Integer> getDeaths() {
		return deaths;
	}

	public void setDeaths(Map<Integer, Integer> deaths) {
		this.deaths = deaths;
	}

	public Map<Integer, Integer> getRecovered() {
		return recovered;
	}

	public void setRecovered(Map<Integer, Integer> recovered) {
		this.recovered = recovered;
	}

	public int fetchConfirmed() {

		int num = 0;
		for (Map.Entry<Integer, Integer> entry : this.getConfirmed().entrySet()) {
			num = entry.getKey();

		}

		return num;

	}
	
	public int fetchRecovered() {

		int num = 0;
		for (Map.Entry<Integer, Integer> entry : this.getRecovered().entrySet()) {
			num = entry.getKey();

		}

		return num;

	}

	public double getRecoveryRate() {
		return recoveryRate;
	}

	public void setRecoveryRate(double recoveryRate) {
		this.recoveryRate = recoveryRate;
	}

	@Override
	public int compareTo(CovidModel o) {

		int num1 = 0;
		int num = fetchConfirmed();
		for (Map.Entry<Integer, Integer> entry : o.getConfirmed().entrySet()) {

			num1 = entry.getKey();
		}
		if (num == num1) {
			return 0;
		} else if (num < num1) {
			return 1;

		} else {
			return -1;
		}
	}

}
