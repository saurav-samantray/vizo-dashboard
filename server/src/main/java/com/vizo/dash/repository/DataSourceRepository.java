package com.vizo.dash.repository;

import com.vizo.dash.model.DataSource;
import com.vizo.dash.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DataSourceRepository extends JpaRepository<DataSource,Long> {

    public List<DataSource> findByUser(User user);
}
