/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teste;

import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author DellVintage
 */
public class Robo {

    private Integer posicaoX;
    private Integer posicaoY;
    private Integer direcao; // 1 = Norte, 2 = Leste,3 = Sul, 4 = Oeste   
    private ArrayList<Integer[]> canteiroIrrigar;
    private Integer maxX;
    private Integer maxY;

    public Robo(Integer posicaoX, Integer posicaoY, Integer direcao,Integer maxX, Integer maxY) {
        this.posicaoX = posicaoX;
        this.posicaoY = posicaoY;
        this.direcao = direcao;
        this.maxX = maxX;
        this.maxY = maxY;
    }

    public Robo(Integer maxX, Integer maxY) {
        this.maxX = maxX;
        this.maxY = maxY;
        this.posicaoX = verificaArea("Onde está o robo em relacão ao comprimento da horta?", "Posição", JOptionPane.QUESTION_MESSAGE, maxX) - 1;
        this.posicaoY = verificaArea("Onde está o robo em relacão a largura da horta?", "Posição", JOptionPane.QUESTION_MESSAGE, maxY) - 1;

        do {
            direcao = Integer.parseInt(verificaEntrada("O robo está virado para:\n"
                    + "1.Norte\n"
                    + "2.Leste\n"
                    + "3.Sul\n"
                    + "4.Oeste", "Direção", JOptionPane.QUESTION_MESSAGE));
            if (!(direcao > 0 && direcao < 5)) {
                direcao = 0;
                JOptionPane.showMessageDialog(null, "Por favor, insira uma opção válida", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } while (direcao == 0);

        this.canteiroIrrigar = new ArrayList<>();
        int canteiroBool = 0;
        do {
            Integer irrigar[] = new Integer[2];
            irrigar[0] = verificaArea("Onde está o canteiro que quer irrigar em relacão ao comprimento da horta?", "Canteiro", JOptionPane.QUESTION_MESSAGE, maxX);
            irrigar[1] = verificaArea("Onde está o canteiro que quer irrigar em relacão a largura da horta?", "Canteiro", JOptionPane.QUESTION_MESSAGE, maxY);
            canteiroIrrigar.add(irrigar);
            canteiroBool = JOptionPane.showConfirmDialog(null, "Deseja irrigar mais algum canteiro?", null, JOptionPane.YES_NO_OPTION);
        } while (canteiroBool == 0);
    }

    public Integer getPosicaoX() {
        return posicaoX;
    }

    public void setPosicaoX(Integer posicaoX) {
        this.posicaoX = posicaoX;
    }

    public Integer getPosicaoY() {
        return posicaoY;
    }

    public void setPosicaoY(Integer posicaoY) {
        this.posicaoY = posicaoY;
    }

    public Integer getDirecao() {
        return direcao;
    }

    public void setDirecao(Integer direcao) {
        this.direcao = direcao;
    }

    public ArrayList<Integer[]> getCanteiroirrigar() {
        return canteiroIrrigar;
    }

    public void setCanteiroirrigar(ArrayList<Integer[]> canteiroirrigar) {
        this.canteiroIrrigar = canteiroirrigar;
    }
    
    public String verificaEntrada(String descricao, String titulo , Integer tipoMensagem) {
        String input = "";
        do {
            input = JOptionPane.showInputDialog(null, descricao, titulo, tipoMensagem);
            if (!input.matches("^[0-9]*$")) {
                JOptionPane.showMessageDialog(null, "Por favor, digitar somente números.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } while (!input.matches("^[0-9]*$"));
        return input;
    }
    
    public Integer verificaArea(String descricao, String titulo , Integer tipoMensagem, Integer max){
        Integer input = 0;
        do {
            input = Integer.parseInt(verificaEntrada(descricao, titulo , tipoMensagem));
            if (input > max) {
                JOptionPane.showMessageDialog(null, "Por favor, digitar menor que a área estabelicada para a horta",
                        "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } while (input > max);
        
        return input;
    }
}
