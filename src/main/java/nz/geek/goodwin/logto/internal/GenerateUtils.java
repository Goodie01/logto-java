package nz.geek.goodwin.logto.internal;

import nz.geek.goodwin.logto.domain.exceptions.LogtoJavaException;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.concurrent.ThreadLocalRandom;

public class GenerateUtils {
    private static final char[] RANDOM_CHARACTERS = {
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            '-', '.', '_', '~'
    };

    private static final String DEFAULT_ALGORITHM = "SHA-256";
    private static final int DEFAULT_RANDOM_STRING_LENGTH = 64;

    public static String generateCodeVerifier() {
        return generateRandomString(DEFAULT_RANDOM_STRING_LENGTH);
    }

    public static String generateState() {
        return generateRandomString(DEFAULT_RANDOM_STRING_LENGTH);
    }

    public static String generateCodeChallenge(final String codeVerifier) {
        try {
            return generateCodeChallengeException(codeVerifier);
        } catch (NoSuchAlgorithmException e) {
            throw new LogtoJavaException(e);
        }
    }

    private static String generateCodeChallengeException(final String codeVerifier) throws NoSuchAlgorithmException {
        MessageDigest digester = MessageDigest.getInstance(DEFAULT_ALGORITHM);
        digester.update(codeVerifier.getBytes(StandardCharsets.UTF_8));
        byte[] byteArray = digester.digest();
        return Base64.getUrlEncoder().withoutPadding().encodeToString(byteArray);
    }

    private static String generateRandomString(final int length) {
        StringBuilder test = new StringBuilder();
        for(int i = 0; i < length; i++) {
            test.append(RANDOM_CHARACTERS[ThreadLocalRandom.current().nextInt(RANDOM_CHARACTERS.length)]);
        }

        return test.toString();
    }
}
