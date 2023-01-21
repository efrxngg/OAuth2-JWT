package com.empresax.security.controller;

import static com.empresax.security.security.Constants.SIGN_IN;
import static com.empresax.security.security.Constants.SIGN_OUT;
import static com.empresax.security.security.Constants.SIGN_UP;
import static com.empresax.security.security.Constants.TOKEN_REFRESH;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Optional;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.empresax.security.domain.dto.RefreshToken;
import com.empresax.security.domain.dto.SignInReq;
import com.empresax.security.domain.dto.SignedInUser;
import com.empresax.security.domain.entity.RoleType;
import com.empresax.security.domain.entity.StateType;
import com.empresax.security.domain.entity.UserEntity;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@AutoConfigureWebTestClient
public class AuthControllerTest {

    @Test
    @Order(1)
    void testEndPointSignUp() {
        webTestClient.post()
                .uri(SIGN_UP)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(user)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(SignedInUser.class)
                .consumeWith(response -> assertNotNull(response.getResponseBody()));
    }

    @Test
    @Order(2)
    void testEndPointSignIn() {
        var credentialsReq = new SignInReq(user.getUsername(), user.getPassword());
        webTestClient.post()
                .uri(SIGN_IN)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(credentialsReq)
                .exchange()
                .expectStatus().isOk()
                .expectBody(SignedInUser.class)
                .consumeWith(response -> {
                    assertNotNull(response.getResponseBody());
                    var aux = Optional.of(response.getResponseBody());
                    refreshToken = new RefreshToken(aux.get().getRefreshToken());
                });
    }

    @Test
    @Order(3)
    void testEndPointRefresh() {
        webTestClient.post()
                .uri(TOKEN_REFRESH)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(refreshToken)
                .exchange()
                .expectStatus().isOk()
                .expectBody(SignedInUser.class)
                .consumeWith(response -> assertNotNull(response.getResponseBody()));
    }

    @Test
    @Order(4)
    void testEndPointSignOut() {
        webTestClient.method(HttpMethod.DELETE).uri(SIGN_OUT)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(refreshToken).exchange()
                .expectStatus().isOk();
    }

    @Autowired
    private WebTestClient webTestClient;
    private final UserEntity user = new UserEntity(
            null,
            "test",
            "1234",
            "Test",
            "Test",
            "testauth@empresax.com",
            "593997188086",
            RoleType.USER,
            StateType.ACTIVE
    );
    private RefreshToken refreshToken;

}
