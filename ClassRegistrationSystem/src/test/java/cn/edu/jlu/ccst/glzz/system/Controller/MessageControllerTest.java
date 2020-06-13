package cn.edu.jlu.ccst.glzz.system.Controller;

import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;



class MessageControllerTest {

    @Test
    void getMessages() {
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
                .param("limit",15)
                .param("page",1)
                .when()
                .get("/student/messages.json")
                .then()
                .log().all()
                .body("status",equalTo(200));
    }

    @Test
    void setHasRead() {
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
                .param("message_id",1)
                .when()
                .get("/student/set_has_read")
                .then()
                .log().all()
                .body("status",equalTo(200));
    }
}