package me.belakede.persistence.demo.jpa.dao;

import me.belakede.persistence.demo.jpa.domain.Note;
import me.belakede.persistence.demo.jpa.domain.User;

public class DaoManager {

    private GenericDao<Note> noteDao;
    private GenericDao<User> userDao;

    private DaoManager() {
    }

    public GenericDao<Note> getNoteDao() {
        if (noteDao == null) {
            noteDao = new DefaultGenericDao<Note>(Note.class) {
            };
        }
        return noteDao;
    }

    public GenericDao<User> getUserDao() {
        if (userDao == null) {
            userDao = new DefaultGenericDao<>(User.class);
        }
        return userDao;
    }

    public static DaoManager getInstance() {
        return DaoManagerHolder.INSTANCE;
    }

    private static class DaoManagerHolder {
        private static final DaoManager INSTANCE = new DaoManager();
    }

}
