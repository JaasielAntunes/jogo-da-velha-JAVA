// autor: Jaasiel Antunes

package br.com.estudos.principal;

import java.util.Scanner;

public class JogoDaVelha {
    public int[][] tabuleiro = new int[3][3];
    public static int jogador;
    public static int valor;
    public static Scanner ent = new Scanner(System.in);
    
    public boolean jogar(int x, int y) {
        if ((x < 0) || (x > 2) || (y < 0) || (y > 2)) {
            return false;
        }
        if (tabuleiro[x][y] != 0) {
            return false;
        }
        tabuleiro[x][y] = valor;
        valor = (valor == 1) ? 2 : 1;
        jogador = (jogador == 1) ? 2 : 1;
        return true;
    }

    public int vencedor() {
        for (int j = 1; j < 3; j++) {
        	
            // testa as linhas
            for (int linha = 0; linha < 3; linha++) {
                boolean fechou = true;
                for (int coluna = 0; coluna < 3; coluna++) {
                    if (tabuleiro[coluna][linha] != j) {
                        fechou = false;
                    }
                }
                if (fechou) {
                    return j;
                }
            }
            
            // testa as colunas
            for (int coluna = 0; coluna < 3; coluna++) {
                boolean fechou = true;
                for (int linha = 0; linha < 3; linha++) {
                    if (tabuleiro[coluna][linha] != j) {
                        fechou = false;
                    }
                }
                if (fechou) {
                    return j;
                }
            }
            
            // testa as diagonais
            boolean fechou = true;
            for (int posicao = 0; posicao < 3; posicao++) {
                if (tabuleiro[posicao][posicao] != j) {
                    fechou = false;
                }
            }
            if (fechou) {
                return j;
            }
            fechou = true;
            for (int posicao = 2; posicao >= 0; posicao--) {
                if (tabuleiro[posicao][2 - posicao] != j) {
                    fechou = false;
                }
            }
            if (fechou) {
                return j;
            }
        }
        
        boolean empate = true;
        for (int linha = 0; linha < 3; linha++) {
            for (int coluna = 0; coluna < 3; coluna++) {
                if (tabuleiro[coluna][linha] == 0) {
                    empate = false;
                }
            }
        }
        if (empate) {
            return 3;
        }
        return 0;
    }

    @Override
    public String toString() {
        String out = "";
        for (int linha = 0; linha < 3; linha++) {
            for (int coluna = 0; coluna < 3; coluna++) {
                switch (tabuleiro[coluna][linha]) {
                    case 0:
                        out += "_ ";
                        break;
                    case 1:
                        out += "O ";
                        break;
                    case 2:
                        out += "X ";
                        break;
                }
            }
            out += "\n";
        }
        return out;
    }

    public void executar() {
    	System.out.println("Jogo Iniciado!");
		System.out.println();
        while (vencedor() == 0) {
            System.out.println(this);
            System.out.println("Jogador: " + jogador);
            System.out.println();
            
            System.out.print("Coluna: ");
            int coluna = ent.nextInt();
            System.out.println();
            
            System.out.print("Linha: ");
            int linha = ent.nextInt();
            System.out.println();
            
            if (!jogar(coluna, linha)) {
                System.out.println("Ops... Jogada invalida, tente novamente...");
            }
        }
        System.out.println(this);
		System.out.println("E o vencedor foi o jogador: " + vencedor());
		System.out.println();
		System.out.println("Obrigado por jogar :)");
		ent.close();
    }

    public static void main(String[] args) {
    	System.out.print("Digite 1 para ser Jogador 1 ou 2 para ser Jogador 2: ");
    	jogador = ent.nextInt();
    	System.out.println();
    	
    	System.out.print("Digite 1 para jogar com O ou 2 para jogar com X: ");
        valor = ent.nextInt();
        System.out.println();
        
        JogoDaVelha jogo = new JogoDaVelha();
        jogo.executar();
    }
}