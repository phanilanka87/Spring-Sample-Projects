package com.jpa.mongo.config;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages={"com.jpa.mongo"}, repositoryImplementationPostfix = "Impl")
public class MongoSpringConfig extends AbstractMongoConfiguration{

    @Override
    protected String getDatabaseName() {
        return "testdb";
    }

    @Override
    public Mongo mongo() throws Exception {
        return new MongoClient("192.168.99.100", 27017);
    }
    
}
