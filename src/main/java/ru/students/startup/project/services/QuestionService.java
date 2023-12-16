package ru.students.startup.project.services;

import org.springframework.beans.factory.annotation.Autowired;
import ru.students.startup.project.dto.Question;
import ru.students.startup.project.dto.Room;

import java.util.List;

public class QuestionService {

    private QuestionRepository questionRepository;

    @Autowired
    public QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    public List<Question> queryAllByRoom(Room room) {
        return questionRepository.queryAllByRoom(room);
    }
}
