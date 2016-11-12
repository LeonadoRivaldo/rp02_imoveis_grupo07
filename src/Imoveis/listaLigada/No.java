/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Imoveis.listaLigada;

/**
 *
 * @author leona_000
 */
public class No<E> {

    private int Indice;
    private E imovel;
    private No proximo;
    private No anterior;

    public No(int Indice, E imovel, No proximo, No anterior) {
        this.Indice = Indice;
        this.imovel = imovel;
        this.proximo = proximo;
        this.anterior = anterior;
    }

    public int getIndice() {
        return Indice;
    }

    public void setIndice(int Indice) {
        this.Indice = Indice;
    }

    public E getImovel() {
        return imovel;
    }

    public void setImovel(E imovel) {
        this.imovel = imovel;
    }

    public No getProximo() {
        return proximo;
    }

    public void setProximo(No proximo) {
        this.proximo = proximo;
    }

    public No getAnterior() {
        return anterior;
    }

    public void setAnterior(No anterior) {
        this.anterior = anterior;
    }
    
    

}
