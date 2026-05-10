package Security;

public class AutoKey {
    public String analyse(String plainText, String cipherText) {
        plainText = plainText.toLowerCase();
        cipherText = cipherText.toLowerCase();
        int len = plainText.length();

        StringBuilder autoKey = new StringBuilder();
        for (int i = 0; i < len; i++) {
            int p = plainText.charAt(i) - 'a';
            int c = cipherText.charAt(i) - 'a';
            autoKey.append((char) (((c - p + 26) % 26) + 'a'));
        }

        for (int keyLen = 1; keyLen <= len; keyLen++) {
            boolean valid = true;
            for (int i = keyLen; i < len; i++) {
                if (autoKey.charAt(i) != plainText.charAt(i - keyLen)) {
                    valid = false;
                    break;
                }
            }
            if (valid) return autoKey.substring(0, keyLen);
        }
        return autoKey.toString();
    }

    public String decrypt(String cipherText, String key) {
        cipherText = cipherText.toLowerCase();
        key = key.toLowerCase();
        int len = cipherText.length();

        StringBuilder autoKey = new StringBuilder(key);
        StringBuilder plainText = new StringBuilder();

        for (int i = 0; i < len; i++) {
            int c = cipherText.charAt(i) - 'a';
            int k = autoKey.charAt(i) - 'a';
            char p = (char) (((c - k + 26) % 26) + 'a');
            plainText.append(p);
            // The decrypted plaintext character extends the key
            if (autoKey.length() < len) {
                autoKey.append(p);
            }
        }
        return plainText.toString();
    }

    public String encrypt(String plainText, String key) {
        plainText = plainText.toLowerCase();
        key = key.toLowerCase();
        int len = plainText.length();

        // Extend key using the plaintext
        StringBuilder autoKey = new StringBuilder(key);
        if (autoKey.length() < len) {
            int diffLen = len - autoKey.length();
            for (int i = 0; i < diffLen; i++) {
                autoKey.append(plainText.charAt(i));
            }
        }

        StringBuilder cipherText = new StringBuilder();
        for (int i = 0; i < len; i++) {
            int p = plainText.charAt(i) - 'a';
            int k = autoKey.charAt(i) - 'a';
            cipherText.append((char) (((p + k) % 26) + 'a'));
        }
        return cipherText.toString();
    }
}
