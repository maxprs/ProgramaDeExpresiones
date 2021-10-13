package com.mycompany.prolo;

class Excepciones extends Exception {

    String errStr;

    public Excepciones(String str) {
        errStr = str;
    }

    public String toString() {
        return errStr;
    }
}

public class Analizador {

    final int NINGUNO = 0;
    final int DELIMITADOR = 1;
    final int VARIABLE = 2;
    final int NUMERO = 3;

    final int SYNTAXIS = 0;
    final int PARENTESIS = 1;
    final int SINEXP = 2;
    final int DIVENTRECERO = 3;

    final String FINEXP = "\0";

    private String exp;
    private int expIndice;
    private String token;
    private int tipoToken;

    public double evaluar(String cadenaExp) throws Excepciones {
        double resultado;
        exp = cadenaExp;
        expIndice = 0;
        obtieneToken();
        if (token.equals(FINEXP)) {
            obtieneError(SINEXP);
        }

        resultado = evaluarExp2();
        if (!token.equals(FINEXP)) {
            obtieneError(SYNTAXIS);
        }
        return resultado;
    }

    private double evaluarExp2() throws Excepciones {
        char op;
        double resultado;
        double resultadoParcial;
        resultado = evaluarExp3();
        while ((op = token.charAt(0)) == '+' || op == '-') {
            obtieneToken();
            resultadoParcial = evaluarExp3();
            switch (op) {
                case '-':
                    resultado = resultado - resultadoParcial;
                    break;
                case '+':
                    resultado = resultado + resultadoParcial;
                    break;
            }
        }
        return resultado;
    }

    private double evaluarExp3() throws Excepciones {
        char op;
        double resultado;
        double resultadoParcial;
        resultado = evaluarExp4();
        while ((op = token.charAt(0)) == '*' || op == '/' || op == '%') {
            obtieneToken();
            resultadoParcial = evaluarExp4();
            switch (op) {
                case '*':
                    resultado = resultado * resultadoParcial;
                    break;
                case '/':
                    if (resultadoParcial == 0.0) {
                        obtieneError(DIVENTRECERO);
                    }
                    resultado = resultado / resultadoParcial;
                    break;
                case '%':
                    if (resultadoParcial == 0.0) {
                        obtieneError(DIVENTRECERO);
                    }
                    resultado = resultado % resultadoParcial;
                    break;
            }
        }
        return resultado;
    }

    private double evaluarExp4() throws Excepciones {
        double resultado;
        double resultadoParcial;
        double ex;
        int t;
        resultado = evaluarExp5();
        if (token.equals("^")) {
            obtieneToken();
            resultadoParcial = evaluarExp4();
            ex = resultado;
            if (resultadoParcial == 0.0) {
                resultado = 1.0;
            } else {
                for (t = (int) resultadoParcial - 1; t > 0; t--) {
                    resultado = resultado * ex;
                }
            }
        }
        return resultado;
    }

    private double evaluarExp5() throws Excepciones {
        double resultado;
        String op;
        op = "";
        if ((tipoToken == DELIMITADOR) && token.equals("+") || token.equals("-")) {
            op = token;
            obtieneToken();
        }
        resultado = evaluarExp6();
        if (op.equals("-")) {
            resultado = -resultado;
        }
        return resultado;
    }

    private double evaluarExp6() throws Excepciones {
        double resultado;
        if (token.equals("(")) {
            obtieneToken();
            resultado = evaluarExp2();
            if (!token.equals(")")) {
                obtieneError(PARENTESIS);
            }
            obtieneToken();
        } else {
            resultado = valor();
        }
        return resultado;
    }

    private double valor() throws Excepciones {
        double resultado = 0.0;
        switch (tipoToken) {
            case NUMERO:
            try {
                resultado = Double.parseDouble(token);
            } catch (NumberFormatException exc) {
                obtieneError(SYNTAXIS);
            }
            obtieneToken();
            break;
            default:
                obtieneError(SYNTAXIS);
                break;
        }
        return resultado;
    }

    private void obtieneError(int error) throws Excepciones {
        String[] err = {
            "ERROR DE SYNTAXIS",
            "PARENTESIS NO BALANCEADOS",
            "NO EXISTE EXPRESION",
            "DIVISION POR CERO"
        };
        throw new Excepciones(err[error]);
    }

    private void obtieneToken() {
        tipoToken = NINGUNO;
        token = "";
        if (expIndice == exp.length()) {
            token = FINEXP;
            return;
        }
        while (expIndice < exp.length() && Character.isWhitespace(exp.charAt(expIndice))) {
            ++expIndice;
        }
        if (expIndice == exp.length()) {
            token = FINEXP;
            return;
        }
        if (esDelimitador(exp.charAt(expIndice))) {
            token += exp.charAt(expIndice);
            expIndice++;
            tipoToken = DELIMITADOR;
        } else if (Character.isLetter(exp.charAt(expIndice))) {
            while (!esDelimitador(exp.charAt(expIndice))) {
                token += exp.charAt(expIndice);
                expIndice++;
                if (expIndice >= exp.length()) {
                    break;
                }
            }
            tipoToken = VARIABLE;
        } else if (Character.isDigit(exp.charAt(expIndice))) {
            while (!esDelimitador(exp.charAt(expIndice))) {
                token += exp.charAt(expIndice);
                expIndice++;
                if (expIndice >= exp.length()) {
                    break;
                }
            }
            tipoToken = NUMERO;
        } else {
            token = FINEXP;
            return;
        }

    }

    private boolean esDelimitador(char c) {
        if (("+-/*^=%()".indexOf(c) != -1)) {
            return true;
        } else {
            return false;
        }
    }
}
