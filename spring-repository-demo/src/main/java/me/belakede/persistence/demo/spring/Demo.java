package me.belakede.persistence.demo.spring;

import me.belakede.persistence.demo.spring.domain.Note;
import me.belakede.persistence.demo.spring.domain.User;
import me.belakede.persistence.demo.spring.repository.NoteRepository;
import me.belakede.persistence.demo.spring.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Demo {

    private static final Logger LOGGER = LoggerFactory.getLogger(Demo.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private NoteRepository noteRepository;

    public Demo() {
    }

    public void run() {
        if (!hasUsers()) {
            createUsers();
            createNotes();
        }
        printNotes();
    }

    private boolean hasUsers() {
        return userRepository.count() > 0;
    }

    private void createUsers() {
        LOGGER.trace("Create users...");
        userRepository.save(getJohn());
        userRepository.save(getJessica());

        LOGGER.debug("Created users: {}", userRepository.findAll());
    }

    private void createNotes() {
        LOGGER.trace("Create notes...");
        List<User> users = userRepository.findByUsernameContaining("devil");
        noteRepository.save(createNote(users.get(0), "My First Note", "This is my first note."));
        noteRepository.save(createNote(users.get(0), "Second Note", "This is my second note."));
        LOGGER.debug("Created notes: {}", noteRepository.findAll());
    }

    private void printNotes() {
        LOGGER.trace("Print notes...");
        userRepository.findAll().forEach(user -> {
            LOGGER.debug("{}'s notes ({} db)", user.getFullName(), user.getNotes().size());
            user.getNotes().forEach(note -> LOGGER.debug("\t{}", note));
        });
    }

    private User getJohn() {
        User user = new User();
        user.setEmail("matt.murdock@demo.com");
        user.setUsername("daredevil");
        user.setFullName("Matthew Murdock");
        user.setEnabled(true);
        return user;
    }

    private User getJessica() {
        User user = new User();
        user.setEmail("jessica.jones@demo.com");
        user.setUsername("jessica.jones");
        user.setFullName("Jessica Campbell Jones Cage");
        user.setEnabled(true);
        return user;
    }

    private Note createNote(User user, String summary, String description) {
        Note note = new Note();
        note.setUser(user);
        note.setSummary(summary);
        note.setDescription(description);
        return note;
    }

}
