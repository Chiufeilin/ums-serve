package cn.kyt.ums;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = {"cn.kyt.ums.mapper"})
public class UMSApplication {
    public static void main(String[] args) {
        SpringApplication.run(UMSApplication.class, args);
    }
}
