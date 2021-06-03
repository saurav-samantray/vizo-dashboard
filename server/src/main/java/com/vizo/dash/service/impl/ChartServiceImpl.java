package com.vizo.dash.service.impl;

import com.vizo.dash.exception.ChartNotFoundException;
import com.vizo.dash.exception.DataSourceNotFoundException;
import com.vizo.dash.model.chart.BaseChart;
import com.vizo.dash.model.chart.PieChart;
import com.vizo.dash.payload.request.chart.Pie;
import com.vizo.dash.repository.chart.ChartRepository;
import com.vizo.dash.repository.chart.PieChartRepository;
import com.vizo.dash.service.ChartService;
import com.vizo.dash.service.DashboardService;
import com.vizo.dash.service.DatasourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ChartServiceImpl implements ChartService {

    @Autowired
    PieChartRepository pieChartRepository;

    @Autowired
    ChartRepository chartRepository;

    @Autowired
    DatasourceService dataSourceService;

    @Autowired
    DashboardService dashboardService;

    @Override
    public List<BaseChart> getAll() {
        return chartRepository.findAll();
    }

    @Override
    public PieChart savePie(Pie pie) throws DataSourceNotFoundException {

        PieChart chart = new PieChart();
        chart.setField(pie.getField());
        chart.setName(pie.getName());
        chart.setDatasource(dataSourceService.get(pie.getDatasourceId()));
        chart.setDashboard(dashboardService.findById(pie.getDashboardId()));
        pieChartRepository.save(chart);

        return chart;
    }

    @Override
    public PieChart findPie(Long id) throws ChartNotFoundException {
        return pieChartRepository.findById(id).orElseThrow(() -> new ChartNotFoundException());
    }
}
