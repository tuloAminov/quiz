package ru.students.startup.project.services;

import ru.students.startup.project.dto.Room;

import java.util.List;

public interface ProjectRepository<T> {

    List<Long> retrieveAll();
    void store(T book);
    Room getRoomById(Long id);
}