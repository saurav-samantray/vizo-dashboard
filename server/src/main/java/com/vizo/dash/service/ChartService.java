package com.vizo.dash.service;

import com.vizo.dash.exception.ChartNotFoundException;
import com.vizo.dash.exception.DataSourceNotFoundException;
import com.vizo.dash.model.chart.BaseChart;
import com.vizo.dash.model.chart.PieChart;
import com.vizo.dash.payload.request.chart.Pie;
import com.vizo.dash.payload.response.chart.PieChartDataResponse;

import java.util.List;

public interface ChartService {

    List<BaseChart> getAll();

    PieChart savePie(Pie pie) throws DataSourceNotFoundException;

    PieChart findPie(Long id) throws DataSourceNotFoundException, ChartNotFoundException;
}
