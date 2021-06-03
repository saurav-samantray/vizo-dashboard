package com.vizo.dash.datasource.utility;

import com.vizo.dash.model.DataSource;
import com.vizo.dash.model.chart.PieChart;
import com.vizo.dash.payload.response.chart.GroupingCount;
import com.vizo.dash.payload.response.chart.PieChartDataResponse;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

@Service("MYSQL")
public class MySQLUtilityImpl implements DatasourceUtility {

    private Connection getConnection(DataSource ds) throws ClassNotFoundException, SQLException {
        String driver = "com.mysql.cj.jdbc.Driver";
        String url = ds.getConnectionDetails() + ds.getDatabaseName();
        Class.forName(driver);
        return DriverManager.getConnection(url, "vizomysql", "vizomysql");
    }

    @Override
    public PieChartDataResponse generatePieChartData(PieChart pie) {
        final PieChartDataResponse response = new PieChartDataResponse();
        try {
            Connection conn = getConnection(pie.getDatasource());

            //Building Query for Pie Chart Data
            StringBuilder queryBuilder = new StringBuilder();
            queryBuilder.append("SELECT ")
                    .append(pie.getField())
                    .append(" AS NAME, COUNT(*) AS VALUE FROM ")
                    .append(pie.getDatasource().getCollection())
                    .append(" GROUP BY ")
                    .append(pie.getField());
            String query = queryBuilder.toString();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next())
                response
                        .getOption()
                        .getSeries()
                        .get(0)
                        .getData()
                        .add(new GroupingCount(rs.getString("name"), rs.getLong("value")));
            response.setName(pie.getName());
            st.close();
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        } finally {
            return response;
        }
    }

    @Override
    public Set<String> keys(DataSource ds) {
        //SELECT COLUMN_NAME FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME = 'VIZO_DS_ORDER' ORDER BY ORDINAL_POSITION;
        Set<String> response = new HashSet<>();
        try {
            Connection conn = getConnection(ds);

            //Building Query for Pie Chart Data
            StringBuilder queryBuilder = new StringBuilder();
            queryBuilder.append("SELECT COLUMN_NAME FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME = '")
                    .append(ds.getCollection())
                    .append("' ORDER BY ORDINAL_POSITION");
            String query = queryBuilder.toString();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next())
                response.add(rs.getString("COLUMN_NAME"));
            st.close();
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        } finally {
            return response;
        }
    }
}
