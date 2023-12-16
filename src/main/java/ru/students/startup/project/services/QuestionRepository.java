package ru.students.startup.project.services;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.students.startup.project.dto.Question;
import ru.students.startup.project.dto.Room;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer> {
    List<Question> queryAllByRoom(Room room);
}