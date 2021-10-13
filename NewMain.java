package com.mycompany.prolo;

import java.io.IOException;
import java.util.Scanner;
import java.util.Stack;

public class NewMain {

    public static void main(String[] args) {
        Scanner leer = new Scanner(System.in);
        boolean continue1 = true;
        int opcion;

        while (continue1) {
            System.out.println("");
            System.out.println("1. Exprecion Aritmetica");
            System.out.println("2. Exprecion Logica");
            System.out.println("3. Exprecion Logica V2");
            System.out.println("4. Salir");
            System.out.println("Opcion elegida: ");
            opcion = leer.nextInt();
            System.out.println("\n");

            switch (opcion) {
                case 1:
                    System.out.println("Expresión");
                    Scanner read = new Scanner(System.in);
                    String expres = read.nextLine();
                    String expresion = depurar(expres);
                    System.out.println("expresion: " + expres);
                    Analizador analizador = new Analizador();
                    try {
                        System.out.println("Resultado: " + analizador.evaluar(expres));
                        System.out.println();
                    } catch (Excepciones exc) {
                        System.out.println(exc);
                    }
                    String[] arrayInfix = expresion.split(" ");

                    Stack< String> E = new Stack< String>();
                    Stack< String> P = new Stack< String>();
                    Stack< String> S = new Stack< String>();

                    for (int i = arrayInfix.length - 1; i >= 0; i--) {
                        E.push(arrayInfix[i]);
                    }
                    try {
                        while (!E.isEmpty()) {
                            switch (pref(E.peek())) {
                                case 1:
                                    P.push(E.pop());
                                    break;
                                case 3:
                                case 4:
                                    while (pref(P.peek()) >= pref(E.peek())) {
                                        S.push(P.pop());
                                    }
                                    P.push(E.pop());
                                    break;
                                case 2:
                                    while (!P.peek().equals("(")) {
                                        S.push(P.pop());
                                    }
                                    P.pop();
                                    E.pop();
                                    break;
                                default:
                                    S.push(E.pop());
                            }
                        }
                        String infix = expresion.replace(" ", "");
                        String postfix = S.toString().replaceAll("[\\]\\[,]", "");

                        System.out.println("Expresion postfija: " + postfix);

                    } catch (Exception ex) {
                        System.out.println("Error en la expresion");
                        System.err.println(ex);
                    }

                    break;

                case 2:
                    ExpLo clasee = new ExpLo();
                    clasee.metodo();
                    break;

                case 3:
                    ExpLoV2 clase = new ExpLoV2();
                    clase.metodo();
                    break;

                case 4:
                    continue1 = false;
                    break;

                default:
                    System.out.println("La opcion elegida no es aceptada");
                    break;

            }

        }
    }

    private static String depurar(String s) {
        s = s.replaceAll("\\s+", "");
        s = "(" + s + ")";
        String simbols = "+-*/()";
        String str = "";

        for (int i = 0; i < s.length(); i++) {
            if (simbols.contains("" + s.charAt(i))) {
                str += " " + s.charAt(i) + " ";
            } else {
                str += s.charAt(i);
            }
        }
        return str.replaceAll("\\s+", " ").trim();
    }

    private static int pref(String op) {
        int prf = 0;
        if (op.equals("^")) {
            prf = 5;
        }
        if (op.equals("*") || op.equals("/")) {
            prf = 4;
        }
        if (op.equals("+") || op.equals("-")) {
            prf = 3;
        }
        if (op.equals(")")) {
            prf = 2;
        }
        if (op.equals("(")) {
            prf = 1;
        }
        return prf;
    }

}
