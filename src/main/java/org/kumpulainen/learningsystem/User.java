package org.kumpulainen.learningsystem;

import java.util.logging.Logger;


public abstract class User {

    protected static final Logger logger = Logger.getLogger(User.class.getName());

    protected Hasher hasher = new Hasher(8);

    abstract String getCode();

    abstract String getName();

    abstract String getEmail();

    abstract boolean login(String password);
}
