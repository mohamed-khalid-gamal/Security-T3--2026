package Security;

public class RepeatingKey {
    public String analyse(String plainText, String cipherText) {
        plainText = plainText.toLowerCase();
        cipherText = cipherText.toLowerCase();
        StringBuilder keyStream = new StringBuilder();
        for (int i = 0; i < plainText.length(); i++) {
            int p = plainText.charAt(i) - 'a';
            int c = cipherText.charAt(i) - 'a';
            keyStream.append((char) (((c - p + 26) % 26) + 'a'));
        }
        String full = keyStream.toString();
        for (int len = 1; len <= full.length(); len++) {
            String candidate = full.substring(0, len);
            boolean valid = true;
            for (int i = 0; i < full.length(); i++) {
                if (full.charAt(i) != candidate.charAt(i % len)) {
                    valid = false;
                    break;
                }
            }
            if (valid) return candidate;
        }
        return full;
    }

    public String decrypt(String cipherText, String key) {
        cipherText = cipherText.toLowerCase();
        key = key.toLowerCase();
        int cipherLen = cipherText.length();

        StringBuilder extendedKey = new StringBuilder(key);
        while (extendedKey.length() < cipherLen) {
            extendedKey.append(extendedKey.charAt(extendedKey.length() - key.length()));
        }

        StringBuilder plainText = new StringBuilder();
        for (int i = 0; i < cipherLen; i++) {
            int c = cipherText.charAt(i) - 'a';
            int k = extendedKey.charAt(i) - 'a';
            plainText.append((char) (((c - k + 26) % 26) + 'a'));
        }
        return plainText.toString();
    }

    public String encrypt(String plainText, String key) {
        plainText = plainText.toLowerCase();
        key = key.toLowerCase();
        int plainLen = plainText.length();

        // Repeat key to match plaintext length
        StringBuilder extendedKey = new StringBuilder(key);
        while (extendedKey.length() < plainLen) {
            extendedKey.append(extendedKey.charAt(extendedKey.length() - key.length()));
        }

        StringBuilder cipherText = new StringBuilder();
        for (int i = 0; i < plainLen; i++) {
            int p = plainText.charAt(i) - 'a';
            int k = extendedKey.charAt(i) - 'a';
            cipherText.append((char) (((p + k) % 26) + 'a'));
        }

        return cipherText.toString();
    }
}
