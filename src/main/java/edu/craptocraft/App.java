package edu.craptocraft;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {

        /**
         * Crea una Address en nuestro sistema para Rick
         * Genera las claves privada y publica de la direccion
         * El balance de enZinium de su direccion es cero
         * El simbolo del enZinium es EZI
         */

        Address rick = new Address();
        rick.generateKeyPair();

    }
}
