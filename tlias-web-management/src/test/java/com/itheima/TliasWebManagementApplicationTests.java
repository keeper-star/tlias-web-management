package com.itheima;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.test.context.SpringBootTest;

@MapperScan("com.itheima.mapper")
@SpringBootTest
class TliasWebManagementApplicationTests {

    @Test
    void contextLoads() {
    }

}
