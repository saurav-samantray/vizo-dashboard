package com.vizo.dash.controller;


import com.vizo.dash.exception.DataSourceNotFoundException;
import com.vizo.dash.model.DataSource;
import com.vizo.dash.model.DatasourceType;
import com.vizo.dash.service.DatasourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/api/datasources")
public class DataSourceController {

    @Autowired
    private DatasourceService service;

    @PostMapping("")
    public ResponseEntity<String> createDatasource(@RequestBody DataSource datasource) {
        service.save(datasource);
        return new ResponseEntity<String>("",HttpStatus.CREATED);
    }

    @GetMapping("")
    public ResponseEntity<List> availableDatasources() {
        return new ResponseEntity<List>(service.listAll(),HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DataSource> updateDatasource(@RequestBody DataSource datasource, @PathVariable long id) throws DataSourceNotFoundException {
        return new ResponseEntity<DataSource>(service.update(datasource,id),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> removeDatasource(@PathVariable long id) {
        service.delete(id);
        return new ResponseEntity<String>("",HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}/keys")
    public ResponseEntity<List> fetchCollectionKeys(@PathVariable long id) {
        return new ResponseEntity<List>(service.getkeys(id),HttpStatus.OK);
    }

    @GetMapping("/types")
    public ResponseEntity<DatasourceType[]> availableDatasourcesTypes() {
        return new ResponseEntity<DatasourceType[]>(DatasourceType.values(),HttpStatus.OK);
    }
}
