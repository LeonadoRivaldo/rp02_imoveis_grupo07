/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Imoveis.chacara;

import Imoveis.Residencia;

/**
 *
 * @author Juliana Mareco
 */
public class Chacara extends Residencia {

    private double distanciaCidade;
    
    public Chacara(){
        super();
    }
    
    public Chacara(double distanciaCidade, int anoDeConstrucao, int numeroDeQuartos, double areaConstruida, String logradouro, int numero, String bairro, String cidade, String descricao, double areaTotal, double valor) {
        super(anoDeConstrucao, numeroDeQuartos, areaConstruida, logradouro, numero, bairro, cidade, descricao, areaTotal, valor);
        this.distanciaCidade = distanciaCidade;
    }

    public Chacara(int codigoObj, String logradouro, int numero, String bairro, String cidade, String descricao, double areaTotal, double valor, double areaConstruida, int numeroDeQuartos, int anoDeConstrucao, double distanciaCidade) {
        super(anoDeConstrucao, numeroDeQuartos, areaConstruida, logradouro, numero, bairro, cidade, descricao, areaTotal, valor);
        this.distanciaCidade = distanciaCidade;
    }

     
    

    /**
     * @return the distanciaCidade
     */
    public double getDistanciaCidade() {
        return distanciaCidade;
    }

    /**
     * @param distanciaCidade the distanciaCidade to set
     */
    public void setDistanciaCidade(double distanciaCidade) {
        this.distanciaCidade = distanciaCidade;
    }

    /**
     * Metodo que trasforma todos os atributos em string
     *
     * @return String com todos os atributos
     */
    @Override
    public String toString() {
        return super.toString() + "\nDistancia Cidade: " + this.distanciaCidade;
    }
}
