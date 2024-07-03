package thyme.event_service;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import thyme.event_service.comments.CommentsModel;
import thyme.event_service.comments.CommentsRepository;
import thyme.event_service.event.Address;
import thyme.event_service.event.EventModel;
import thyme.event_service.event.EventRepository;
import thyme.event_service.user.UserModel;
import thyme.event_service.user.UserRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@SpringBootApplication
public class EventServiceApplication implements CommandLineRunner {

    public EventServiceApplication(EventRepository eventRepository, UserRepository userRepository, CommentsRepository commentsRepository) {
        this.eventRepository = eventRepository;
        this.userRepository = userRepository;
        this.commentsRepository = commentsRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(EventServiceApplication.class, args);
    }


    private final EventRepository eventRepository;
    private final UserRepository userRepository;
    private final CommentsRepository commentsRepository;

    @Override
    public void run(String... args) throws Exception {
        UserModel user1 = new UserModel(1L, "Mdss", "Maćko", "Łomiarz", "mdss@o2.pl", 40, new HashSet<>(), new ArrayList<>(), new HashSet<>());
        UserModel user2 = new UserModel(2L, "Oloratuj", "Olek", "Bolek", "bolo@wp.pl", 33, new HashSet<>(), new ArrayList<>(), new HashSet<>());
        UserModel user3 = new UserModel(3L, "Nicalodeon Sp.Z O.O.", "Nick", "Quick", "m@qn.pl", 38, new HashSet<>(), new ArrayList<>(), new HashSet<>());
        UserModel user4 = new UserModel(4L, "jaśko@333", "Jan", "Nowawowaczyńskij", "janekdzbanek@interia.pl", 25, new HashSet<>(), new ArrayList<>(), new HashSet<>());

        EventModel event1 = new EventModel(1L, user1, "Noc muzeów", "Można se raz w roku pochodzić po muzeach za frajer. A co!", LocalDateTime.parse("2025-12-25T20:00:00.000"), new Address("Cracov", "Kupa", "10/2", "Poland"), 0.0, true, new ArrayList<>(), new HashSet<>());
        EventModel event2 = new EventModel(2L, user1, "Obrona zamku przed szwedem", "Zamek Tenczyn, jako jeden ze strategicznych gospodarzy po stronie województwa Małopolskiego bierze udział w Juromanii – święcie Jury Krakowsko Częstochowskiej.", LocalDateTime.now(), new Address("Rudno", "Tenczyńska", "7/2", "Poland"), 0.0, false, new ArrayList<>(), new HashSet<>());
        EventModel event3 = new EventModel(3L, user2, "Eastside - seaside", "Festiwal muzyczny Radia 357 organizowany dzięki datkom od radiosłuchaczy.", LocalDateTime.parse("2025-09-02T08:00:00.000"), new Address("Munster", "Scholtz", "22", "Germany"), 20.0, false, new ArrayList<>(), new HashSet<>());
        EventModel event4 = new EventModel(4L, user3, "Music Rock Festival", "Coroczna imprezka jakich mało w Polsce już teraz w Krakowie!!", LocalDateTime.now().plusDays(33), new Address("Cracov", "Berka", "17/1", "Poland"), 9.99, true, new ArrayList<>(), new HashSet<>());
        EventModel event5 = new EventModel(5L, user2, "Rock Poland", "Największa rockowa imprezka w Polsce jedzie kolejny rock z rzędu!!!", LocalDateTime.now().plusWeeks(7), new Address("Cracov", "AGH", "13", "Poland"), 59.99, true, new ArrayList<>(), new HashSet<>());
        EventModel event6 = new EventModel(6L, user2, "Science for kids!", "Science Kids is the online home of science & technology for children around the world.Learn more with our fun science experiments, cool facts, free games, activities, lesson plans, quizzes, videos, photos and science fair project ideas.", LocalDateTime.now().minusDays(15), new Address("Warsaw", "Aleje Ujazdowskie", "9/1", "Poland"), 99.00, true, new ArrayList<>(), new HashSet<>());

        CommentsModel comm1 = new CommentsModel(1L, user3, event1, "Zarąbista akcja,tak trzymać!", 9, LocalDateTime.now().minusMinutes(33));
        CommentsModel comm2 = new CommentsModel(2L, user2, event1, "No spoko oko na maroko :)", 7, LocalDateTime.now().minusMinutes(133));
        CommentsModel comm3 = new CommentsModel(3L, user3, event2, "Gituwa! Dobre żarcie, armaty waliły i ogólnie dobra zabawa! Wrócę tu za rok!", 8, LocalDateTime.now().minusMinutes(13));
        CommentsModel comm4 = new CommentsModel(4L, user2, event2, "Takie se. Mogłoby być lepsze ale nie jest w sumie...", 5, LocalDateTime.now().minusMinutes(99));
        CommentsModel comm5 = new CommentsModel(5L, user1, event3, "Good good very good", 9, LocalDateTime.now().minusMinutes(11));
        CommentsModel comm6 = new CommentsModel(6L, user1, event4, "do bani i lipa straszna", 4, LocalDateTime.now().minusMinutes(66));
        CommentsModel comm7 = new CommentsModel(7L, user4, event2, "Eee zmieniam zdanie - jednak może być, bo wcale nie takie złe:)", 8, LocalDateTime.now().minusMinutes(3));

        userRepository.saveAll(List.of(user1, user2, user3, user4));
        eventRepository.saveAll(List.of(event1, event2, event3, event4, event5, event6));
        commentsRepository.saveAll(List.of(comm1, comm2, comm3, comm4, comm5, comm6, comm7));

        event1.getComments().addAll(List.of(comm1, comm2));
        event2.getComments().addAll(List.of(comm3, comm4, comm7));
        event3.getComments().add(comm5);
        event4.getComments().add(comm6);

        user1.getComments().addAll(List.of(comm5, comm6));
        user2.getComments().addAll(List.of(comm2, comm4, comm7));
        user3.getComments().addAll(List.of(comm1, comm3));
        user4.getComments().addAll(List.of(comm7));

        userRepository.saveAll(List.of(user1, user2, user3, user4));
        eventRepository.saveAll(List.of(event1, event2, event3, event4, event5, event6));
    }
}
