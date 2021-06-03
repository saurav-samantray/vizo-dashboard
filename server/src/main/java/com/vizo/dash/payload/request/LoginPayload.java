package com.vizo.dash.payload.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginPayload {
    private String username;
    private String password;
}
