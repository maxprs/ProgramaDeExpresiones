package com.mycompany.prolo;

public class ExpLoV2 {

    public static void metodo() {
        boolean valid;
        String numeros = "";
        String caracteres = "";
        String cadenas = "";
        //contadores 
        int nnumeros = 0;
        int ncaracteres = 0;
        int ncadenas = 0;
        //expresion a analizar separar con espacios
        String expresion = "( 9 + 9 ) * 0.2 / 6^2 - b7.6";
        //arreglo donde se guarda la cadena separada
        String exp[] = expresion.split(" ");
        for (int i = 0; i < exp.length; i++) {
            //metodo (matches) para ver si el [i] es numero
            // "\\d" indica que hay un numero
            // "*"  indica que un conjunto de caracteres se pueden repetir o no
            // https://www.discoduroderoer.es/metodo-string-matches-de-java/
            valid = exp[i].matches("\\d*");
            if (valid == true) {
                nnumeros++;
                numeros += " " + exp[i];
            }
            if (valid == false) {
                if (exp[i].length() == 1) {
                    ncaracteres++;
                    caracteres += " " + exp[i];
                } else {
                    ncadenas++;
                    cadenas += " " + exp[i];
                }
            }
        }
        System.out.println("Cantidad de numeros: " + nnumeros);
        System.out.println("Numeros: " + numeros);
        System.out.println("-------------------------------");
        System.out.println("Cantidad de caracteres: " + ncaracteres);
        System.out.println("Caracteres: " + caracteres);
        System.out.println("-------------------------------");
        System.out.println("Cantidad de cadenas: " + ncadenas);
        System.out.println("Cadenas: " + cadenas + "\n" + "\n");
    }
}
