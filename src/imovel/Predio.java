/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imovel;

import imovel.Imovel;

/**
 *
 * @author junio
 */
public class Predio extends Imovel{
    protected String nomeEdificio;
    protected int andar;    
    protected double valorCondominio;    

    public Predio(String nomeEdificio, int andar, double valorCondominio, String logradouro, int numero, String bairro, String cidade, String descricao, double areaTotal, double valor) {
        super(logradouro, numero, bairro, cidade, descricao, areaTotal, valor);
        this.nomeEdificio = nomeEdificio;
        this.andar = andar;
        this.valorCondominio = valorCondominio;
    }

    /**
     * @return the nomeEdificio
     */
    public String getNomeEdificio() {
        return nomeEdificio;
    }

    /**
     * @param nomeEdificio the nomeEdificio to set
     */
    public void setNomeEdificio(String nomeEdificio) {
        this.nomeEdificio = nomeEdificio;
    }

    /**
     * @return the andar
     */
    public int getAndar() {
        return andar;
    }

    /**
     * @param andar the andar to set
     */
    public void setAndar(int andar) {
        this.andar = andar;
    }

    /**
     * @return the valorCondominio
     */
    public double getValorCondominio() {
        return valorCondominio;
    }

    /**
     * @param valorCondominio the valorCondominio to set
     */
    public void setValorCondominio(double valorCondominio) {
        this.valorCondominio = valorCondominio;
    }
    
    
}
