/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imovel;

/**
 *
 * @author leona_000
 */
public class Apartamento extends Predio {
    protected int numeroDeQuartos, anoDeConstrucao, numeroDeVagasNaGaragem, numeroDoApartamento;

    public Apartamento(int numeroDeQuartos, int anoDeConstrucao, int numeroDeVagasNaGaragem, int numeroDoApartamento, String nomeEdificio, int andar, double valorCondominio, String logradouro, int numero, String bairro, String cidade, String descricao, double areaTotal, double valor) {
        super(nomeEdificio, andar, valorCondominio, logradouro, numero, bairro, cidade, descricao, areaTotal, valor);
        this.numeroDeQuartos = numeroDeQuartos;
        this.anoDeConstrucao = anoDeConstrucao;
        this.numeroDeVagasNaGaragem = numeroDeVagasNaGaragem;
        this.numeroDoApartamento = numeroDoApartamento;
    }


    public int getNumeroDeQuartos() {
        return numeroDeQuartos;
    }

    public void setNumeroDeQuartos(int numeroDeQuartos) {
        this.numeroDeQuartos = numeroDeQuartos;
    }

    public int getAnoDeConstrucao() {
        return anoDeConstrucao;
    }

    public void setAnoDeConstrucao(int anoDeConstrucao) {
        this.anoDeConstrucao = anoDeConstrucao;
    }

    public int getNumeroDeVagasNaGaragem() {
        return numeroDeVagasNaGaragem;
    }

    public void setNumeroDeVagasNaGaragem(int numeroDeVagasNaGaragem) {
        this.numeroDeVagasNaGaragem = numeroDeVagasNaGaragem;
    }

    public int getNumeroDoApartamento() {
        return numeroDoApartamento;
    }

    public void setNumeroDoApartamento(int numeroDoApartamento) {
        this.numeroDoApartamento = numeroDoApartamento;
    }
    
    
    
    
}
