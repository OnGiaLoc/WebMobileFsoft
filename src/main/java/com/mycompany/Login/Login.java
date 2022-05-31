package com.mycompany.Login;

import javax.persistence.*;

@Entity
@Table(name = "login")
public class Login {
    @Id
    @Column(length = 15,nullable = false)
    private String username;
    @Column(length = 15,nullable = false)
    private String password;

    private int typeuser;
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public int getTypeuser() {
        return typeuser;
    }

    public void setTypeuser(int typeuser) {
        this.typeuser = typeuser;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", typeuser=" + typeuser +
                '}';
    }
}
