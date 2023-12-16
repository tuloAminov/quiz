package ru.students.startup.project.dto;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Component
@Entity
@Table(name = "room")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @OneToMany(mappedBy = "room")
    private List<Question> question = new ArrayList<>();

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public List<Question> getQuestion() {
        return question;
    }

    public void setQuestion(List<Question> question) {
        this.question = question;
    }

    @Override
    public String toString() {
        return "Room{" +
                "Id=" + Id +
                ", question=" + question +
                '}';
    }
}