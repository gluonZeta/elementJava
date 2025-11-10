package gluon.projects.repository.impl;

import gluon.projects.entity.OrderFlowEntity;
import gluon.projects.repository.OrderFlowRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderFlowRepositoryImplTest {

    @BeforeEach
    void setUp() {
    }

    @Test
    void saveOrderFlow() {
        OrderFlowRepository orderFlowRepository = new OrderFlowRepositoryImpl();
        OrderFlowEntity orderFlowEntity = new OrderFlowEntity();
        orderFlowEntity.setPrice(5.7F);
        orderFlowRepository.saveOrderFlow(orderFlowEntity);
    }
}