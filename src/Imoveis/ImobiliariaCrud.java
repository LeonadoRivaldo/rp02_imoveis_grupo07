/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Imoveis;

import Imoveis.apartamento.Apartamento;
import Imoveis.chacara.Chacara;
import Imoveis.salaComercial.SalaComercial;
import Imoveis.terreno.Terreno;
import java.io.BufferedReader;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author junio
 */
public class ImobiliariaCrud implements ListaImoveis {

    private List<Imovel> listaImoveis;
    private Tipo tipoImovel;

    public ImobiliariaCrud() {
        listaImoveis = new ArrayList<>();
    }

    public List<Imovel> getListaImoveis() {
        return listaImoveis;
    }

    public Tipo getTipoImovel() {
        return tipoImovel;
    }

    public void setTipoImovel(Tipo tipoImovel) {
        this.tipoImovel = tipoImovel;
    }

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
        this.escreverArquivo();
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
        Imovel imovel = this.consultar(codigo);
        if (imovel != null) {
            int indice = this.listaImoveis.indexOf(imovel);
            this.listaImoveis.set(indice, im);
            this.escreverArquivo();
            return true;
        }
        return false;
    }

    @Override
    public boolean excluir(int codigo) {
        Imovel imovel = this.consultar(codigo);
        if (imovel != null) {
            int indice = this.listaImoveis.indexOf(imovel);
            this.listaImoveis.remove(indice);
            this.escreverArquivo();
            return true;
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
        String dir = null;
        String fileName = "\\listaImoveis.csv";
        FileOutputStream outFile = null;
        BufferedWriter buff = null;
        String objProp = null;
        Imovel im;
        //abre o arquivo para escrita
        //A estrutura try-catch é usada pois o objeto BufferedWriter exige que as
        //excessões sejam tratadas
        try {

            if (tipoImovel.getTipo() == 1) {
                dir = dirName("apartamento");
                im = new Apartamento();
                objProp = objProp(im);
            } else if (tipoImovel.getTipo() == 2) {
                dir = dirName("chacara");
                im = new Chacara();
                objProp = objProp(im);
            } else if (tipoImovel.getTipo() == 3) {
                dir = dirName("sala_comercial");
                im = new SalaComercial();
                objProp = objProp(im);
            } else if (tipoImovel.getTipo() == 4) {
                dir = dirName("terreno");
                im = new Terreno();
                objProp = objProp(im);
            }

            File files = new File(dir);
            if (!files.exists()) {
                if (files.mkdirs()) {
                } else {
                    return false;
                }
            }
            outFile = new FileOutputStream(dir + fileName);
            //Criação de um buffer para a escrita em uma stream
            buff = new BufferedWriter(new FileWriter(dir + fileName));
            //escreve o número de contas na primeira linha do arquivo
            //buff.write(listaImoveis.size() + ",");
            //escreve as informações de cada conta
            buff.write("codigo;" + objProp);
            for (Imovel imovel : listaImoveis) {
                buff.newLine();
                buff.write(objToString(imovel));
                //escreve uma linha em branco entre uma conta e a seguinte
                buff.write("\n");
            }
            this.gravaCodigo(dir);
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
        FileInputStream inFile;
        BufferedReader buff;
        String linha, aux;
        Imovel imovel;
        int nroImoveis, codigoObj, nroBanheiros, nroSala, andar, numero, numeroDeQuartos, anoDeConstrucao, numeroDeVagasNaGaragem, numeroDoApartamento;
        String nomeEdificio, logradouro, bairro, cidade, descricao;
        double areaTotal, valor, valorCondominio, dimensaoFrente, dimensaoLado, distanciaCidade, areaConstruida;
        try {
            String dir = null;
            String objProp;
            Terreno t = null;
            Apartamento a = null;
            SalaComercial sc = null;
            Chacara ch = null;
            if (tipoImovel.getTipo() == 1) {
                dir = dirName("apartamento");
            } else if (tipoImovel.getTipo() == 2) {
                dir = dirName("chacara");
            } else if (tipoImovel.getTipo() == 3) {
                dir = dirName("sala_comercial");
            } else if (tipoImovel.getTipo() == 4) {
                dir = dirName("terreno");
            }

            inFile = new FileInputStream(new File(dir + "\\listaImoveis.csv"));
            buff = new BufferedReader(new InputStreamReader(inFile, "UTF-8"));
            String line;
            String[] objetoLinha;
            String att = buff.readLine();
            String[] atts = att.split(";");
            /*
             codigo
             Nome Edificio
             Logradouro
             Numero
             Bairro
             Cidade
             Descricao
             Area Total
             Valor
            
             Andar
             Valor Condominio
            
             Numero de quartos
             Ano de construcao
             Numero de vagas na garagem
             Numero do apartamento
             */

            while ((line = buff.readLine()) != null) {
                if (!line.equalsIgnoreCase("")) {
                    String[] conteudo = line.split(";");
                    //Atributos do imovel
                    codigoObj = Integer.parseInt(conteudo[0].trim());
                    logradouro = conteudo[2];
                    numero = Integer.parseInt(conteudo[3].trim());
                    bairro = conteudo[4];
                    cidade = conteudo[5];
                    descricao = conteudo[6];
                    areaTotal = Double.parseDouble(conteudo[7].trim().replace("m²", ""));
                    valor = Double.parseDouble(conteudo[8].trim().replace("R$", ""));
                    if (tipoImovel.getTipo() == 1) {
                        nomeEdificio = conteudo[1];
                        andar = Integer.parseInt(conteudo[9].trim());
                        valorCondominio = Double.parseDouble(conteudo[10].trim().replace("R$", ""));
                        numeroDeQuartos = Integer.parseInt(conteudo[11].trim());
                        anoDeConstrucao = Integer.parseInt(conteudo[12].trim());
                        numeroDeVagasNaGaragem = Integer.parseInt(conteudo[13].trim());
                        numeroDoApartamento = Integer.parseInt(conteudo[14].trim());
                        a = new Apartamento(codigoObj, numeroDeQuartos, anoDeConstrucao, numeroDeVagasNaGaragem, numeroDoApartamento, nomeEdificio, andar, valorCondominio, logradouro, numero, bairro, cidade, descricao, areaTotal, valor);
                        this.incluir(a);
                    } else if (tipoImovel.getTipo() == 2) {
                        areaConstruida = Double.parseDouble(conteudo[9].trim());
                        numeroDeQuartos = Integer.parseInt(conteudo[10].trim());
                        anoDeConstrucao = Integer.parseInt(conteudo[11].trim());
                        distanciaCidade = Double.parseDouble(conteudo[12].trim());
                        ch = new Chacara(codigoObj, logradouro, numero, bairro, cidade, descricao, areaTotal, valor, areaConstruida, numeroDeQuartos, anoDeConstrucao, distanciaCidade);
                        this.incluir(ch);
                    } else if (tipoImovel.getTipo() == 3) {
                        nomeEdificio = conteudo[1];
                        andar = Integer.parseInt(conteudo[9].trim());
                        valorCondominio = Double.parseDouble(conteudo[10].trim().replace("R$", ""));
                        nroBanheiros = Integer.parseInt(conteudo[11].trim());
                        nroSala = Integer.parseInt(conteudo[12].trim());
                        sc = new SalaComercial(codigoObj, nroSala, nroBanheiros, nomeEdificio, andar, valorCondominio, logradouro, numero, bairro, cidade, descricao, areaTotal, valor);
                        this.incluir(sc);
                    } else if (tipoImovel.getTipo() == 4) {
                        dimensaoFrente = Double.parseDouble(conteudo[9].trim());
                        dimensaoLado = Double.parseDouble(conteudo[10].trim());
                        t = new Terreno(codigoObj, dimensaoFrente, dimensaoLado, logradouro, numero, bairro, cidade, descricao, areaTotal, valor);
<<<<<<< HEAD
=======
                        this.incluir(t);
>>>>>>> b491587df3a1885d1380b052f8ebdb1a76c7fd17
                    }
                }
            }
        } catch (FileNotFoundException | UnsupportedEncodingException ex) {
            Logger.getLogger(ImobiliariaCrud.class
                    .getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ImobiliariaCrud.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    private String dirName(String tipo) {
        Properties p = System.getProperties();
        return p.getProperty("user.home") + "\\projetoRPII\\" + tipo;
    }

    private String objProp(Imovel im) {
        String prop = im.toString();
        String[] props = prop.split("\n");
        String propsaux = "";
        for (int x = 0; x < props.length; x++) {
            if (x != 0) {
                propsaux += props[x].split(":")[0];
            }
            if (x != props.length - 1 && x != 0) {
                propsaux += ";";
            }

        }
        return propsaux;
    }

    private String objToString(Imovel im) {
        String prop = im.toString();
        String[] props = prop.split("\n");
        String propsaux = "";
        propsaux += im.getCodigoObj() + ";";
        for (int x = 0; x < props.length; x++) {
            if (x != 0) {
                propsaux += props[x].split(":")[1].trim();
            }
            if (x != props.length - 1 && x != 0) {
                propsaux += ";";
            }

        }
        return propsaux;
    }

    private void gravaCodigo(String dir) throws FileNotFoundException, IOException {
        String fileName = "\\ultimoCod.csv";
        /*
         escreve o ultimo codigo em um arquivo para continuar a contagem ao
         reiniciar o programa
         */
        FileOutputStream outFileCont = new FileOutputStream(dir + fileName);
        BufferedWriter buffCont = new BufferedWriter(new FileWriter(dir + fileName));
        int i = Imovel.getCodigoClasse();
        buffCont.write(Integer.toString(i));
        outFileCont.close();
        buffCont.close();
    }

    private void setUltimoCodigo(String dir) throws FileNotFoundException, UnsupportedEncodingException, IOException {
        FileInputStream inFile = new FileInputStream(new File(dir + "\\ultimoCod.csv"));
        BufferedReader buff = new BufferedReader(new InputStreamReader(inFile, "UTF-8"));
        Imovel.setUltimoCodigo(Integer.parseInt(buff.readLine()));
        System.out.println("DEU ERRO! ARQUIVO DE CODIGO N ENCONTRADO");
    }

}
