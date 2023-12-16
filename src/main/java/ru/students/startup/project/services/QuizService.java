package ru.students.startup.project.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.students.startup.project.dto.Question;
import ru.students.startup.project.dto.QuestionForm;
import ru.students.startup.project.dto.Result;
import ru.students.startup.project.dto.Room;

@Service
public class QuizService {

    @Autowired
    Question question;
    @Autowired
    QuestionForm qForm;
    @Autowired
    QuestionRepository qRepo;
    @Autowired
    Result result;
    @Autowired
    ResultRepository rRepo;

    public QuestionForm getQuestions(Room room) {
        List<Question> allQues = qRepo.queryAllByRoom(room);

        qForm.setQuestions(allQues);

        return qForm;
    }

    public int getResult(QuestionForm qForm) {
        int correct = 0;

        for(Question q: qForm.getQuestions())
            if(q.getAns() == q.getChose())
                correct++;

        return correct;
    }

    public void saveScore(Result result) {
        Result saveResult = new Result();
        saveResult.setUsername(result.getUsername());
        saveResult.setTotalCorrect(result.getTotalCorrect());
        rRepo.save(saveResult);
    }

    public List<Result> getTopScore() {
        List<Result> sList = rRepo.findAll(Sort.by(Sort.Direction.DESC, "totalCorrect"));

        return sList;
    }
}