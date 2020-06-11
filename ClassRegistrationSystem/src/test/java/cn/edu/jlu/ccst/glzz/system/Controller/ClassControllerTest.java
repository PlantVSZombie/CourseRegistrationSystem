package cn.edu.jlu.ccst.glzz.system.Controller;

import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.Test;


import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import static org.junit.jupiter.api.Assertions.*;

class ClassControllerTest {

    @Test
    void getGrade() {
        //成功登陆
        ValidatableResponse myresponse =given()
                .log().all()
                .queryParam("userid","00128")
                .queryParam("password","12345678")
                .queryParam("usertype","student")
                .when()
                .post("/login")
                .then()
                .log().all()
                .body("status",equalTo(200));
        Map responseCookies = myresponse.extract().cookies();

        given()
                .log().all()
                .cookies(responseCookies)
        .when()
                .post("/student/grade.json")
        .then()
                .log().all()
        .body("status",equalTo(200));
    }
}