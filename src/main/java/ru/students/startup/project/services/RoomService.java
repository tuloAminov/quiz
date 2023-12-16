package ru.students.startup.project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.students.startup.project.dto.Room;

import java.util.logging.Logger;

@Service
public class RoomService {

    private final ProjectRepository<Room> roomRepo;
    private final Logger logger = Logger.getLogger(String.valueOf(RoomService.class));

    @Autowired
    public RoomService(ProjectRepository<Room> roomRepo) {
        this.roomRepo = roomRepo;
    }

    public void saveRoom(Room room){
        roomRepo.store(room);
    }

    public Room getRoomById(Long id) {
        return roomRepo.getRoomById(id);
    }

    private void defaultInit() {
        logger.info("default INIT in book service");
    }

    private void defaultDestroy() {
        logger.info("default DESTROY in book service");
    }
}
