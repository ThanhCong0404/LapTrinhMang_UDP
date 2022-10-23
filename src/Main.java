import Algorithms.CaesarCipher;

public class Main {
    public static void main(String[] args) {
        String text = "ASDZXCASDZXC";

        String str =  CaesarCipher.encrypt(text,3).toString();
        System.out.println(str);
        String str2 = CaesarCipher.decrypt(str,3).toString();
        System.out.println(str2);

    }
}
