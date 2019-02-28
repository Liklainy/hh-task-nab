package ru.hh.nab.todo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class TodoDao {

    private final SessionFactory sessionFactory;

    public TodoDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<Todo> getAll() {
        return session()
                .createQuery("from Todo", Todo.class)
                .getResultList();
    }

    public void saveNew(Todo todo) {
        session().persist(todo);
    }

    public void update(Todo todo) {
        session().merge(todo);
    }

    public void deleteById(int id) {
        session()
                .createQuery("delete from Todo where id = :id")
                .setParameter("id", id)
                .executeUpdate();
    }

    private Session session() {
        return this.sessionFactory.getCurrentSession();
    }
}