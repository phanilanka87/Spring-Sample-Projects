package com.jpa.mongo.main;

import com.jpa.mongo.config.MongoManualConfig;
import com.jpa.mongo.domain.Order;
import com.jpa.mongo.repo.MongoAutoRepository;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main 
{
    public static void main( String[] args )
    {
        AnnotationConfigApplicationContext context = 
                new AnnotationConfigApplicationContext(MongoManualConfig.class);
        //MongoSpringConfig
        
        MongoAutoRepository repo = context.getBean(MongoAutoRepository.class);
        
        Order order = new Order();
        order. setId("1000001");
        order.setType("Electronics");
        repo.save(order);
        System.out.println("Total repo size: " + repo.findAll().size());
        System.out.println(repo.findOrdersByType("Electronics").size());
        System.out.println(repo.findOrderByTypeLike("tron").size());
    }
}
