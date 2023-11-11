package com.edix.apirest.cinema.entities;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordEncoder {

    // Método para encriptar una contraseña
    public static String encryptPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    // Método para verificar si una contraseña sin cifrar coincide con la versión cifrada
    public static boolean checkPassword(String candidate, String hashedPassword) {
        return BCrypt.checkpw(candidate, hashedPassword);
    }
}
