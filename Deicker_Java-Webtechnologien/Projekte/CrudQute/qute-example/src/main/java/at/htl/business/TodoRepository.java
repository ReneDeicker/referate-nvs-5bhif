package at.htl.business;

import at.htl.model.Todo;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
@Transactional
public class TodoRepository implements PanacheRepository<Todo> {
    public Todo save(Todo todo) {
        this.persistAndFlush(todo);
        return findById(todo.getId());
    }
    public void delete(long id) {
        this.delete("id", id);
    }
    public List<Todo> getAll() {
        return findAll().list();
    }
}
