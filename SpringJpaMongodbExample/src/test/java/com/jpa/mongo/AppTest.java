package com.jpa.mongo;

import com.jpa.mongo.config.MongoSpringConfig;
import com.jpa.mongo.domain.Order;
import com.jpa.mongo.repo.MongoAutoRepository;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={MongoSpringConfig.class})
public class AppTest 
{
    @Autowired
    private MongoAutoRepository repo;
    
    @Before
    public void clearData(){
        repo.deleteAll();
    }
    
    @Test
    public void testTheInsert()
    {
        Order order = new Order();
        order.setId("1001");
        order.setType("Electronics");
        repo.save(order);
        assertEquals(1,repo.findAll().size());
    }
    
    @Test
    public void testTheSearch(){
        Order order = new Order();
        order.setId("1001");
        order.setType("Electronics");
        repo.save(order);
        
        order = new Order();
        order.setId("1002");
        order.setType("Home");
        repo.save(order);
        
        order = new Order();
        order.setId("1003");
        order.setType("Electronics");
        repo.save(order);
        
        assertEquals(2, repo.findOrdersByType("Electronics").size());
        assertEquals(1, repo.findOrdersByType("Home").size());
        assertEquals(0, repo.findOrdersByType("JUNK").size());
    }
    
    @Test
    public void testTheCustomQuery(){
        Order order = new Order();
        order.setId("1001");
        order.setType("Electronics");
        repo.save(order);
        
        order = new Order();
        order.setId("1002");
        order.setType("Home");
        repo.save(order);
        
        order = new Order();
        order.setId("1003");
        order.setType("Electronics");
        repo.save(order);
        
        assertEquals(1, repo.findOrderByTypeLike("m").size());
        assertEquals(2, repo.findOrderByTypeLike("tron").size());
        assertEquals(3, repo.findOrderByTypeLike("o").size());
    }
}
