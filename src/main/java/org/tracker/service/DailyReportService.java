package org.tracker.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.annotation.PostConstruct;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.tracker.model.CovidModel;
import org.tracker.model.CovidStats;

@Service
public class DailyReportService {

	private static final String CONFIRMED_URL = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";

	private static final String DEATH_URL = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_deaths_global.csv";

	private static final String RECOVERED_URL = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_recovered_global.csv";

	public List<CovidStats> confirmedStats = new CopyOnWriteArrayList<>();

	public List<CovidStats> deathStats = new CopyOnWriteArrayList<>();

	public List<CovidStats> recoveredStats = new CopyOnWriteArrayList<>();

	public List<CovidModel> consolidateList = new ArrayList<CovidModel>();

	public Set<String> countries = new HashSet<>();

	@PostConstruct
	public void countries() throws Exception {

		Set<String> countries = new HashSet<String>();
		URL url = new URL(CONFIRMED_URL);
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("GET");
		int responseCode = con.getResponseCode();

		if (responseCode == HttpURLConnection.HTTP_OK) {

			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			Iterable<CSVRecord> records = CSVFormat.RFC4180.withHeader().parse(in);
			for (CSVRecord record : records) {

				countries.add(record.get(1));

			}
			this.countries = countries;
			in.close();
		}
	}

	public Set<String> getCountries() {
		return countries;
	}

	@PostConstruct
	@Scheduled(fixedDelay = 10800000)
	public void fetchConfirmed() throws Exception {

		URL obj = new URL(CONFIRMED_URL);
		List<CovidStats> stats = new ArrayList<CovidStats>();
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("GET");
		int responseCode = con.getResponseCode();
		System.out.println("GET Response Code :: " + responseCode);
		if (responseCode == HttpURLConnection.HTTP_OK) {
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			Iterable<CSVRecord> records = CSVFormat.RFC4180.withHeader().parse(in);
			for (CSVRecord record : records) {

				CovidStats cs = new CovidStats();
				String state = record.get(0);
				String country = record.get(1);
				String totalCases = record.get(record.size() - 1);
				String totalCasesPreviousDay = record.get(record.size() - 2);
				cs.setState(state);
				cs.setCountry(country);
				if (!totalCases.equals("")) {
					cs.setTotalCases(Integer.parseInt(totalCases));
					cs.setChangesSinceLastDay(Integer.parseInt(totalCases) - Integer.parseInt(totalCasesPreviousDay));
				} else {
					cs.setTotalCases(0);
					cs.setChangesSinceLastDay(0);
				}
				stats.add(cs);

			}
			this.confirmedStats = stats;
			in.close();
		} else {
			System.out.println("GET request not worked");
		}

	}

	@PostConstruct
	@Scheduled(fixedDelay = 10800000)
	public void fetchDeaths() throws Exception {

		URL obj = new URL(DEATH_URL);
		List<CovidStats> stats = new ArrayList<CovidStats>();
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("GET");
		int responseCode = con.getResponseCode();
		System.out.println("GET Response Code :: " + responseCode);
		if (responseCode == HttpURLConnection.HTTP_OK) {
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			Iterable<CSVRecord> records = CSVFormat.RFC4180.withHeader().parse(in);
			for (CSVRecord record : records) {

				CovidStats cs = new CovidStats();
				String state = record.get(0);
				String country = record.get(1);
				String totalCases = record.get(record.size() - 1);
				String totalCasesPreviousDay = record.get(record.size() - 2);
				cs.setState(state);
				cs.setCountry(country);
				if (!totalCases.equals("")) {
					cs.setTotalCases(Integer.parseInt(totalCases));
					cs.setChangesSinceLastDay(Integer.parseInt(totalCases) - Integer.parseInt(totalCasesPreviousDay));
				} else {
					cs.setTotalCases(0);
					cs.setChangesSinceLastDay(0);
				}
				stats.add(cs);

			}
			this.deathStats = stats;
			in.close();
		} else {
			System.out.println("GET request not worked");
		}

	}

	@PostConstruct
	@Scheduled(fixedDelay = 10800000)
	public void fetchRecovered() throws Exception {

		URL obj = new URL(RECOVERED_URL);
		List<CovidStats> stats = new ArrayList<CovidStats>();
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("GET");
		int responseCode = con.getResponseCode();
		System.out.println("GET Response Code :: " + responseCode);
		if (responseCode == HttpURLConnection.HTTP_OK) {
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			Iterable<CSVRecord> records = CSVFormat.RFC4180.withHeader().parse(in);
			for (CSVRecord record : records) {

				CovidStats cs = new CovidStats();
				String state = record.get(0);
				String country = record.get(1);
				String totalCases = record.get(record.size() - 1);
				String totalCasesPreviousDay = record.get(record.size() - 2);
				cs.setState(state);
				cs.setCountry(country);
				if (!totalCases.equals("")) {
					cs.setTotalCases(Integer.parseInt(totalCases));
					cs.setChangesSinceLastDay(Integer.parseInt(totalCases) - Integer.parseInt(totalCasesPreviousDay));
				} else {
					cs.setTotalCases(0);
					cs.setChangesSinceLastDay(0);
				}
				stats.add(cs);

			}
			this.recoveredStats = stats;
			in.close();
		} else {
			System.out.println("GET request not worked");
		}

	}

	public List<CovidStats> getRecoveredStats() {
		return recoveredStats;
	}

	public List<CovidModel> getConsolidateList() {
		return consolidateList;
	}

	public void setConsolidateList(List<CovidModel> consolidateList) {
		this.consolidateList = consolidateList;
	}

