/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Imoveis;

import static Imoveis.EntradasTeclado.inInt;
import Imoveis.apartamento.InterfaceApartamento;
import Imoveis.chacara.InterfaceChacara;
import Imoveis.salaComercial.InterfaceSalaComercial;
import Imoveis.terreno.InterfaceTerreno;

/**
 *
 * @author junio
 */
public class Main {

    public static void main(String[] args) {
        InterfaceApartamento iApartamento = new InterfaceApartamento();
        InterfaceTerreno iTerreno = new InterfaceTerreno();
        InterfaceChacara iChacara = new InterfaceChacara();
        InterfaceSalaComercial iSalaComercial = new InterfaceSalaComercial();
        int op;

        do {
            System.out.println("##################################################");
            System.out.println("1 - Terreno");
            System.out.println("2 - Apartamento");
            System.out.println("3 - Sala comercial");
            System.out.println("4 - Chacara");
            System.out.println("0 - sair");
            System.out.println("---------------------------------------------------");
            op = inInt("Opção: ");

            switch (op) {
                case 1:
                    iTerreno.principal();
                    break;
                case 2:
                    iApartamento.principal();
                    break;
                case 3:
                    iSalaComercial.principal();
                    break;
                case 4:
                    iChacara.principal();
                    break;
                default:
                    throw new AssertionError();
            }

        } while (op != 0);

    }
}
