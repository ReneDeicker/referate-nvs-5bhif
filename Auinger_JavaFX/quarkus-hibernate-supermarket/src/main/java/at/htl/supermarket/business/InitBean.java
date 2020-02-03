package at.htl.supermarket.business;

import at.htl.supermarket.model.*;
import io.quarkus.runtime.StartupEvent;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@ApplicationScoped
public class InitBean {

    @Inject
    EntityManager em;

    @Transactional
    void init(@Observes StartupEvent ev)
    {
        System.err.println("* Init started! *");
        //Creation of objects to persist

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        DateTimeFormatter time_formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");

        //STORE

        Store billa_meixner = new Store("BILLA Meixnerkreuzung");
        em.persist(billa_meixner);
        Store spar_leonding = new Store("EUROSPAR Leonding");
        em.persist(spar_leonding);
        Store spar_hbf = new Store("SPAR Hauptbahnhof");
        em.persist(spar_hbf);
        Store buffet_leonding = new Store("HTL Leonding Buffet");
        em.persist(buffet_leonding);

        //PRODUCT

        Product innocent_greensmoothie = new Product("Green Smoothie", 2.26, LocalDate.parse("19.12.2018",formatter), "Innocent",10,billa_meixner);
        em.persist(innocent_greensmoothie);

        Product chiquita_bananen  = new Product("Original Chiquita Bananen", 1.00, LocalDate.parse("10.12.2018",formatter), "Chiquita",20,billa_meixner);
        em.persist(chiquita_bananen);

        Product green_apfel = new Product("Green Apfel", 0.98, LocalDate.parse("05.12.2018",formatter),"Green",150,billa_meixner);
        em.persist(green_apfel);

        Product clever_wasser = new Product("Clever Wasser", 1.30, LocalDate.parse("05.05.2019",formatter),"Clever",7,billa_meixner);
        em.persist(clever_wasser);

        Product SBudget_Kaese = new Product("SBudget Kaese", 5.40, LocalDate.parse("16.08.2020",formatter),"SBudget",222,spar_hbf);
        em.persist(SBudget_Kaese);

        Product Clever_Kaese = new Product("Clever Kaese", 5.99, LocalDate.parse("16.08.2020",formatter),"Clever",222,billa_meixner);
        em.persist(Clever_Kaese);

        Product innocent_greensmoothie1 = new Product("Green Smoothie", 2.26, LocalDate.parse("19.12.2018",formatter), "Innocent",10,spar_leonding);
        em.persist(innocent_greensmoothie1);

        Product chiquita_bananen1  = new Product("Original Chiquita Bananen", 1.00, LocalDate.parse("10.12.2018",formatter), "Chiquita",20,spar_leonding);
        em.persist(chiquita_bananen1);

        Product green_apfel1 = new Product("Green Apfel", 0.98, LocalDate.parse("05.12.2018",formatter),"Green",150,spar_leonding);
        em.persist(green_apfel1);

        Product clever_wasser1 = new Product("Clever Wasser", 1.30, LocalDate.parse("05.05.2019",formatter),"Clever",7,spar_leonding);
        em.persist(clever_wasser1);

        Product SBudget_Kaese1 = new Product("SBudget Kaese", 5.40, LocalDate.parse("16.08.2020",formatter),"SBudget",222,spar_hbf);
        em.persist(SBudget_Kaese1);

        Product Clever_Kaese1 = new Product("Clever Kaese", 5.99, LocalDate.parse("16.08.2020",formatter),"Clever",222,spar_leonding);
        em.persist(Clever_Kaese1);

        //CUSTOMER

        Customer c_philipp = new Customer("Philipp","Auinger",LocalDate.parse("13.12.2000",formatter),"+4306804423123","philipp-email.com",
                LocalDate.parse("10.01.2014",formatter),940,45352,"Friend", billa_meixner);
        em.persist(c_philipp);
        Customer c_nenad = new Customer("Nenad","Tripic",LocalDate.parse("06.11.2000",formatter),"+43 4345222122","nenad-saveemail.com",
                LocalDate.parse("14.10.2015",formatter),264,12344,"Normal", billa_meixner);
        em.persist(c_nenad);
        Customer c_thomas = new Customer("Susanna", "Geld", LocalDate.parse("05.07.1999",formatter),"+66 43242342","",
                LocalDate.parse("03.04.2017",formatter),102,12219,"Normal", billa_meixner);
        em.persist(c_thomas);
        Customer c_stephan = new Customer("Stephan","Do",LocalDate.parse("28.11.2000",formatter),"+4312344444","stephan-do@bestmail.com",
                LocalDate.parse("09.06.2011",formatter),9,10103,"Newbie", billa_meixner);
        em.persist(c_stephan);

        //CASHIER

        Cashier ca_cashier_1 = new Cashier("Susanna", "Geld", LocalDate.parse("05.07.1999",formatter),"+66 43242342","",
                LocalDate.parse("01.01.2000",formatter),2300.20, billa_meixner);
        em.persist(ca_cashier_1);
        Cashier ca_cashier_2 = new Cashier("Bernd", "Moneten", LocalDate.parse("17.04.1980",formatter),"+1244444342","bernd.newemail.com",
                LocalDate.parse("04.06.2014",formatter),1000.99, billa_meixner);
        em.persist(ca_cashier_2);

        Cashier ca_cashier_3 = new Cashier("Richard", "Pesos", LocalDate.parse("05.07.1999",formatter),"+66 43242342","",
                LocalDate.parse("01.01.2000",formatter),2300.20, billa_meixner);
        em.persist(ca_cashier_3);
        Cashier ca_cashier_4 = new Cashier("Marco", "Hain", LocalDate.parse("17.04.1980",formatter),"+1244444342","marco.newemail.com",
                LocalDate.parse("04.06.2014",formatter),1000.99, spar_hbf);
        em.persist(ca_cashier_4);

        //ACTIVITY

        Activity a = new Activity(LocalDateTime.parse("02.12.2018 13:20:01",time_formatter),2,green_apfel,ca_cashier_1,c_philipp, billa_meixner);
        em.persist(a);
        a = new Activity(LocalDateTime.parse("02.02.2018 13:20:06",time_formatter),2,clever_wasser,ca_cashier_1,c_philipp, billa_meixner);
        em.persist(a);
        a = new Activity(LocalDateTime.parse("02.02.2018 14:01:30",time_formatter),1,green_apfel,ca_cashier_2,c_nenad, billa_meixner);
        em.persist(a);
        a = new Activity(LocalDateTime.parse("02.02.2018 11:46:10",time_formatter),1,SBudget_Kaese,ca_cashier_1,c_thomas, billa_meixner);
        em.persist(a);
        a = new Activity(LocalDateTime.parse("02.02.2018 13:46:14",time_formatter),1,chiquita_bananen,ca_cashier_1,c_thomas, billa_meixner);
        em.persist(a);
        a = new Activity(LocalDateTime.parse("02.02.2018 20:10:30",time_formatter),2,chiquita_bananen,ca_cashier_2,c_nenad, billa_meixner);
        em.persist(a);
        a = new Activity(LocalDateTime.parse("02.02.2018 21:40:55",time_formatter),1,chiquita_bananen,ca_cashier_1,c_stephan, billa_meixner);
        em.persist(a);

        a = new Activity(LocalDateTime.parse("02.12.2018 13:20:01",time_formatter),2,green_apfel,ca_cashier_1,c_philipp, spar_hbf);
        em.persist(a);
        a = new Activity(LocalDateTime.parse("02.02.2018 13:20:06",time_formatter),2,clever_wasser,ca_cashier_1,c_philipp, spar_hbf);
        em.persist(a);
        a = new Activity(LocalDateTime.parse("02.02.2018 14:01:30",time_formatter),1,green_apfel,ca_cashier_2,c_nenad, spar_hbf);
        em.persist(a);
        a = new Activity(LocalDateTime.parse("02.02.2018 11:46:10",time_formatter),1,SBudget_Kaese,ca_cashier_1,c_thomas, spar_hbf);
        em.persist(a);
        a = new Activity(LocalDateTime.parse("02.02.2018 13:46:14",time_formatter),1,chiquita_bananen,ca_cashier_1,c_thomas, spar_hbf);
        em.persist(a);
        a = new Activity(LocalDateTime.parse("02.02.2018 20:10:30",time_formatter),2,chiquita_bananen,ca_cashier_2,c_nenad, spar_hbf);
        em.persist(a);
        a = new Activity(LocalDateTime.parse("02.02.2018 21:40:55",time_formatter),1,chiquita_bananen,ca_cashier_1,c_stephan, spar_hbf);
        em.persist(a);

        a = new Activity(LocalDateTime.parse("02.12.2018 13:20:01",time_formatter),2,green_apfel,ca_cashier_1,c_philipp, spar_leonding);
        em.persist(a);
        a = new Activity(LocalDateTime.parse("02.02.2018 13:20:06",time_formatter),2,clever_wasser,ca_cashier_1,c_philipp, spar_leonding);
        em.persist(a);
        a = new Activity(LocalDateTime.parse("02.02.2018 14:01:30",time_formatter),1,green_apfel,ca_cashier_2,c_nenad, spar_leonding);
        em.persist(a);
        a = new Activity(LocalDateTime.parse("02.02.2018 11:46:10",time_formatter),1,SBudget_Kaese,ca_cashier_1,c_thomas, spar_leonding);
        em.persist(a);
        a = new Activity(LocalDateTime.parse("02.02.2018 13:46:14",time_formatter),1,chiquita_bananen,ca_cashier_1,c_thomas, spar_leonding);
        em.persist(a);
        a = new Activity(LocalDateTime.parse("02.02.2018 20:10:30",time_formatter),2,chiquita_bananen,ca_cashier_2,c_nenad, spar_leonding);
        em.persist(a);
        a = new Activity(LocalDateTime.parse("02.02.2018 21:40:55",time_formatter),1,chiquita_bananen,ca_cashier_1,c_stephan, spar_leonding);

        //REQUESTS

        em.createNamedQuery("Customer.getAll",Customer.class).getResultList()
                .forEach(c -> System.err.println(c.getFirstname() + c.getLoyalty_points()));
    }
}
