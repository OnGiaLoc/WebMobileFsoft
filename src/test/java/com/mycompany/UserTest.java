package com.mycompany;


import com.mycompany.Login.Login;
import com.mycompany.Login.LoginRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class UserTest {

    @Autowired private LoginRepository repo;
    @Test
    public void testAddUser(){
        Login user = new Login();
        user.setUsername("admin");
        user.setPassword("admin");
        user.setTypeuser(1);

        Login saveUser = repo.save(user);
        Assertions.assertThat(saveUser).isNotNull();
    }


}
