package com.vizo.dash.model.chart;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@DiscriminatorValue("PIE")
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING)
@Getter
@Setter
@ToString
public class PieChart extends BaseChart{

    @NotBlank
    private String field;
}
