/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Imoveis;

import Imoveis.Imovel;
import Imoveis.salaComercial.SalaComercial;
import Imoveis.Predio;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author junio
 */
public class ImobiliariaCrud implements ListaImoveis {

    private List<Imovel> listaImoveis;
    //private String tipoImovel;

    public ImobiliariaCrud() {
        listaImoveis = new ArrayList<>();
    }

    public List<Imovel> getListaImoveis() {
        return listaImoveis;
    }

    /*public String getTipoImovel() {
        return tipoImovel;
    }

    public void setTipoImovel(String tipoImovel) {
        this.tipoImovel = tipoImovel;
    }*/
    /**
     * metodo que inclui um objeto na lista geral de imoveis
     *
     * @param imovel que foi criado na interface
     * @return True caso inclua com sucesso, falso caso contrário
     */
    @Override
    public boolean incluir(Imovel imovel) {
        for (Imovel i : listaImoveis) {
            if (imovel.getCodigoObj() == i.getCodigoObj()) {
                return false;
            }
        }
        listaImoveis.add(imovel);
        escreverArquivo();
        return true;
    }

    @Override
    public Imovel consultar(int codigo) {
        for (Imovel imovel : listaImoveis) {
            if (imovel.getCodigoObj() == codigo) {
                return imovel;
            }
        }
        return null;
    }

    @Override
    public boolean editar(int codigo, Imovel im) {
        for (Imovel imovel : this.listaImoveis) {
            if (imovel.getCodigoObj() == codigo) {
                int indice = this.listaImoveis.indexOf(imovel);
                this.listaImoveis.set(indice, im);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean excluir(int codigo) {
        for (Imovel imovel : this.listaImoveis) {
            if (imovel.getCodigoObj() == codigo) {
                int indice = this.listaImoveis.indexOf(imovel);
                this.listaImoveis.remove(indice);
                return true;
            }
        }
        return false;
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

        FileOutputStream outFile = null;
        BufferedWriter buff = null;
        //abre o arquivo para escrita
        //A estrutura try-catch é usada pois o objeto BufferedWriter exige que as
        //excessões sejam tratadas
        try {

            //Criação de um buffer para a escrita em uma stream
            BufferedWriter StrW = new BufferedWriter(new FileWriter("C:\\ListaImoveis.csv"));
            //escreve o número de contas na primeira linha do arquivo
            buff.write(listaImoveis.size() + ",");
            //escreve as informações de cada conta
            for (Imovel listaImoveis : listaImoveis) {
                //escreve o numero e saldo
                buff.write(listaImoveis.toString() + "\n");
            }
            // fecha o arquivo
            buff.close();
            outFile.close();
            return true;

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean lerArquivo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
