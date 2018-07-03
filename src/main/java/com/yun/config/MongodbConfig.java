package com.yun.config;

import com.mongodb.Mongo;
import com.mongodb.MongoCredential;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.core.MongoClientFactoryBean;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.Arrays;

@Configuration
@EnableMongoRepositories(basePackages = "com.yun.mongodb.dao")//启用MongoDB的resposities功能
public class MongodbConfig {

    @Autowired
    private Environment env;
    @Bean
    public MongoClientFactoryBean mongo(){
        MongoClientFactoryBean mongoClientFactoryBean = new MongoClientFactoryBean();
        return mongoClientFactoryBean;
    }

    @Bean
    public MongoOperations mongoTemplate(Mongo mongo){
        return new MongoTemplate(mongo,"chatDB");
    }
}
