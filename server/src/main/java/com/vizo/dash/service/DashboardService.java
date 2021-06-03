package com.vizo.dash.service;

import com.vizo.dash.model.Dashboard;
import com.vizo.dash.payload.request.DashboardRequest;
import com.vizo.dash.payload.response.DashboardResponse;

import java.util.List;

public interface DashboardService {
    public List<Dashboard> findAll();
    public Dashboard findById(long id);
    public DashboardResponse fetchDashboard(Long id);
    public DashboardResponse save(DashboardRequest dashboardRequest);

}
