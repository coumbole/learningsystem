package org.kumpulainen.learningsystem;

import javax.persistence.Id;
import java.util.logging.Logger;

public class User {

    protected static final Logger logger = Logger.getLogger(User.class.getName());

    private Hasher hasher = new Hasher(8);

    private String code;

    private String password, name, email;

    public User() {}

    // TODO: Hash password
    public User(String code, String pwd, String name, String email) {
        this.code = code;
        this.password = hasher.hash(pwd);
        logger.info(String.format("Hashed pwd %s as %s", pwd, this.password));
        logger.info(String.format("Verifying pwd: %s: %s", pwd, hasher.verifyHash(pwd, this.password)));
        logger.info(String.format("Verifying wrong pwd: %s: %s", "abcde", Boolean.toString(hasher.verifyHash("abcde", this.password))));
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
