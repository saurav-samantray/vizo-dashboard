package com.vizo.dash.service.impl;

import com.vizo.dash.model.Dashboard;
import com.vizo.dash.payload.request.DashboardRequest;
import com.vizo.dash.payload.response.DashboardResponse;
import com.vizo.dash.repository.DashboardRepository;
import com.vizo.dash.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class DashboardServiceImpl implements DashboardService {

    @Autowired
    DashboardRepository repository;

    @Override
    public List<Dashboard> findAll() {
        return repository.findAll();
    }

    @Override
    public Dashboard findById(long id) {
        return repository.getById(id);
    }

    @Override
    public DashboardResponse fetchDashboard(Long id) {
        return generateResponse(repository.getById(id));
    }

    @Override
    public DashboardResponse save(DashboardRequest dashboardRequest) {
        Dashboard dashboard = new Dashboard();
        dashboard.setName(dashboardRequest.getName());
        repository.save(dashboard);

        //Generate Response object
        return generateResponse(dashboard);
    }

    private DashboardResponse generateResponse(Dashboard dashboard){
        DashboardResponse response = new DashboardResponse();
        response.setName(dashboard.getName());
        return response;
    }
}
