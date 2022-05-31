package com.mycompany.Login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoginService {

    @Autowired
    private LoginRepository repo;

    public List<Login> listAll() {
        return (List<Login>) repo.findAll();
    }

    public void save(Login login) {
        repo.save(login);
    }

    public boolean login(String username, String password) {
        List<Login> userList = listAll();
        boolean check = false;
        for (int i = 0; i < userList.size(); i++) {
            if (userList.get(i).getUsername().trim().toLowerCase().equals(username.trim().toLowerCase())) {
                if (userList.get(i).getPassword().equals(password)) {
                    check = true;
                    break;
                }
            }
        }
        return check;
    }

    public int gettypeuser(String username) {
        List<Login> userList = listAll();
        int type = 0;
        for (int i = 0; i < userList.size(); i++) {
            if (userList.get(i).getUsername().toLowerCase().trim().equals(username.toLowerCase().trim())) {
                type = userList.get(i).getTypeuser();
            }
        }
        return type;
    }
}
