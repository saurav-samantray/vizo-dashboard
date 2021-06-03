package com.vizo.dash.model.chart;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.vizo.dash.model.Dashboard;
import com.vizo.dash.model.DataSource;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.sql.Timestamp;

@Getter
@Setter
@ToString
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="type",discriminatorType=DiscriminatorType.STRING)
@DiscriminatorValue(value="BASE")
@Entity(name = "VIZO_CHART")
public class BaseChart {

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

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "DATASOURCE_ID")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private DataSource datasource;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "DASHBOARD_ID")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Dashboard dashboard;

    @Column(name="created_on", columnDefinition="TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP")
    @CreationTimestamp
    private Timestamp createdOn;
}
