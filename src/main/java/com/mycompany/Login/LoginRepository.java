package com.mycompany.Login;

import com.mycompany.Login.Login;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginRepository extends JpaRepository<Login, Integer> {
    Login findByUsernameAndPassword(String username, String password);

}