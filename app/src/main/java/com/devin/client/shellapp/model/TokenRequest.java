package com.devin.client.shellapp.model;

/**
 * @author devin
 * @Class TokenRequest
 * @Date 16/11/1
 */

public class TokenRequest {

    /**
     * token_input {
        token (string, optional): token
     }
     *
     *
     */

    /**
     * token : string
     */

    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
