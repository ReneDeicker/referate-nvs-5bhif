package at.htl.business;

import at.htl.model.Todo;
import at.htl.persistence.TodoRepository;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Startup
@Singleton
public class InitBean {
    @Inject
    private TodoRepository todoRepository;

    public InitBean() {
    }

    @PostConstruct
    private void init() throws ParseException {
        //C:\"Program Files"\Java\jdk1.8.0_151\db\bin\startNetworkServer.bat -noSecurityManager
        System.out.println("InitBean***");
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");

        todoRepository.add(new Todo("Englisch", format.parse("5.2.2020"), format.parse("5.2.2020")));
        todoRepository.add(new Todo("Deutsch", format.parse("7.2.2020"), format.parse("7.2.2020")));
        todoRepository.add(new Todo("Mathe", format.parse("7.2.2020"), format.parse("7.2.2020")));
        todoRepository.add(new Todo("Chemie", format.parse("9.2.2020"), format.parse("9.2.2020")));
        todoRepository.add(new Todo("Programmiern", format.parse("11.2.2020")));
        todoRepository.add(new Todo("Geschichte", format.parse("11.2.2020")));
    }
}
