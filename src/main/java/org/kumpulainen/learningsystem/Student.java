package org.kumpulainen.learningsystem;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "student")
public class Student {
    @Id
    private String code;

    private String password, name, email;

    public Student() {}

    public Student(String code, String pwd, String name, String email) {
        this.code = code;
        this.password = pwd;
        this.name = name;
        this.email = email;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}
