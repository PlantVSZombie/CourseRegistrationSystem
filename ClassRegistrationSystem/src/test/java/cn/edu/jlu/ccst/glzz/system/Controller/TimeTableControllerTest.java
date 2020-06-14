package cn.edu.jlu.ccst.glzz.system.Controller;

import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;

class TimeTableControllerTest {

    @Test
    void getTimeTable() {
        //成功登陆
        ValidatableResponse myresponse =given()
                .log().all()
                .queryParam("userid","00128")
                .queryParam("password","1234567")
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
                .get("/student/timetable.json")
                .then()
                .log().all()
                .body("status",equalTo(200));
    }
}