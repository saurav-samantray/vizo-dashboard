package com.vizo.dash.repository;

import com.vizo.dash.model.Dashboard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DashboardRepository extends JpaRepository<Dashboard, Long> {
}
