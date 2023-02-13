package edu.craptocraft;

import java.util.Random;

public class GenSig {
    private final String allChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    public GenSig() {}

    public String generateKey(){
        StringBuilder key = new StringBuilder();
        for (int i = 0; i<16; i++){
            Random random = new Random();
                  int  numeroRandom = random.nextInt(allChars.length() - 1);
                  char caracter =  (allChars.charAt(numeroRandom));
                  key.append(caracter);
        }
        return key.toString();
    }
}
