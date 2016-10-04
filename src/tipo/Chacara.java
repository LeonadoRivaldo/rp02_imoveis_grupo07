/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tipo;

/**
 *
 * @author Juliana Mareco
 */
public class Chacara extends Residencia{
    private double distanciaCidade;

    public Chacara(String logradouro, int numero, String bairro, String cidade, String descricao, double areaTotal, double valor) {
        super(logradouro, numero, bairro, cidade, descricao, areaTotal, valor);
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
