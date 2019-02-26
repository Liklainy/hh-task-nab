package ru.hh.nab.todo;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import ru.hh.nab.hibernate.transaction.DataSourceContextTransactionManager;

@Repository
@Transactional
public class TodoDao {

    private final SessionFactory sessionFactory;
    private final DataSourceContextTransactionManager transactionManager;

    public TodoDao(SessionFactory sessionFactory, DataSourceContextTransactionManager transactionManager) {
        this.sessionFactory = sessionFactory;
        this.transactionManager = transactionManager;
    }

    public List<Todo> getAll() {
        return session()
            .createQuery("from Todo", Todo.class)
            .getResultList();
    }

    public void saveNew(Todo todo) {
        //TransactionStatus transactionStatus = startTransaction();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        
        session.persist(todo);

        transaction.commit();
        session.close();
        //transactionManager.commit(transactionStatus);
    }

    public void update(Todo todo) {
        session().merge(todo);
    }

    public void deleteById(int id) {
        CriteriaBuilder cb = session().getCriteriaBuilder();
        CriteriaDelete<Todo> delete = cb.createCriteriaDelete(Todo.class);
        Root<Todo> e = delete.from(Todo.class);
        delete.where(cb.equal(e.get("id"), id));
        session().createQuery(delete).executeUpdate();
    }

    private Session session() {
        return this.sessionFactory.getCurrentSession();
    }

    protected TransactionStatus startTransaction() {
        return transactionManager.getTransaction(new DefaultTransactionDefinition());
    }
}