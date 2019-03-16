package com.jpa.mongo.config;

import com.mongodb.Mongo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoFactoryBean;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages={"com.jpa.mongo"}, repositoryImplementationPostfix = "Impl")
public class MongoManualConfig {
    
    @Bean
    public MongoFactoryBean mongo(){
        MongoFactoryBean mongo = new MongoFactoryBean();
        mongo.setHost("192.168.99.100");
        mongo.setPort(27017);
        return mongo;
    }
    
    @Bean
    public MongoOperations mongoTemplate(Mongo mongo){
        return new MongoTemplate(mongo, "testdb");
    }
}
