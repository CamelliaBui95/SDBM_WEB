package fr.btn.sdbm_web.security;

import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;

public class Argon2 {
    private final static int SALT_LENGTH = 16;
    private final static int KEY_LENGTH = 32;
    private final static int ITERATIONS = 10;
    private final static int MEMORY = 2 ^ 24;
    private final static int PARALLELISM = 1;

    private final static String SUFFIX = "$argon2id$v=19$m=26,t=10,p=1$";

    public static String getHashedPassword(String rawPassword) {
        return getEncode(rawPassword).substring(SUFFIX.length());
    }

    private static String getEncode(String password) {
        Argon2PasswordEncoder argon2 = new Argon2PasswordEncoder(SALT_LENGTH,KEY_LENGTH,PARALLELISM,MEMORY,ITERATIONS);

        return argon2.encode(password);
    }

    public static boolean validate(String rawPassword, String hashedPassword) {
        hashedPassword = SUFFIX + hashedPassword;
        return new Argon2PasswordEncoder(SALT_LENGTH,KEY_LENGTH,PARALLELISM,MEMORY,ITERATIONS).matches(rawPassword, hashedPassword);
    }
}
