package com.vizo.dash;

import com.mongodb.Block;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Accumulators;
import com.mongodb.client.model.Sorts;
import com.vizo.dash.payload.response.chart.GroupingCount;
import com.vizo.dash.payload.response.chart.PieChartDataResponse;
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;

import static com.mongodb.client.model.Aggregates.*;

@Component
@Slf4j
public class MongoDBUtils {

    private MongoDatabase getDatabase(String connection,String database){
        MongoClient mongo = MongoClients.create(connection);
        MongoDatabase db = mongo.getDatabase(database);
        return db;
    }

    public PieChartDataResponse performSingleAggregation(String connection, String database, String collection, String field){
        MongoClient mongo = MongoClients.create(connection);
        MongoDatabase db = mongo.getDatabase(database);

        //final GroupingCount count = new GroupingCount();
        final PieChartDataResponse response = new PieChartDataResponse();
        //PieChartDataResponse.Option.Entry entry = new PieChartDataResponse.Option.Entry();

        db.getCollection(collection)
            .aggregate(Arrays.asList(
                group("$"+field, Accumulators.sum("count", 1)),
                sort(Sorts.descending("count")))
                //project().andExclude("_id").and("_id").as("name")
            ).forEach(new Block<Document>() {
                @Override
                public void apply(final Document document) {
                    response.getOption().getSeries().get(0).getData().add(new GroupingCount((String)document.get("_id"),(Integer)document.get("count")));
                    //count.put((Long) document.get("_id"), (Integer) document.get("count"));
                }
            });

        mongo.close();
        return response;
    }
}
