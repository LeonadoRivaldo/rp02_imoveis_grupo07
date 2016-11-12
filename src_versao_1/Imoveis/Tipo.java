/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Imoveis;

/**
 * 
 * @author junio
 */
public enum Tipo {
    APARTAMENTO(1),
    CHACARA(2),
    SALACOMERCIAL(3),
    TERRENO(4);

    private final int tipo;

    /**
     *
     * @param tipo
     */
    private Tipo(int tipo) {
        this.tipo = tipo;
    }

    /**
     *
     * @return Tipo
     */
    public int getTipo() {
        return tipo;
    }

    /**
     *
     * @param tipo
     * @return
     */
    public static Tipo verificarTipo(int tipo) {
        if (Tipo.verificarTipo(APARTAMENTO.getTipo()).equals(tipo)) {
            return Tipo.APARTAMENTO;
        } else if (Tipo.verificarTipo(CHACARA.getTipo()).equals(tipo)) {
            return Tipo.CHACARA;
        } else if (Tipo.verificarTipo(SALACOMERCIAL.getTipo()).equals(tipo)) {
            return Tipo.SALACOMERCIAL;
        } else if (Tipo.verificarTipo(TERRENO.getTipo()).equals(tipo)) {
            return Tipo.TERRENO;
        } else {
            return null;
        }
    }
}
