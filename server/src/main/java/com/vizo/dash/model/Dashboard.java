package com.vizo.dash.model;

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
@Table(name = "VIZO_DASHBOARD")
@Getter
@Setter
@ToString
public class Dashboard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank
    @Size(min = 1, max = 30)
    @Column(name="NAME")
    private String name;

    @Column(name="created_on", columnDefinition="TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP")
    @CreationTimestamp
    private Timestamp createdOn;

    @OneToMany(mappedBy = "dashboard", cascade = CascadeType.ALL)
    private Set<BaseChart> charts = new HashSet<>();

}
