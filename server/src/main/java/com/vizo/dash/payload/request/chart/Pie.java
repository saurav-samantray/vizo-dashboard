package com.vizo.dash.payload.request.chart;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class Pie {
    private String name;
    private String field;
    private long datasourceId;
    private long dashboardId;
}
