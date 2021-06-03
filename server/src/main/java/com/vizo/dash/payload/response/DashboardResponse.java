package com.vizo.dash.payload.response;

import com.vizo.dash.model.chart.BaseChart;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class DashboardResponse {
    private String name;
    private List<BaseChart> charts;
}
