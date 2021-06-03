package com.vizo.dash.controller;

import com.vizo.dash.exception.ChartNotFoundException;
import com.vizo.dash.exception.DataSourceNotFoundException;
import com.vizo.dash.model.chart.BaseChart;
import com.vizo.dash.model.chart.PieChart;
import com.vizo.dash.payload.request.chart.Pie;
import com.vizo.dash.payload.response.chart.PieChartDataResponse;
import com.vizo.dash.service.ChartDataService;
import com.vizo.dash.service.ChartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/api/charts")
@Slf4j
public class ChartController {

    @Autowired
    ChartService chartService;

    @Autowired
    ChartDataService chartDataService;

    @GetMapping("")
    public ResponseEntity<List<BaseChart>> getCharts() {
        return new ResponseEntity<>(chartService.getAll(),HttpStatus.OK);
    }

    @PostMapping("pie")
    public ResponseEntity<PieChart> createPie(@RequestBody Pie pie) throws DataSourceNotFoundException {
        return new ResponseEntity<PieChart>(chartService.savePie(pie),HttpStatus.CREATED);
    }

    @GetMapping("pie/{id}")
    public ResponseEntity<PieChartDataResponse> generatePieChartData(@PathVariable Long id) throws DataSourceNotFoundException, ChartNotFoundException {
        return new ResponseEntity<>(chartDataService.generatePieChart(id),HttpStatus.OK);
    }

    @PostMapping("pie/preview")
    public ResponseEntity<PieChartDataResponse> previewPie(@RequestBody Pie pie) throws DataSourceNotFoundException, InterruptedException {
        Thread.sleep(4000);
        return new ResponseEntity<>(chartDataService.previewPie(pie),HttpStatus.OK);
    }
}
