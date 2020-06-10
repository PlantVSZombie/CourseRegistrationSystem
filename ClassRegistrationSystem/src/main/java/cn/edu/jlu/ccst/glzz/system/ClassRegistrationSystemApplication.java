package cn.edu.jlu.ccst.glzz.system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class ClassRegistrationSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClassRegistrationSystemApplication.class, args);
    }

}
