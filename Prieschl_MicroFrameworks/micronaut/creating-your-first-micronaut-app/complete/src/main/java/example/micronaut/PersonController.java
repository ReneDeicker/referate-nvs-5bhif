package example.micronaut;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller("/persons")
public class PersonController {
    private static List<Person> persons = new ArrayList<>();
    @Post
    public Person add(Person person) {
        person.setId((persons.size() + 1L));
        persons.add(person);
        return person;
    }
    @Get("/{id}")
    public Optional<Person> findById(Long id) {
        return persons.stream()
                .filter(it -> it.getId().equals(id))
                .findFirst();
    }
    @Get
    public List<Person> findAll() {
        return persons;
    }

    public static void initializeList() {
        persons.add(new Person(1L, "Max", "Mustermann", 18, "male"));
        persons.add(new Person(2L, "Tom", "Bauer", 28, "male"));
        persons.add(new Person(3L, "Susi", "Musterfrau", 12, "female"));
        persons.add(new Person(4L, "Kevin", "Müller", 20, "male"));
        persons.add(new Person(5L, "Marie", "Mayer", 16, "female"));
    }
}
