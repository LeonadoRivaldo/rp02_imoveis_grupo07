/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imovel;

/**
 *
 * @author Juliana Mareco
 */
public class Chacara extends Residencia{
    private double distanciaCidade;

    public Chacara(double distanciaCidade, int anoConstrucao, int nroQuartos, float areaConstruida, String logradouro, int numero, String bairro, String cidade, String descricao, double areaTotal, double valor) {
        super(anoConstrucao, nroQuartos, areaConstruida, logradouro, numero, bairro, cidade, descricao, areaTotal, valor);
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
     * @return String com todos os atributos
     */
    @Override
    public String toString() {
        return super.toString() + "\nDistancia Cidade: " + this.distanciaCidade;
    }
}
