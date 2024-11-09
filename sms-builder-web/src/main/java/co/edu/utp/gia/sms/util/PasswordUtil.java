package co.edu.utp.gia.sms.util;
import io.quarkus.elytron.security.common.BcryptUtil;

public class PasswordUtil {
    public static String hashedPassword(String password) {
        return BcryptUtil.bcryptHash(password);
    }

    public static boolean verifyPassword(String passwordToVerify, String passwordStored) {
        return BcryptUtil.matches(passwordToVerify, passwordStored);
    }
}
