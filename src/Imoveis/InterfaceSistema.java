/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Imoveis;

import static Imoveis.EntradasTeclado.div;
import static Imoveis.EntradasTeclado.inInt;
import java.util.List;

/**
 *
 * @author leona_000
 */
public abstract class InterfaceSistema {

    /**
     * metodo abstrato somente para definir um padrao nas interfaces.
     */
    protected abstract void criarImovel();

    /**
     * metodo abstrato somente para definir um padrao nas interfaces.
     */
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

    /**
     * Função para exibir uma lista com todos os imoveis na lista de imoveis que
     * deve ser passada pro parametro e retorna um inteiro com o codigo do
     * imovel
     *
     * @param lista o objeto da lista de cada interface, para cada sessao do
     * sistema
     * @return retorno é o codigo do imovel para que o imovel seja exibido
     */
    public int listaImoveis(ImobiliariaCrud lista) {
        List<Imovel> list = lista.getListaImoveis();
        int imovelCodig;
        div();
        for (Imovel imovel : list) {
            System.out.println(imovel.getCodigoObj() + " - Logradouro: " + imovel.getLogradouro() + " Valor:" + imovel.getValor());
        }
        div();
        return inInt("Digite o codigo do imovel: ");
    }

}
