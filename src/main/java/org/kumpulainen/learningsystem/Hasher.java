package org.kumpulainen.learningsystem;

import org.mindrot.jbcrypt.BCrypt;

// Hashing best practices from https://www.stubbornjava.com/posts/hashing-passwords-in-java-with-bcrypt
public class Hasher {

    private final int logRounds;

    public Hasher(int logRounds) {
        this.logRounds = logRounds;
    }

    public String hash(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt(logRounds));
    }

    public boolean verifyHash(String password, String hash) {
        return BCrypt.checkpw(password, hash);
    }

}
