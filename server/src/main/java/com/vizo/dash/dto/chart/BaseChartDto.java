package com.vizo.dash.dto.chart;

import com.vizo.dash.model.DataSource;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BaseChartDto {
    private String name;
    private DataSource datasource;
}
