package me.belakede.persistence.demo.jpa;

import me.belakede.persistence.demo.jpa.dao.DaoManager;
import me.belakede.persistence.demo.jpa.domain.Note;
import me.belakede.persistence.demo.jpa.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class Application {

    private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        new Application().run();
    }

    public void run() {
        if (!hasUsers()) {
            createUsers();
            createNotes();
        }
        printNotes();
    }

    private boolean hasUsers() {
        return DaoManager.getInstance().getUserDao().findAll().size() > 0;
    }

    private void createUsers() {
        LOGGER.trace("Create users...");
        DaoManager.getInstance().getUserDao().create(getJohn());
        DaoManager.getInstance().getUserDao().create(getJessica());
        LOGGER.debug("Created users: {}", DaoManager.getInstance().getUserDao().findAll());
    }

    private void createNotes() {
        LOGGER.trace("Create notes...");
        List<User> users = DaoManager.getInstance().getUserDao().findAll();
        DaoManager.getInstance().getNoteDao().create(createNote(users.get(0), "My First Note", "This is my first note."));
        DaoManager.getInstance().getNoteDao().create(createNote(users.get(0), "Second Note", "This is my second note."));
        LOGGER.debug("Created notes: {}", DaoManager.getInstance().getNoteDao().findAll());
    }

    private void printNotes() {
        LOGGER.trace("Print notes...");
        DaoManager.getInstance().getUserDao().clearCache();
        DaoManager.getInstance().getUserDao().findAll().forEach(user -> {
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
