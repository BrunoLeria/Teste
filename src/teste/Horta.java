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
public class Horta {

    private Integer comprimento;
    private Integer largura;
    private ArrayList<String[]> area;

    public Horta(Integer comprimento, Integer largura, Robo bot) {
        this.comprimento = comprimento;
        this.largura = largura;
    }

    public Horta() {
        // Pede e converte para integer o tamanho do comprimento e largura, certificando que só há números.
        this.comprimento = Integer.parseInt(verificaEntrada("Qual é o tamanho do comprimento da horta?", "Comprimento")); 
        this.largura = Integer.parseInt(verificaEntrada("Qual é o tamanho da largura da horta?", "Largura"));
    }

    public Integer getComprimento() {
        return comprimento;
    }

    public void setComprimento(Integer comprimento) {
        this.comprimento = comprimento;
    }

    public Integer getLargura() {
        return largura;
    }

    public void setLargura(Integer largura) {
        this.largura = largura;
    }

    public String verificaEntrada(String descricao, String titulo) {
        // Verifica se o que foi digitado na entrada do JOpationpane é um número
        // atravez de regex
        String input = "";
        do {
            input = JOptionPane.showInputDialog(null, descricao, titulo, JOptionPane.QUESTION_MESSAGE);
            if (!input.matches("^[0-9]*$")) {
                JOptionPane.showMessageDialog(null, "Por favor, digitar somente números.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } while (!input.matches("^[0-9]*$"));
        return input;
    }

}
