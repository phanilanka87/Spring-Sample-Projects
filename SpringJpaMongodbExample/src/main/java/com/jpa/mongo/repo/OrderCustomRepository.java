package com.jpa.mongo.repo;

import com.jpa.mongo.domain.Order;
import java.util.List;

public interface OrderCustomRepository {

    public List<Order> findOrdersByType(String type);
}
