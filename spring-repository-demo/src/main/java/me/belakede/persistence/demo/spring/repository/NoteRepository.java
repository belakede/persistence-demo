package me.belakede.persistence.demo.spring.repository;

import me.belakede.persistence.demo.spring.domain.Note;
import me.belakede.persistence.demo.spring.domain.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface NoteRepository extends CrudRepository<Note, Long> {

    List<Note> findByUser(User user);

}
