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
class ListaVaziaException extends RuntimeException {

    public ListaVaziaException() {
        super("A sua lista está vazia");
    }
    
}
