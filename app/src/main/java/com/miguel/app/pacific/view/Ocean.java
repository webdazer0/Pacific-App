package com.miguel.app.pacific.view;

public class  Ocean {
    private Square[][] map;
    int n = 20;
    int turn = 0;

    public Ocean() {
        buildMap();
    }

    private void buildMap() {
        map = new Square[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                map[i][j] = new Square(PacificType.VUOTO, 0, 0);
            }
        }

    }

    public Square[][] getMap() {
        return map;
    }

    public void run(int total) {
        for (int i = 0; i < total; i++) turn();
     }

    public void turn() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {

                if (map[i][j].getType() != PacificType.VUOTO && map[i][j].getTurn() == turn) {

                    map[i][j].setTurn(turn + 1);

                        int di, dj;
                        di = map[i][j].getdRow();
                        dj = map[i][j].getdColumn();


                        di = i + di;
                        dj = j + dj;

                        while (di >= n)
                            di -= n;
                        while (di < 0)
                            di += n;
                        while (dj >= n)
                            dj -= n;
                        while (dj < 0)
                            dj += n;

                        if (map[di][dj].getType() == PacificType.VUOTO
                                || (map[i][j].getType() == PacificType.ORCA && map[di][dj].getType() == PacificType.LEONE_MARINO)) {
                            map[di][dj] = map[i][j];
                            map[i][j] = new Square();
                        }


                }

            }
        }

        turn++;
    }


//    private void loadAnimals() {
//        matrice[1][1] = new Casella(SavanaTipo.LEONE, 1, 1);
//
//        matrice[6][3] = new Casella(SavanaTipo.GAZZELLA, 6, 0);
//        matrice[8][12] = new Casella(SavanaTipo.GAZZELLA, 7, 2);
//        matrice[1][11] = new Casella(SavanaTipo.GAZZELLA, 7, 3);
//        matrice[19][2] = new Casella(SavanaTipo.GAZZELLA, 8, 1);
//        matrice[12][12] = new Casella(SavanaTipo.GAZZELLA, 6, 0);
//        matrice[8][7] = new Casella(SavanaTipo.GAZZELLA, 7, 2);
//        matrice[9][9] = new Casella(SavanaTipo.GAZZELLA, 7, 3);
//        matrice[9][1] = new Casella(SavanaTipo.GAZZELLA, 8, 1);
//        matrice[6][0] = new Casella(SavanaTipo.GAZZELLA, 6, 0);
//        matrice[9][5] = new Casella(SavanaTipo.GAZZELLA, 7, 2);
//        matrice[5][3] = new Casella(SavanaTipo.GAZZELLA, 7, 3);
//        matrice[19][6] = new Casella(SavanaTipo.GAZZELLA, 8, 1);
//
//        matrice[14][10] = new Casella(SavanaTipo.GAZZELLA, 6, 0);
//        matrice[17][11] = new Casella(SavanaTipo.GAZZELLA, 7, 2);
//        matrice[10][15] = new Casella(SavanaTipo.GAZZELLA, 7, 3);
//        matrice[19][16] = new Casella(SavanaTipo.GAZZELLA, 8, 1);
//        matrice[3][16] = new Casella(SavanaTipo.GAZZELLA, 6, 0);
//
//        matrice[1][16] = new Casella(SavanaTipo.GAZZELLA, 7, 2);
//        matrice[6][17] = new Casella(SavanaTipo.GAZZELLA, 7, 3);
//        matrice[1][19] = new Casella(SavanaTipo.GAZZELLA, 8, 1);
//        matrice[19][19] = new Casella(SavanaTipo.GAZZELLA, 6, 0);
//        matrice[15][19] = new Casella(SavanaTipo.GAZZELLA, 7, 2);
//        matrice[5][8] = new Casella(SavanaTipo.GAZZELLA, 7, 3);
//        matrice[4][7] = new Casella(SavanaTipo.GAZZELLA, 8, 1);
//        matrice[2][12] = new Casella(SavanaTipo.GAZZELLA, 6, 0);
//        matrice[8][13] = new Casella(SavanaTipo.GAZZELLA, 7, 2);
//        matrice[1][5] = new Casella(SavanaTipo.GAZZELLA, 7, 3);
//        matrice[17][5] = new Casella(SavanaTipo.GAZZELLA, 8, 1);
//        matrice[16][4] = new Casella(SavanaTipo.GAZZELLA, 6, 0);
//
//        matrice[8][8] = new Casella(SavanaTipo.LEONE, 2, 2);
//        matrice[14][6] = new Casella(SavanaTipo.LEONE, 3, 3);
//        matrice[16][16] = new Casella(SavanaTipo.LEONE, 4, 0);
//        matrice[3][15] = new Casella(SavanaTipo.LEONE, 5, 1);
//        matrice[12][2] = new Casella(SavanaTipo.LEONE, 1, 1);
//        matrice[12][4] = new Casella(SavanaTipo.LEONE, 2, 2);
//        matrice[12][11] = new Casella(SavanaTipo.LEONE, 3, 3);
//        matrice[8][1] = new Casella(SavanaTipo.LEONE, 4, 0);
//        matrice[2][9] = new Casella(SavanaTipo.LEONE, 5, 1);
//
//        matrice[17][7] = new Casella(SavanaTipo.GAZZELLA, 6, 0);
//        matrice[16][11] = new Casella(SavanaTipo.GAZZELLA, 7, 2);
//
//    }

    public int getTurn() {
        return turn;
    }

