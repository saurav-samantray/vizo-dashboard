package com.vizo.dash.service;

import com.vizo.dash.config.DataSourceUtilityRegistry;
import com.vizo.dash.exception.ChartNotFoundException;
import com.vizo.dash.exception.DataSourceNotFoundException;
import com.vizo.dash.model.DataSource;
import com.vizo.dash.model.chart.PieChart;
import com.vizo.dash.payload.request.chart.Pie;
import com.vizo.dash.payload.response.chart.PieChartDataResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChartDataService {
    @Autowired
    DatasourceService dsService;

    @Autowired
    ChartService chartService;

    @Autowired
    DataSourceUtilityRegistry registry;

    public PieChartDataResponse generatePieChart(long id) throws DataSourceNotFoundException, ChartNotFoundException {
        PieChart pc = chartService.findPie(id);
        //DataSource ds = pc.getDatasource();
        return  registry
                    .getService(pc.getDatasource().getType().toString())
                    .generatePieChartData(pc);
        //return utils.performSingleAggregation(ds.getConnectionDetails(),ds.getDatabaseName(),ds.getCollection(),pc.getField());

    }

    public PieChartDataResponse previewPie(Pie pie) throws DataSourceNotFoundException{
        DataSource ds = dsService.get(pie.getDatasourceId());
        PieChart pc = new PieChart();
        pc.setName(pie.getName());
        pc.setField(pie.getField());
        pc.setDatasource(ds);
        return  registry
                .getService(pc.getDatasource().getType().toString())
                .generatePieChartData(pc);
        //return utils.performSingleAggregation(ds.getConnectionDetails(),ds.getDatabaseName(),ds.getCollection(),pie.getField());
    }
}
