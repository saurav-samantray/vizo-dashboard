package com.vizo.dash.controller;

import com.vizo.dash.exception.ChartNotFoundException;
import com.vizo.dash.exception.DataSourceNotFoundException;
import com.vizo.dash.model.Dashboard;
import com.vizo.dash.payload.request.DashboardRequest;
import com.vizo.dash.payload.response.DashboardResponse;
import com.vizo.dash.service.DashboardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/api/dashboards")
@Slf4j
public class DashboardController {

    @Autowired
    DashboardService service;

    @GetMapping("")
    public ResponseEntity<List<Dashboard>> fetchAllDashboard() {
        return new ResponseEntity<>(service.findAll(),HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<DashboardResponse> createDashboard(@RequestBody DashboardRequest request){
        return new ResponseEntity<>(service.save(request), HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<DashboardResponse> fetchDashboard(@PathVariable Long id) throws DataSourceNotFoundException, ChartNotFoundException {
        return new ResponseEntity<>(service.fetchDashboard(id),HttpStatus.OK);
    }
}
