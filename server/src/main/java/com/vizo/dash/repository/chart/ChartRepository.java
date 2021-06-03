package com.vizo.dash.repository.chart;

import com.vizo.dash.model.chart.BaseChart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface ChartRepository<T extends BaseChart> extends JpaRepository<T, Long> {
}
