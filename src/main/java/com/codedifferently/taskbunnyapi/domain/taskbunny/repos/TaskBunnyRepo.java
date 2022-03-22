package com.codedifferently.taskbunnyapi.domain.taskbunny.repos;

import com.codedifferently.taskbunnyapi.domain.taskbunny.models.TaskBunny;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface TaskBunnyRepo extends CrudRepository<TaskBunny, Long> {
    Optional<TaskBunny> findByEmail(String email);
}
