package com.kovaitech.adani.solar.supervision.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;


@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginResponse {

    private boolean loginStatus;
    private String responseMsg;

}
