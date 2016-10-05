/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Imoveis.apartamento;

import static Imoveis.EntradasTeclado.*;
import Imoveis.InterfaceSistema;
import Imoveis.ImobiliariaCrud;

/**
 *
 * @author leona_000
 */
public class InterfaceApartamento extends InterfaceSistema {
    
    private ImobiliariaCrud listaApartamentos = new ImobiliariaCrud();
    private Apartamento ap = null;
    
    @Override
    public void principal(){
        int opcao = -1;
        do{
            System.out.println("##########################");
            System.out.println("1 - incluir");
            System.out.println("2 - exibir");
            System.out.println("0 - sair");
            opcao = inInt("Opcao: ");
            
            
        }while(opcao != 0);
        
        
    }

    @Override
    protected void criarImovel() {
        listaApartamentos.incluir(ap);
    }
    
    
    public static void main(String[] args) {
        InterfaceApartamento i = new InterfaceApartamento();
        i.principal();
    }
    
    
    
}
