/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teste;

import java.util.Arrays;
import java.util.Objects;

/**
 *
 * @author DellVintage
 */
public class Teste {

    private Horta plantacao;
    private Robo bot;
    private String[][] area;

    public Teste() {
        this.plantacao = new Horta();
        this.bot = new Robo(plantacao.getComprimento(), plantacao.getLargura());
        this.area = new String[plantacao.getComprimento()][plantacao.getLargura()];
    }

    public void mostraInfo() {
        for (String[] linha : area) {
            for (int i = 0; i < linha.length; i++) {
                linha[i] = "| |";
            }
        }
        area[bot.getPosicaoX()][bot.getPosicaoY()] = "|R|";
        System.out.println("Posição Inicial: (" + (bot.getPosicaoX() + 1) + " ," + (bot.getPosicaoY() + 1) + ")");
        System.out.println("Direção inicial:" + direcao());

        System.out.print("Canteiros a irrigar: ");
        bot.getCanteiroirrigar().stream().forEach((ge) -> {
            System.out.print(Arrays.toString(ge));
            area[(ge[0] - 1)][(ge[1] - 1)] = "|i" + (bot.getCanteiroirrigar().lastIndexOf(ge) + 1) + "|";
        });
        System.out.println("\n");
        for (String[] linha : area) {
            System.out.println(Arrays.toString(linha));
        }
    }

    public String direcao() {
        switch (bot.getDirecao()) {
            case 1:
                return "Norte";
            case 2:
                return "Leste";
            case 3:
                return "Sul";
            default:
                return "Oeste";
        }
    }

    public void movimento() {
        String resultado = "Caminho:";
        Integer x = bot.getPosicaoX();
        Integer y = bot.getPosicaoY();
        Integer destinoX;
        Integer destinoY;
        for (Integer[] canteiro : bot.getCanteiroirrigar()) {
            destinoX = canteiro[1] - 1;
            destinoY = canteiro[0] - 1;
            if (destinoX > x) {
                switch (bot.getDirecao()) {
                    case 1:
                        resultado += " D ";
                        break;
                    case 2:
                        break;
                    case 3:
                        resultado += " E ";
                        break;
                    default:
                        resultado += " D D ";
                        break;
                }
                bot.setDirecao(2);

                while (!Objects.equals(destinoX, x)) {
                    resultado += " M ";
                    x++;
                }
            } else if (destinoX < x) {
                switch (bot.getDirecao()) {
                    case 1:
                        resultado += " E ";
                        break;
                    case 2:
                        resultado += " D D ";
                        break;
                    case 3:
                        resultado += " D ";
                        break;
                    default:
                        break;

                }
                bot.setDirecao(4);
                while (!Objects.equals(destinoX, x)) {
                    resultado += " M ";
                    x--;
                }
            }
            if (destinoY < y) {
                switch (bot.getDirecao()) {
                    case 1:
                        break;
                    case 2:
                        resultado += " E ";
                        break;
                    case 3:
                        resultado += " D D ";
                        break;
                    default:
                        resultado += " D ";
                        break;
                }
                bot.setDirecao(1);

                while (!Objects.equals(destinoY, y)) {
                    resultado += " M ";
                    y--;
                }
            } else if (destinoY > y) {
                switch (bot.getDirecao()) {
                    case 1:
                        resultado += " D D ";
                        break;
                    case 2:
                        resultado += " D ";
                        break;
                    case 3:
                        break;
                    default:
                        resultado += " E ";
                        break;

                }
                bot.setDirecao(3);
                while (!Objects.equals(destinoY, y)) {
                    resultado += " M ";
                    y++;
                }
                
            }
            resultado += " I ";
        }

        System.out.println(resultado);
        System.out.println("Direção final:" + direcao());

    }

    public static void main(String[] args) {
        Teste run = new Teste();
        run.mostraInfo();
        run.movimento();
    }
}
