package me.belakede.persistence.demo.spring.repository;

import me.belakede.persistence.demo.spring.domain.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {

    /**
     *
     *  @see http://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods.query-creation
     */
    List<User> findByUsernameContaining(String username);

}
