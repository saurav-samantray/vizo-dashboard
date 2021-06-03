package com.vizo.dash.dto.chart;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PieChartDto extends BaseChartDto{
    private String field;
}
