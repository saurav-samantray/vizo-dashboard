package com.vizo.dash.payload.response.chart;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PieChartDataResponse {
    private String id="Pie";
    private String name="Pie";
    private Option option = new Option();
    //private List<GroupingCount> data = new ArrayList<>();

    @Getter
    @Setter
    public class Option{
        public Option(){
            this.tooltip = new Tooltip();
            this.series=new ArrayList<>();
            this.series.add(new Entry());
        }
        private Tooltip tooltip;
        private List<Entry> series;

        @Getter
        @Setter
        public class Tooltip{
            private String trigger = "item";
            private String formatter = "{a} <br/>{b} : {c} ({d}%)";
        }

        @Getter
        @Setter
        public class Entry{
            private String name = "Vizo Pie Chart";
            private String type = "pie";
            private String radius = "70%";
            private List<String> center = List.of("50%","60%");
            private List<GroupingCount> data = new ArrayList<>();
        }
    }
}
