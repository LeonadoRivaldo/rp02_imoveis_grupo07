/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Imoveis;

/**
 *
 * @author Juliana Mareco
 */
public abstract class Residencia extends Imovel {

    private int anoDeConstrucao;
    private int numeroDeQuartos;
    private double areaConstruida;

    public Residencia() {
        super();
    }

    public Residencia(int anoDeConstrucao, int numeroDeQuartos, double areaConstruida, String logradouro, int numero, String bairro, String cidade, String descricao, double areaTotal, double valor) {
        super(logradouro, numero, bairro, cidade, descricao, areaTotal, valor);
        this.anoDeConstrucao = anoDeConstrucao;
        this.numeroDeQuartos = numeroDeQuartos;
        this.areaConstruida = areaConstruida;
    }

    /**
     * @return the anoConstrucao
     */
    public int getAnoConstrucao() {
        return anoDeConstrucao;
    }

    /**
     * @param anoConstrucao the anoConstrucao to set
     */
    public void setAnoConstrucao(int anoDeConstrucao) {
        this.anoDeConstrucao = anoDeConstrucao;
    }

    /**
     * @return the nroQuartos
     */
    public int getNroQuartos() {
        return numeroDeQuartos;
    }

    /**
     * @param nroQuartos the nroQuartos to set
     */
    public void setNroQuartos(int numeroDeQuartos) {
        this.numeroDeQuartos = numeroDeQuartos;
    }

    /**
     * @return the areaConstruida
     */
    public double getAreaConstruida() {
        return areaConstruida;
    }

    /**
     * @param areaConstruida the areaConstruida to set
     */
    public void setAreaConstruida(double areaConstruida) {
        this.areaConstruida = areaConstruida;
    }

    @Override
    public String toString() {
        return super.toString() + "\nArea Construida: " + this.areaConstruida + "\nNúmero de Quartos: " + this.numeroDeQuartos + "\nAno de Construção: " + this.anoDeConstrucao;
    }

}
