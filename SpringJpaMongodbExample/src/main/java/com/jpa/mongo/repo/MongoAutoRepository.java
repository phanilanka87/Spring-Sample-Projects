package com.jpa.mongo.repo;

import com.jpa.mongo.domain.Order;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface MongoAutoRepository extends MongoRepository<Order, String>, OrderCustomRepository {

    @Query("{'type' : {$regex : '.*?0.*'} }")
    public List<Order> findOrderByTypeLike(String type);
}
