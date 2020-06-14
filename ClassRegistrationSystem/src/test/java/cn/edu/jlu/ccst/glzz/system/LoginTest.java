package cn.edu.jlu.ccst.glzz.system;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;


import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import org.junit.jupiter.api.Test;

public class LoginTest {
    @Test
    public void TestLoginStudent(){
        //成功登陆
        given()
                .log().all()
                .queryParam("userid","00128")
                .queryParam("password","1234567")
                .queryParam("usertype","student")
        .when()
                .post("/login")
        .then()
                .log().all()
                .body("status",equalTo(200));
        //密码错误
        given()
                .log().all()
                .queryParam("userid","00121")
                .queryParam("password","12345678")
                .queryParam("usertype","student")
                .when()
                .post("/login")
                .then()
                .log().all()
                .body("status",equalTo(1001));
    }

    @Test
    public void TestLoginProfessor(){
        //成功登陆
        given()
                .log().all()
                .queryParam("userid","2211223")
                .queryParam("password","12345678")
                .queryParam("usertype","professor")
                .when()
                .post("/login")
                .then()
                .log().all()
                .body("status",equalTo(200));
        //密码错误
        given()
                .log().all()
                .queryParam("userid","22112231")
                .queryParam("password","12345678")
                .queryParam("usertype","professor")
                .when()
                .post("/login")
                .then()
                .log().all()
                .body("status",equalTo(1001));
    }

    @Test
    public void TestLoginAdmin(){
        //成功登陆
        given()
                .log().all()
                .queryParam("userid","1001")
                .queryParam("password","123456")
                .queryParam("usertype","admin")
                .when()
                .post("/login")
                .then()
                .log().all()
                .body("status",equalTo(200));
        //密码错误
        given()
                .log().all()
                .queryParam("userid","1001")
                .queryParam("password","1234556")
                .queryParam("usertype","admin")
                .when()
                .post("/login")
                .then()
                .log().all()
                .body("status",equalTo(1001));
    }
}
