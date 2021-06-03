package com.vizo.dash.service;

import com.vizo.dash.exception.DataSourceNotFoundException;
import com.vizo.dash.model.DataSource;
import com.vizo.dash.model.User;
import com.vizo.dash.repository.DataSourceRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@Slf4j
public class DatasourceService {

    @Autowired
    private DataSourceRepository repo;

    @Autowired
    private UserService userService;

    public List<DataSource> listAll() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userName = auth.getName();

        return repo.findByUser(userService.findOne(userName));
    }

    public void save(DataSource datasource) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userName = auth.getName();
        User user = userService.findOne(userName);

        datasource.setUser(user);

        repo.save(datasource);
    }

    public DataSource get(Long id) throws DataSourceNotFoundException{

        return repo.findById(id).orElseThrow(() -> new DataSourceNotFoundException());
    }

    public List<String> getkeys(Long id){
        List<String> keys = new ArrayList<>();
        keys.add("clickType");
        keys.add("userName");
        //DataSource datasource = repo.findById(id).get();

        return keys;
    }

    public void delete(long id) {
        repo.deleteById(id);
    }

    public DataSource update(DataSource datasource, long id) throws DataSourceNotFoundException{
        return repo.findById(id).map(ds -> {
            ds.setName(datasource.getName());
            ds.setConnectionDetails(datasource.getConnectionDetails());
            ds.setCollection(datasource.getCollection());
            ds.setDatabaseName(datasource.getDatabaseName());
            ds.setActive(datasource.getActive());
            ds.setConnected(datasource.getConnected());
            ds.setType(datasource.getType());
            return repo.save(ds);
        }).orElseThrow(() -> new DataSourceNotFoundException());
    }
}
