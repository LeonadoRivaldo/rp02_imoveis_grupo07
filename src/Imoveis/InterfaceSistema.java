/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Imoveis;

/**
 *
 * @author leona_000
 */
public abstract class InterfaceSistema {

    protected abstract void criarImovel();

    public abstract void principal();

    /**
     * função para exibir mensagens no sistema
     *
     * @param mensagem mensagem que vai ser exibida para o usuario
     */
    protected void exibeMensagem(String mensagem) {
        System.out.println("\n===========================================================");
        System.out.println(mensagem.toUpperCase());
        System.out.println("===========================================================\n");
    }
}
