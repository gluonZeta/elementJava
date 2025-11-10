package gluon.projects.repository.impl;

import gluon.projects.HibernateUtil;
import gluon.projects.entity.OrderFlowEntity;
import gluon.projects.repository.OrderFlowRepository;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class OrderFlowRepositoryImpl implements OrderFlowRepository {
    @Override
    public void saveOrderFlow(OrderFlowEntity orderFlowEntity) {
        Transaction transaction = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(orderFlowEntity);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}
