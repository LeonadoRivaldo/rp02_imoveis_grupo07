/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cruds;

import imovel.Imovel;
import java.util.ArrayList;
import java.util.List;
import persistenciaDados.ObjectCrud;

/**
 *
 * @author junio
 */
public class ImobiliariaCrud implements ListaImoveis{
    private List<Imovel> listaImoveis = new ArrayList();
    private String tipoImovel;

    public List<Imovel> getListaImoveis() {
        return listaImoveis;
    }

    public String getTipoImovel() {
        return tipoImovel;
    }

    public void setTipoImovel(String tipoImovel) {
        this.tipoImovel = tipoImovel;
    }
    /**
     * metodo que inclui um objeto na lista geral de imoveis
     * @param imovel que foi criado na interface
     * @return True caso inclua com sucesso, falso caso contrário
     */
    @Override
    public boolean incluir(Imovel imovel) {
        listaImovel.add(imovel);
        ObjectCrud.saveObject(imovel, this.tipoImovel);
        return true;
    }
}
