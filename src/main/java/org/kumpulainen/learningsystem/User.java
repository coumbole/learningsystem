package org.kumpulainen.learningsystem;

import java.util.logging.Logger;

public class User {

    protected static final Logger logger = Logger.getLogger(User.class.getName());

    private Hasher hasher = new Hasher(8);

    private String code, password, name, email;

    public User() {}

    public User(String code, String pwd, String name, String email) {
        this.code = code;
        this.password = hasher.hash(pwd);
        this.name = name;
        this.email = email;
        logger.info("Created a user with code: " + this.code);
    }

    public String getCode() {
        return this.code;
    }

    public String getName() {
        return this.name;
    }

    public String getEmail() {
        return this.email;
    }

    public boolean login(String password) {
        return hasher.verifyHash(password, this.password);
    }

}
