package com.vizo.dash.datasource.utility;

import com.vizo.dash.model.DataSource;
import com.vizo.dash.model.chart.PieChart;
import com.vizo.dash.payload.response.chart.PieChartDataResponse;

import java.util.Set;

public interface DatasourceUtility {
    PieChartDataResponse generatePieChartData(PieChart pie);
    Set<String> keys(DataSource ds);
}
