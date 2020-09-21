package org.tracker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.tracker.model.CovidStats;
import org.tracker.service.DailyReportService;

@Controller
public class HomeController {

	@Autowired
	private DailyReportService reportService;

	@GetMapping("/")
	public String index(Model model) {

		// List<CovidStats> stats = reportService.getConfirmedStats();
		// // Collections.sort(stats);
		// model.addAttribute("stats", stats);
		// int total = reportService.getConfirmedStats().stream().mapToInt(cs ->
		// cs.getTotalCases()).sum();
		// int totalChange = reportService.getConfirmedStats().stream().mapToInt(cs ->
		// cs.getChangesSinceLastDay()).sum();
		// model.addAttribute("totalReported", total);
		// model.addAttribute("totalChange", totalChange);
		return "index";

	}

}
