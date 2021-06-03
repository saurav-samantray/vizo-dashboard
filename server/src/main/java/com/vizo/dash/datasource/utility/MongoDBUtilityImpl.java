package com.vizo.dash.datasource.utility;

import com.mongodb.Block;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Accumulators;
import com.mongodb.client.model.Sorts;
import com.vizo.dash.model.DataSource;
import com.vizo.dash.model.chart.PieChart;
import com.vizo.dash.payload.response.chart.GroupingCount;
import com.vizo.dash.payload.response.chart.PieChartDataResponse;
import org.bson.Document;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Set;

import static com.mongodb.client.model.Aggregates.group;
import static com.mongodb.client.model.Aggregates.sort;

@Service("MONGODB")
public class MongoDBUtilityImpl implements DatasourceUtility{

    private MongoDatabase getDatabase(String connection, String database){
        MongoClient mongo = MongoClients.create(connection);
        MongoDatabase db = mongo.getDatabase(database);
        return db;
    }

    @Override
    public PieChartDataResponse generatePieChartData(PieChart pie) {
        DataSource dataSource = pie.getDatasource();
        MongoClient mongo = MongoClients.create(dataSource.getConnectionDetails());
        MongoDatabase db = mongo.getDatabase(dataSource.getDatabaseName());

        //final GroupingCount count = new GroupingCount();
        final PieChartDataResponse response = new PieChartDataResponse();
        //PieChartDataResponse.Option.Entry entry = new PieChartDataResponse.Option.Entry();

        db.getCollection(dataSource.getCollection())
                .aggregate(Arrays.asList(
                        group("$"+pie.getField(), Accumulators.sum("count", 1)),
                        sort(Sorts.descending("count")))
                        //project().andExclude("_id").and("_id").as("name")
                ).forEach(new Block<Document>() {
            @Override
            public void apply(final Document document) {
                response.setName(pie.getName());
                response
                        .getOption()
                        .getSeries()
                        .get(0)
                        .getData()
                        .add(new GroupingCount((String)document.get("_id"),(Integer) document.get("count")));
                //count.put((Long) document.get("_id"), (Integer) document.get("count"));
            }
        });

        mongo.close();
        return response;
    }

    @Override
    public Set<String> keys(DataSource ds) {
        MongoClient mongo = MongoClients.create(ds.getConnectionDetails());
        MongoDatabase db = mongo.getDatabase(ds.getDatabaseName());
        return db.getCollection(ds.getCollection()).find().first().keySet();
    }
}
