package ru.students.startup.project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.students.startup.project.dto.Room;

import java.util.List;
import java.util.logging.Logger;

@Service
public class JoinService {

    private final ProjectRepository<Room> roomRepo;
    private final Logger logger = Logger.getLogger(String.valueOf(RoomService.class));

    @Autowired
    public JoinService(ProjectRepository<Room> roomRepo) {
        this.roomRepo = roomRepo;
    }

    public boolean joining(Long id) {
        logger.info("try join with id: " + id);
        return roomRepo.retrieveAll().contains(id);
    }

    public Room getRoomById(Long id) {
        return roomRepo.getRoomById(id);
    }
}