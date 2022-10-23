package Algorithms;

public class CaesarCipher {

    public static StringBuffer encrypt(String text, int s)
    {
        char[] array = text.toCharArray();

        StringBuffer result = new StringBuffer();

        for (int i = 0; i < array.length; i++) {

            //  'a' - 'z'
            if ('a' <= array[i] && array[i] <= 'z') {
                result.append((char) (((array[i] - 'a' + s) % 26) + 'a'));

                // 'A' - 'Z'
            } else if ('A' <= array[i] && array[i] <= 'Z') {
                result.append((char) (((array[i] - 'A' + s) % 26) + 'A'));

            } else {
                result.append(array[i]);
            }
        }

        return result;
    }

    public static StringBuffer decrypt(String text, int s)
    {
        s = 26 - (s%26);

        char[] array = text.toCharArray();

        StringBuffer result = new StringBuffer();

        for (int i = 0; i < array.length; i++) {

            // 'a' - 'z'
            if ('a' <= array[i] && array[i] <= 'z') {
                result.append((char) (((array[i] - 'a' + s) % 26) + 'a'));

                // 'A' - 'Z'
            } else if ('A' <= array[i] && array[i] <= 'Z') {
                result.append((char) (((array[i] - 'A' + s) % 26) + 'A'));

            } else {
                result.append(array[i]);
            }
        }

        return result;
    }
}
