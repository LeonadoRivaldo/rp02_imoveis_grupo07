/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tipo;

import imovel.Imovel;

/**
 *
 * @author Juliana Mareco
 */
public class Residencia extends Imovel{
    private int anoConstrucao;
    private int nroQuartos;
    private float areaConstruida;

    public Residencia(String logradouro, int numero, String bairro, String cidade, String descricao, double areaTotal, double valor) {
        super(logradouro, numero, bairro, cidade, descricao, areaTotal, valor);
    }

    /**
     * @return the anoConstrucao
     */
    public int getAnoConstrucao() {
        return anoConstrucao;
    }

    /**
     * @param anoConstrucao the anoConstrucao to set
     */
    public void setAnoConstrucao(int anoConstrucao) {
        this.anoConstrucao = anoConstrucao;
    }

    /**
     * @return the nroQuartos
     */
    public int getNroQuartos() {
        return nroQuartos;
    }

    /**
     * @param nroQuartos the nroQuartos to set
     */
    public void setNroQuartos(int nroQuartos) {
        this.nroQuartos = nroQuartos;
    }

    /**
     * @return the areaConstruida
     */
    public float getAreaConstruida() {
        return areaConstruida;
    }

    /**
     * @param areaConstruida the areaConstruida to set
     */
    public void setAreaConstruida(float areaConstruida) {
        this.areaConstruida = areaConstruida;
    }
    
    @Override
    public String toString() {
        return super.toString() + "Area Construida " + this.areaConstruida + "\nNúmero de Quartos: " + this.nroQuartos + "\nAno de Construção: " + this.anoConstrucao;
    }
    
}