//
//    public void stampa() {
//        int g = 0, l = 0;
//
//        for (Casella[] riga : matrice) {
//            for (Casella elem : riga) {
//                if (elem.tipo == SavanaTipo.VUOTO)
//                    System.out.print("-");
//                if (elem.tipo == SavanaTipo.LEONE) {
//                    System.out.print("L");
//                    l++;
//                }
//                if (elem.tipo == SavanaTipo.GAZZELLA) {
//                    System.out.print("G");
//                    g++;
//                }
//                System.out.print(" ");
//            }
//            System.out.println();
//        }
//        System.out.println("Turno: " + turno + " Gazelle: " + g + " | Leoni: " + l);
//    }
//
//    public void turno_OLD() {
//        for (int i = 0; i < n; i++) {
//            for (int j = 0; j < n; j++) {
//
//                if (matrice[i][j].tipo != SavanaTipo.VUOTO && matrice[i][j].turno == turno) {
//
//                    matrice[i][j].turno = turno + 1;
//
//                    for (int k = 0; k < 4; k++) {
//
//                        int direzione = matrice[i][j].direzione;
//                        int di = 0, dj = 0;
//
//                        if (direzione == 0) {
//                            di = -1;
//                            dj = 0;
//                        }
//                        if (direzione == 1) {
//                            di = 0;
//                            dj = 1;
//                        }
//                        if (direzione == 2) {
//                            di = 1;
//                            dj = 0;
//                        }
//                        if (direzione == 3) {
//                            di = 0;
//                            dj = -1;
//                        }
//
//                        di = i + di * matrice[i][j].movimento;
//                        dj = j + dj * matrice[i][j].movimento;
//
//                        while (di >= n)
//                            di -= n;
//                        while (di < 0)
//                            di += n;
//                        while (dj >= n)
//                            dj -= n;
//                        while (dj < 0)
//                            dj += n;
//
//                        if (matrice[di][dj].tipo == SavanaTipo.VUOTO
//                                || (matrice[i][j].tipo == SavanaTipo.LEONE && matrice[di][dj].tipo == SavanaTipo.GAZZELLA)) {
//                            matrice[di][dj] = matrice[i][j];
//                            matrice[i][j] = new Casella();
//                            break;
//                        }
//
//                        matrice[i][j].direzione = (matrice[i][j].direzione + 1) % 4;
//
//                    }
//
//                }
//
//            }
//        }
//
//        turno++;
//    }

}
