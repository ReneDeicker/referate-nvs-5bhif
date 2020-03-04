package at.htl.model;

import javax.ws.rs.FormParam;

public class TodoForm {

    public @FormParam("title") String title;

    public Todo convertIntoTodo() {
        Todo todo = new Todo();
        todo.title = title;
        return todo;
    }
}
