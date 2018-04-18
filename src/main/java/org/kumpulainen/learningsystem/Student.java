package org.kumpulainen.learningsystem;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "student")
public class Student extends User {

    @Id
    private String id;

    public Student() {}

    public Student(String code, String pwd, String name, String email) {
        super(code, pwd, name, email);
    }

    @Override
    public String toString() {
        return String.format("Student(%s, %s, %s)",
                this.getCode(),
                        this.getName(),
                        this.getEmail());
    }
}
