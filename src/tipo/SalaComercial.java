/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tipo;

import imovel.Imovel;




/**
 *
 * @author Junior
 */
public class SalaComercial extends Imovel{
    private int nroBanheiros;
    private String nomeEdificio;
    private int andar;
    private int nroSala;
    private double valorCondominio;

    public SalaComercial(String logradouro, int numero, String bairro, String cidade, String descricao, double areaTotal, double valor) {
        super(logradouro, numero, bairro, cidade, descricao, areaTotal, valor);
    }

    

    public int getNroBanheiros() {
        return nroBanheiros;
    }

    public void setNroBanheiros(int nroBanheiros) {
        this.nroBanheiros = nroBanheiros;
    }

    public String getNomeEdificio() {
        return nomeEdificio;
    }

    public void setNomeEdificio(String nomeEdificio) {
        this.nomeEdificio = nomeEdificio;
    }

    public int getAndar() {
        return andar;
    }

    public void setAndar(int andar) {
        this.andar = andar;
    }

    public int getNroSala() {
        return nroSala;
    }

    public void setNroSala(int nroSala) {
        this.nroSala = nroSala;
    }

    public double getValorCondominio() {
        return valorCondominio;
    }

    public void setValorCondominio(double valorCondominio) {
        this.valorCondominio = valorCondominio;
    }
    
    
    
}
