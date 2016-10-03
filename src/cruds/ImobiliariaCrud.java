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
        listaImoveis.add(imovel);
        ObjectCrud.salvarObjeto(imovel, this.tipoImovel);
        return true;
    }

    @Override
    public Imovel consultar(int codigo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean editar(int codigo, Imovel im) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean excluir(int codigo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Imovel> ordenarCodigo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Imovel> ordenarValor() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Imovel> ordenarArea() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Imovel> pesquisaValor(double valor) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Imovel> pesquisaBairro(String bairro) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean escreverArquivo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean lerArquivo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
