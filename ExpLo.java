package com.mycompany.prolo;

public class ExpLo {

    public static void metodo() {
        String exp = "[ ( p -> q ) ^ p ] -> q";
        String[] p = {"v", "v", "f", "f"};
        String[] q = {"v", "f", "v", "f"};
        String[] res = new String[4];
        String[] res2 = new String[4];
        String[] res3 = new String[4];

        System.out.println("La exprecion es: " + exp);

        //condicion ->
        if (p[0].equals("v") && q[0].equals("v")) {
            res[0] = "v";
        } else {
            res[0] = "f";
        }

        if (p[1].equals("v") && q[1].equals("f")) {
            res[1] = "f";
        } else {
            res[1] = "v";
        }

        if (p[2].equals("f") && q[2].equals("v")) {
            res[2] = "v";
        } else {
            res[2] = "f";
        }

        if (p[3].equals("f") && q[3].equals("f")) {
            res[3] = "v";
        } else {
            res[3] = "f";
        }

        // ^
        if (res[0].equals("v") && p[0].equals("v")) {
            res2[0] = "v";
        } else {
            res2[0] = "f";
        }

        if (res[1].equals("f") && p[1].equals("v")) {
            res2[1] = "f";
        } else {
            res2[1] = "v";
        }

        if (res[2].equals("v") && p[2].equals("f")) {
            res2[2] = "f";
        } else {
            res2[2] = "v";
        }

        if (res[3].equals("v") && p[3].equals("f")) {
            res2[3] = "f";
        } else {
            res2[3] = "v";
        }

        //condicion ->
        if (res2[0].equals("v") && q[0].equals("v")) {
            res3[0] = "v";
        } else {
            res3[0] = "f";
        }

        if (res2[1].equals("f") && q[1].equals("f")) {
            res3[1] = "v";
        } else {
            res3[1] = "f";
        }

        if (res2[2].equals("f") && q[2].equals("v")) {
            res3[2] = "v";
        } else {
            res3[2] = "f";
        }

        if (res2[3].equals("f") && q[3].equals("f")) {
            res3[3] = "v";
        } else {
            res3[3] = "f";
        }
        System.out.println();
        System.out.print("(p->q    ");
        System.out.print("^    ");
        System.out.println("->  ");
        for (int i = 0; i < 4; i++) {
            System.out.print("  " + res[i] + "    ");
            System.out.print("  " + res2[i] + "   ");
            System.out.println(" " + res3[i]);
        }
    }
}
