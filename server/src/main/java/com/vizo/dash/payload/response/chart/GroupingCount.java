package com.vizo.dash.payload.response.chart;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class GroupingCount {
    private String name;
    private long value;
}
