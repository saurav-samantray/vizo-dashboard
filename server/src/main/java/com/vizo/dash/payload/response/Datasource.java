package com.vizo.dash.payload.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Datasource {
    private String name;
    private String type;
    private boolean connected;
    private long queries;
}
