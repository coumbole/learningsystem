package org.kumpulainen.learningsystem;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "teacher")
public class Teacher {
    @Id
    private String code;

    private String password, name, email, role;

    public Teacher(String code, String pwd, String name, String email, String role) {
        this.code = code;
        this.password = pwd;
        this.name = name;
        this.email = email;
        this.role = role;
    }

}
