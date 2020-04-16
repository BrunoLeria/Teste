/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teste;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

/**
 *
 * @author DellVintage
 */
public class Teste {

    private Horta plantacao;
    private Robo bot;
    private ArrayList<String[]> area;
    private String[] linhaAuxiliar;

    public Teste() {
        this.plantacao = new Horta();
        this.bot = new Robo(plantacao.getComprimento() - 1, plantacao.getLargura() - 1);
        this.area = new ArrayList<>();
        for (int i = 0; i < plantacao.getLargura(); i++) {      //Adiciona o número de linhas com as colunas dentro da matriz
            area.add(new String[plantacao.getComprimento()]);
        }
    }

    public void mostraInfo() {
        area.stream().forEach((linha) -> {
            //Desenha as linhas e o robô na sua posição inicial.
            for (int i = 0; i < linha.length; i++) {
                if(Arrays.equals(linha, area.get(bot.getPosicaoY()))&& i == bot.getPosicaoX())
                    linha[i] = "|R|";
                else
                    linha[i] = "| |";
                    
            }
        });
        System.out.println("Posição Inicial: (" + bot.getPosicaoX() + " ," + bot.getPosicaoY() + ")");
        System.out.println("Direção inicial:" + direcao());

        System.out.print("Canteiros a irrigar: ");
        //Desenha na matriz onde está os lugares que o robo de irrigar
        bot.getCanteiroirrigar().stream().forEach((ge) -> {     
            System.out.print(Arrays.toString(ge));
            linhaAuxiliar = area.get(ge[1]);
            linhaAuxiliar[ge[0]] = "|i" + (bot.getCanteiroirrigar().lastIndexOf(ge) + 1) + "|";
            area.remove(linhaAuxiliar);
            area.add(ge[1], linhaAuxiliar);
        });
        System.out.println("");
        
        for (int i = (area.size() - 1); i >= 0; i--) { // Imprime a martriz
            System.out.println(i + Arrays.toString(area.get(i)));
        }
        for (int i = 0; i <= (plantacao.getComprimento() - 1); i++) { // Imprime a martriz
            System.out.print("   " + i + " ");
        }
        System.out.println("");
    }

    public String direcao() {           //Lista para converter um número em uma posição 
        switch (bot.getDirecao()) {     //Usado na de dizer para onde o robô está virado no início e no final
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
        //Criação do registro do movimento do robô
        String resultado = "Caminho:";
        Integer x = bot.getPosicaoX();
        Integer y = bot.getPosicaoY();
        Integer destinoX;
        Integer destinoY;
        for (Integer[] canteiro : bot.getCanteiroirrigar()) {
            destinoX = canteiro[0];
            destinoY = canteiro[1];
            if (destinoX > x) {
                switch (bot.getDirecao()) { //Vira para Leste e soma a posição x do robô
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
                switch (bot.getDirecao()) { //Vira para Oeste e diminui a posição x do robô
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
            if (destinoY > y) {             //Vira para Norte e aumanta a posição y do robô
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
                    y++;
                }
            } else if (destinoY < y) {          //Vira para Sul e diminui a posição y do robô
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
                    y--;
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
