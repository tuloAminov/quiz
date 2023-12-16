package ru.students.startup.project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.students.startup.project.dto.Room;
import ru.students.startup.project.services.RoomService;

import java.util.logging.Logger;

@Controller
public class CreateController {

    private Logger logger = Logger.getLogger(String.valueOf(CreateController.class));
    private RoomService roomService;

    @Autowired
    public CreateController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping("/create")
    public String rooms(Model model){
        logger.info(this.toString());
        model.addAttribute("room", new Room());
        return "create";
    }

    @PostMapping("create/save")
    public String saveRoom(Room room, Model model) {
        roomService.saveRoom(room);
        return "redirect:/main";
    }
}
