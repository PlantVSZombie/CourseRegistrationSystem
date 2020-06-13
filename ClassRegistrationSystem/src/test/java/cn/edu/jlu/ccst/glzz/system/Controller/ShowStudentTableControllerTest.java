package cn.edu.jlu.ccst.glzz.system.Controller;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import ch.qos.logback.classic.spi.CallerData;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ShowStudentTableControllerTest {

    @Test
    void getStudentTable() {
        ValidatableResponse myresponse =given()
                .log().all()
                .queryParam("userid","2211223")
                .queryParam("password","12345678")
                .queryParam("usertype","professor")
                .when()
                .post("/login")
                .then()
                .log().all()
                .body("status",equalTo(200));
        Map responseCookies = myresponse.extract().cookies();

        given()
                .log().all()
                .cookies(responseCookies)
                .param("limit",15)
                .param("page",1)
        .when()
                .post("/professor/student_table.json")
        .then()
                .log().all()
        .body("status",equalTo(200));
    }
}