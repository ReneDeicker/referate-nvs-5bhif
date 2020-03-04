package at.htl.rest;

import at.htl.business.TodoRepository;
import at.htl.model.Todo;
import at.htl.model.TodoForm;
import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Path("/todo")
public class TodoResource {

    @Inject
    Template todo;

    @Inject
    TodoRepository todoRepository;

    @GET
    @Consumes(MediaType.TEXT_HTML)
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance listTodos() {
        return todo.data("todos", todoRepository.getAll());
    }

    @POST
    @Transactional
    @Produces(MediaType.TEXT_HTML)
    @Path("/{id}/delete")
    public Response deleteTodo(@PathParam("id") long id) {
        todoRepository.delete(id);

        return Response.status(301)
                .location(URI.create("/todo"))
                .build();
    }

    @POST
    @Produces(MediaType.TEXT_HTML)
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Transactional
    @Path("/new")
    public Response addTodo(@MultipartForm TodoForm todoForm) {
        Todo todo = todoForm.convertIntoTodo();
        todoRepository.save(todo);

        return Response.status(301)
                .location(URI.create("/todo"))
                .build();
    }
}
