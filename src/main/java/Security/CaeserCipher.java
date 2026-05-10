package Security;

public class CaeserCipher {
    public String encrypt(String plainText, int key) {
        plainText = plainText.toLowerCase();
        char[] charArr = plainText.toCharArray();

        for (int i = 0; i < charArr.length; i++) {
            // Formula: E(x) = (x + k) mod 26
            charArr[i] = (char) ('a' + (charArr[i] - 'a' + key) % 26);
        }
        return new String(charArr);
    }

    public String decrypt(String cipherText, int key) {
        cipherText = cipherText.toLowerCase();
        char[] charArr = cipherText.toCharArray();

        for (int i = 0; i < charArr.length; i++) {
            // Formula: D(x) = (x - k + 26) mod 26
            charArr[i] = (char) ('a' + (charArr[i] - 'a' - key + 26) % 26);
        }
        return new String(charArr);
    }

    public int analyse(String plainText, String cipherText) {
        plainText = plainText.toLowerCase();
        cipherText = cipherText.toLowerCase();
        int key = (cipherText.charAt(0) - plainText.charAt(0) + 26) % 26;
        return key;
    }
}
