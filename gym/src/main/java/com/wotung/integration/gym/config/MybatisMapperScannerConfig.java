package com.wotung.integration.gym.config;

import com.wotung.integration.gym.Constants;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
//TODO 注意，由于MapperScannerConfigurer执行的比较早，所以必须有下面的注解
@AutoConfigureAfter(MybatisAutoConfiguration.class)
public class MybatisMapperScannerConfig {

    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer() {
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
        mapperScannerConfigurer.setBasePackage(Constants.MAPPER_PACKAGE);
        return mapperScannerConfigurer;
    }

}
