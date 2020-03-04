package at.htl.business;

import at.htl.model.Todo;
import at.htl.persistence.TodoRepository;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Named
@ViewScoped//Scope = Gültigkeitsbereich
public class TodoBean implements Serializable {
    private List<Todo> todosUndone;
    private List<Todo> allToDos;
    private Todo newTodo;

    @Inject
    private TodoRepository todoRepository;

    @PostConstruct
    public void init() {
        todosUndone = todoRepository.getAllUndone();
        allToDos = todoRepository.getAll();

        this.newTodo = new Todo("", new Date());
    }

    public void delete(Todo todo) {
        todoRepository.delete(todo);

        todosUndone = todoRepository.getAllUndone();
        allToDos = todoRepository.getAll();
    }

    public String add() {
        todoRepository.add(newTodo);

        todosUndone = todoRepository.getAllUndone();
        allToDos = todoRepository.getAll();

        this.newTodo = new Todo("", new Date());
        return "index.html?faces-redirect=true";
    }

    public String setToDoDone(Todo todo) {
        todo.setDone(true);
        todo.setDueDate(new Date());
        todoRepository.update(todo);

        todosUndone = todoRepository.getAllUndone();
        allToDos = todoRepository.getAll();

        return "index.html";
    }

    public Todo getNewTodo() {
        return newTodo;
    }
    public void setNewTodo(Todo newTodo) {
        this.newTodo = newTodo;
    }
    public List<Todo> getTodosUndone() {return todosUndone;}
    public void setTodosUndone(List<Todo> todosUndone) {this.todosUndone = todosUndone;}
    public List<Todo> getAllToDos() {
        return allToDos;
    }
    public void setAllToDos(List<Todo> allToDos) {
        this.allToDos = allToDos;
    }
}
