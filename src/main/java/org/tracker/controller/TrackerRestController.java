package org.tracker.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tracker.model.CovidModel;
import org.tracker.model.CovidStats;
import org.tracker.service.DailyReportService;

@RestController
@RequestMapping("/angular")
public class TrackerRestController {

	@Autowired
	private DailyReportService reportService;

	@GetMapping("/confirmed")
	public ResponseEntity<?> getConfirmedCases() {

		List<CovidStats> confirmedCases = reportService.arrangeConfirmed();
		Collections.sort(confirmedCases);
		return new ResponseEntity<List<CovidStats>>(confirmedCases, HttpStatus.OK);

	}

	@GetMapping("/deaths")
	public ResponseEntity<?> getDeathCases() {

		List<CovidStats> deathCases = reportService.arrangeDeaths();
		Collections.sort(deathCases);
		return new ResponseEntity<List<CovidStats>>(deathCases, HttpStatus.OK);

	}

	@GetMapping("/recovered")
	public ResponseEntity<?> getRecoveredCases() {

		List<CovidStats> recoveredCases = reportService.arrangeRecovered();
		Collections.sort(recoveredCases);
		return new ResponseEntity<List<CovidStats>>(recoveredCases, HttpStatus.OK);

	}

	@GetMapping("/consolidatedList")
	public ResponseEntity<?> getConsolidateList() {

		List<CovidModel> list = reportService.getConsolidateList();
		reportService.fillConfirmedDataToConsolidated();
		reportService.fillRecoveredDataToConsolidated();
		reportService.fillDeathDataToConsolidated();
		list.forEach(c -> {
			reportService.calculateRecoveryRate(c);
		});
		Collections.sort(list);
		return new ResponseEntity<List<CovidModel>>(list, HttpStatus.OK);

	}
}
