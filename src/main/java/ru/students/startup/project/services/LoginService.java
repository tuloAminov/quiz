package ru.students.startup.project.services;

import org.springframework.stereotype.Service;
import ru.students.startup.project.dto.LoginForm;

import java.util.logging.Logger;

@Service
public class LoginService {

    private Logger logger = Logger.getLogger(String.valueOf(LoginService.class));

    public boolean authenticate(LoginForm loginForm) {
        logger.info("try auth with user-form: " + loginForm);
        return loginForm.getUsername().equals("root") &&
                loginForm.getPassword().equals("123");
    }
}