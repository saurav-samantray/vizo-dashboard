package com.vizo.dash.dto;

import com.vizo.dash.model.DataSource;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDto {
    private String username;
    private String password;
    private Set<DataSource> datasources;
}
