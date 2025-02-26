package org.huang.pwgtp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
@MapperScan("org.huang.pwgtp.repository")
public class PwgtpApplication {

    public static void main(String[] args) {
        SpringApplication.run(PwgtpApplication.class, args);
    }

}