	public List<CovidStats> getDeathStats() {
		return deathStats;
	}

	public List<CovidStats> getConfirmedStats() {
		return confirmedStats;
	}

	public int fetchTotalCasesPerCountry(String country, List<CovidStats> stats) {

		int totalCases = 0;
		for (CovidStats c : stats) {

			if (c.getCountry().equalsIgnoreCase(country)) {

				totalCases += c.getTotalCases();
			}

		}

		return totalCases;

	}

	public int fetchChangesSinceLastDayPerCountry(String country, List<CovidStats> stats) {

		int totalChanges = 0;
		for (CovidStats c : stats) {

			if (c.getCountry().equalsIgnoreCase(country)) {

				totalChanges += c.getChangesSinceLastDay();

			}

		}

		return totalChanges;

	}

	public List<CovidStats> arrangeConfirmed() {

		List<CovidStats> confirmedCases = new ArrayList<>();
		Set<String> countries = this.getCountries();
		for (String s : countries) {

			CovidStats c = new CovidStats();
			c.setCountry(s);
			c.setTotalCases(fetchTotalCasesPerCountry(s, getConfirmedStats()));
			c.setChangesSinceLastDay(fetchChangesSinceLastDayPerCountry(s, getConfirmedStats()));
			confirmedCases.add(c);

		}

		return confirmedCases;
	}

	public List<CovidStats> arrangeDeaths() {

		List<CovidStats> deathCases = new ArrayList<>();
		Set<String> countries = this.getCountries();
		for (String s : countries) {

			CovidStats c = new CovidStats();
			c.setCountry(s);
			c.setTotalCases(fetchTotalCasesPerCountry(s, getDeathStats()));
			c.setChangesSinceLastDay(fetchChangesSinceLastDayPerCountry(s, getDeathStats()));
			deathCases.add(c);

		}

		return deathCases;
	}

	public List<CovidStats> arrangeRecovered() {

		List<CovidStats> recoveredCases = new ArrayList<>();
		Set<String> countries = this.getCountries();
		for (String s : countries) {

			CovidStats c = new CovidStats();
			c.setCountry(s);
			c.setTotalCases(fetchTotalCasesPerCountry(s, getRecoveredStats()));
			c.setChangesSinceLastDay(fetchChangesSinceLastDayPerCountry(s, getRecoveredStats()));
			recoveredCases.add(c);

		}

		return recoveredCases;
	}

	public void fillConfirmedDataToConsolidated() {

		this.arrangeConfirmed().forEach(c -> {

			CovidModel cm = findByCountry(c.getCountry());
			if (cm == null) {

				CovidModel cm1 = new CovidModel();
				cm1.setCountry(c.getCountry());
				Map<Integer, Integer> kv = new HashMap<Integer, Integer>();
				kv.put(c.getTotalCases(), c.getChangesSinceLastDay());
				cm1.setConfirmed(kv);
				this.consolidateList.add(cm1);

			} else {

				Map<Integer, Integer> kv = new HashMap<Integer, Integer>();
				kv.put(c.getTotalCases(), c.getChangesSinceLastDay());
				cm.setConfirmed(kv);
			}

		});

		System.out.println("Confirmed Data Filled!");

	}

	public void fillDeathDataToConsolidated() {

		this.arrangeDeaths().forEach(c -> {

			CovidModel cm = findByCountry(c.getCountry());
			if (cm == null) {

				CovidModel cm1 = new CovidModel();
				cm1.setCountry(c.getCountry());
				Map<Integer, Integer> kv = new HashMap<Integer, Integer>();
				kv.put(c.getTotalCases(), c.getChangesSinceLastDay());
				cm1.setDeaths(kv);
				this.consolidateList.add(cm1);

			} else {

				Map<Integer, Integer> kv = new HashMap<Integer, Integer>();
				kv.put(c.getTotalCases(), c.getChangesSinceLastDay());
				cm.setDeaths(kv);
			}

		});
		System.out.println("Deaths Data Filled!");

	}

	public void fillRecoveredDataToConsolidated() {

		this.arrangeRecovered().forEach(c -> {

			CovidModel cm = findByCountry(c.getCountry());
			if (cm == null) {

				CovidModel cm1 = new CovidModel();
				cm1.setCountry(c.getCountry());
				Map<Integer, Integer> kv = new HashMap<Integer, Integer>();
				kv.put(c.getTotalCases(), c.getChangesSinceLastDay());
				cm1.setRecovered(kv);
				this.consolidateList.add(cm1);

			} else {

				Map<Integer, Integer> kv = new HashMap<Integer, Integer>();
				kv.put(c.getTotalCases(), c.getChangesSinceLastDay());
				cm.setRecovered(kv);
			}

		});
		System.out.println("Recovered Data Filled!");

	}

	public CovidModel findByCountry(String country) {

		for (CovidModel c : consolidateList) {

			if (c.getCountry().equalsIgnoreCase(country)) {

				return c;
			}

		}

		return null;

	}

	public void calculateRecoveryRate(CovidModel model) {

		double recovered = 0;
		double confirmed = 0;

		for (Map.Entry<Integer, Integer> r : model.getRecovered().entrySet()) {

			recovered = r.getKey();

		}
		for (Map.Entry<Integer, Integer> c : model.getConfirmed().entrySet()) {

			confirmed = c.getKey();

		}
		double recoveryRate = (recovered / confirmed ) *100;
		BigDecimal d = new BigDecimal(recoveryRate).setScale(2, RoundingMode.HALF_UP);
		model.setRecoveryRate(d.doubleValue());
	}

}
