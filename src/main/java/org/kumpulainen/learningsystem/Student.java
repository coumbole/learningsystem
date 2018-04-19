package org.kumpulainen.learningsystem;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "student")
public class Student extends User implements Serializable {

    @Id
    private String code;

    private String password, name, email;

    public Student() {}

    public Student(String code_, String pwd, String name, String email) {
        this.code = code_;
        this.password = this.hasher.hash(pwd);
        this.name = name;
        this.email = email;
    }

    @Override
    String getCode() {
        return this.code;
    }

    @Override
    String getName() {
        return this.name;
    }

    @Override
    String getEmail() {
        return this.email;
    }

    @Override
    boolean login(String password) {
        return this.hasher.verifyHash(password, this.password);
    }

    @Override
    public String toString() {
        return String.format("Student(%s, %s, %s)",
                this.getCode(),
                        this.getName(),
                        this.getEmail());
    }
}
