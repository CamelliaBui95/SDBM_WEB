package fr.btn.sdbm_web.security;


public class Main {
    public static void main(String[] args) {
        String pass1 = "buituongnghi";
        String pass2 = "HelloWorld";

        String hashedPass1 = Argon2.getHashedPassword(pass1);
        String hashedPass2 = Argon2.getHashedPassword(pass2);

        System.out.println("Pass1 = " + hashedPass1);
        System.out.println("Pass2 = " + hashedPass2);

        System.out.println(Argon2.validate(pass1, hashedPass1));
    }
}
