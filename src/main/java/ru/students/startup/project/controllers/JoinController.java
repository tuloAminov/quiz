package ru.students.startup.project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.students.startup.project.dto.QuestionForm;
import ru.students.startup.project.dto.Result;
import ru.students.startup.project.dto.Room;
import ru.students.startup.project.exception.RoomLoginException;
import ru.students.startup.project.services.JoinService;
import ru.students.startup.project.services.QuizService;

import java.util.List;

@Controller
public class JoinController {

    private JoinService joinService;
    private QuizService quizService;

    @Autowired
    Result result;
    Boolean submitted = false;

    @ModelAttribute("result")
    public Result getResult() {
        return result;
    }

    @Autowired
    public JoinController(QuizService quizService, JoinService joinService) {
        this.quizService = quizService;
        this.joinService = joinService;
    }

    @GetMapping("/join")
    public String join(Model model){
        model.addAttribute("room", new Room());
        return "UserRoom";
    }

    @PostMapping("/playing")
    public String joining(Room room) throws RoomLoginException {
        if (joinService.joining(room.getId())){
            Long id = room.getId();
            return "redirect:/playing/" + id;
        }
        else{
            throw new RoomLoginException("invalid id");
        }
    }

    @GetMapping("/playing/{id}")
    public String playing(@PathVariable("id") Long id, Model model) {
        QuestionForm qForm = quizService.getQuestions(joinService.getRoomById(id));
        model.addAttribute("qForm", qForm);
        return "quiz";
    }

    @ExceptionHandler(RoomLoginException.class)
    public String handleError(Model model, RoomLoginException exception){
        model.addAttribute("errorMessage", exception.getMessage());
        return "errors/404";
    }

    @PostMapping("/submit")
    public String submit(@ModelAttribute QuestionForm qForm, Model m) {
        if(!submitted) {
            result.setTotalCorrect(quizService.getResult(qForm));
            quizService.saveScore(result);
            submitted = true;
        }

        return "result";
    }

    @GetMapping("/score")
    public String score(Model m) {
        List<Result> sList = quizService.getTopScore();
        m.addAttribute("sList", sList);

        return "scoreboard";
    }
}