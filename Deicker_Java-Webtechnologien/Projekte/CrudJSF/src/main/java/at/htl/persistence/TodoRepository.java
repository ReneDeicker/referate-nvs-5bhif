package at.htl.persistence;

import at.htl.model.Todo;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Stateless
public class TodoRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public List<Todo> getAll() {
        List<Todo> todos = this.entityManager.createQuery("SELECT t FROM Todo t", Todo.class).getResultList();
        Collections.sort(todos);
        return todos;
    }

    public List<Todo> getAllUndone() {
        List<Todo> todos = this.entityManager.createQuery("SELECT t FROM Todo t where t.done = false", Todo.class).getResultList();
        return todos;
    }
    public List<Todo> getAllDone() {
        List<Todo> todos = this.entityManager.createQuery("SELECT t FROM Todo t where t.done = true", Todo.class).getResultList();
        return todos;
    }

    public void delete(Todo todo) {
        if (entityManager.contains(todo)) {
            entityManager.remove(todo);
        } else {
            Todo managedTodo = entityManager.find(Todo.class, todo.getId());
            if (managedTodo != null) {
                entityManager.remove(managedTodo);
            }
        }
    }

    public void add(Todo todo ) {
        this.entityManager.persist(todo);
    }

    public Todo update(Todo todo){
        return entityManager.merge(todo);
    }
}
