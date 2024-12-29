package com.ecommerce.sb_ecom.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
public class UserInfoResponse {

    @Setter
    private Long id;
    @Setter
    private String username;

    @Setter
    private List<String> roles;

    public UserInfoResponse(Long id, String username, List<String> roles) {
        this.id = id;
        this.username = username;
        this.roles = roles;
    }
}

