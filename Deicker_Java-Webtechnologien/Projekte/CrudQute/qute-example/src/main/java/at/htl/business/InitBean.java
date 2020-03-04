package at.htl.business;

import at.htl.model.Todo;
import io.quarkus.runtime.StartupEvent;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.transaction.Transactional;

@ApplicationScoped
public class InitBean {
    @Inject
    TodoRepository todoRepository;

    @Transactional
    void init(@Observes StartupEvent ev) {
        /*
        * Postgres starten
        *   cd compose
        *   docker-compose up
        * Quarkus starten
        *   mvnw quarkus:dev
        * */
        todoRepository.save(new Todo("Programmiern"));
        todoRepository.save(new Todo("Deutsch"));
    }
}
