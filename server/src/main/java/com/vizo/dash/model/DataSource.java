package com.vizo.dash.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.vizo.dash.model.chart.BaseChart;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "VIZO_DATASOURCES")
@Getter
@Setter
@ToString
public class DataSource {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(
            name="ID",
            columnDefinition = "BIGINT"
    )
    private long id;

    @NotBlank
    @Size(min = 1, max = 30)
    @Column(name="NAME")
    private String name;

    @NotBlank
    @Size(min = 1, max = 120)
    @Column(name="connection_details")
    private String connectionDetails;

    @NotBlank
    @Size(min = 1, max = 120)
    @Column(name="DATABASE_NAME")
    private String databaseName;

    @NotBlank
    @Size(min = 1, max = 120)
    @Column(name="COLLECTION")
    private String collection;


    @Enumerated(EnumType.STRING)
    @Column(
            name="TYPE",
            length=45,
            columnDefinition = "ENUM('MYSQL', 'MONGODB', 'POSTGRESQL')"
    )
    private DatasourceType type;

    @Column(name="is_active")
    private Boolean active;

    @Column(name="is_connected")
    private Boolean connected;

    @Column(name="created_on", columnDefinition="TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP")
    @CreationTimestamp
    private Timestamp createdOn;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private User user;

    @OneToMany(mappedBy = "datasource", cascade = CascadeType.ALL)
    private Set<BaseChart> charts = new HashSet<>();

    public void setConnectionDetails(String connectionDetails) {
        if(connectionDetails != null) this.connectionDetails = connectionDetails;
    }

    public void setActive(Boolean active) {
        if(active != null) this.active = active;
    }

    public void setConnected(Boolean connected) {
        if(connected != null) this.connected = connected;
    }

    public void setName(String name) {
        if(name != null) this.name = name;
    }

    public void setDatabaseName(String databaseName) {
        if(databaseName != null) this.databaseName = databaseName;
    }

    public void setCollection(String collection) {
        if(databaseName != collection) this.collection = collection;
    }

    public void setType(DatasourceType type) {
        if(type != null) this.type = type;
    }

    public void setCharts(Set<BaseChart> charts) {
        if(charts != null)this.charts = charts;
    }

    public void setUser(User user) {
        if(user != null){
            this.user = user;
        }
    }
}
