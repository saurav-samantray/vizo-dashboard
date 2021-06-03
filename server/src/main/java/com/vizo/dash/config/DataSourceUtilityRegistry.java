package com.vizo.dash.config;

import com.vizo.dash.datasource.utility.DatasourceUtility;

public interface DataSourceUtilityRegistry {
    public DatasourceUtility getService(String serviceName);
}
