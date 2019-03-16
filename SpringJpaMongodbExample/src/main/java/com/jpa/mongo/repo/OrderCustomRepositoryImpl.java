package com.jpa.mongo.repo;

import com.jpa.mongo.domain.Order;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class OrderCustomRepositoryImpl implements OrderCustomRepository {

    @Autowired
    private MongoOperations mongo;

    public List<Order> findOrdersByType(String type) {
        String changedType = "WEB".equals(type) ? "NET" : type;
        Criteria where = Criteria.where("type").is(changedType);
        Query query = Query.query(where);
        return mongo.find(query, Order.class);
    }
}
