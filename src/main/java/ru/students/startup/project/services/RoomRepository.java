package ru.students.startup.project.services;

import org.apache.catalina.core.ApplicationContext;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContextAware;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.students.startup.project.dto.Question;
import ru.students.startup.project.dto.Room;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

@Repository
public class RoomRepository implements ProjectRepository<Room>, ApplicationContextAware {

    private final Logger logger = Logger.getLogger(String.valueOf(RoomRepository.class));
    private ApplicationContext context;

    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public RoomRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Long> retrieveAll() {
        List<Long> ids = jdbcTemplate.query("SELECT id FROM room", (ResultSet rs, int rowNum) -> {
            Long id;
            id = rs.getLong("id");
            return id;
        });
        return new ArrayList<>(ids);
    }

    @Override
    public void store(Room room) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("question",room.getQuestion());
        jdbcTemplate.update("INSERT INTO room(question) VALUES(:question)",parameterSource);
        logger.info("store new book: " + room);
    }

    @Override
    public Room getRoomById(Long id) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("id",id);
        List<Room> rooms = jdbcTemplate.query("SELECT * FROM room WHERE id = :id", parameterSource, (ResultSet rs, int rowNum) -> {
            Room room1 = new Room();
            Question question = new Question();
            room1.setId(rs.getLong("id"));

            return room1;
        });
        return rooms.get(0);
    }

    @Override
    public void setApplicationContext(org.springframework.context.ApplicationContext applicationContext) throws BeansException {

    }

    public void defaultInit(){
        logger.info("default INIT in book repo bean");
    }

    public void defaultDestroy(){
        logger.info("default DESTROY in book repo bean");
    }
}
