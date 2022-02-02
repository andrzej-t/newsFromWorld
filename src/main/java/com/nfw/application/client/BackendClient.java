package com.nfw.application.client;

import com.nfw.application.config.ConnectionConfig;
import com.nfw.application.domain.LoginRequest;
import com.nfw.application.domain.RegistrationRequest;
import lombok.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
@Getter
@Setter
public class BackendClient {

    private final RestTemplate restTemplate;
    private final ConnectionConfig connectionConfig;
    private static final Logger LOGGER = LoggerFactory.getLogger(BackendClient.class);


    public void registerAccount(RegistrationRequest registrationRequest) {
        restTemplate.postForObject(connectionConfig.getBackApiEndpoint() + "/registration", registrationRequest, RegistrationRequest.class);
    }

    public void login(LoginRequest loginRequest) {
        restTemplate.postForObject(connectionConfig.getBackApiEndpoint() + "/registration/login", loginRequest, LoginRequest.class);
    }

    public void logout(LoginRequest loginRequest) {
        restTemplate.postForObject(connectionConfig.getBackApiEndpoint() + "/registration/logout", loginRequest, LoginRequest.class);
    }
}
