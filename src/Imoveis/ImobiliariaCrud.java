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
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
    private int countcodTrys = 0;

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

    //<editor-fold defaultstate="collapsed" desc="Incluir, Consultar, Editar, Excluir">
    //<editor-fold defaultstate="collapsed" desc="Incluir">
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
        return true;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Consultar">
    @Override
    public Imovel consultar(int codigo) {
        for (Imovel imovel : listaImoveis) {
            if (imovel.getCodigoObj() == codigo) {
                return imovel;
            }
        }
        return null;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Editar">    
    @Override
    public boolean editar(int codigo, Imovel im) {
        Imovel imovel = this.consultar(codigo);
        if (imovel != null) {
            int indice = this.listaImoveis.indexOf(imovel);
            this.listaImoveis.set(indice, im);
            if (!this.escreverArquivo()) {
                return false;
            }
            return true;
        }
        return false;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Excluir">
    @Override
    public boolean excluir(int codigo) {
        Imovel imovel = this.consultar(codigo);
        if (imovel != null) {
            int indice = this.listaImoveis.indexOf(imovel);
            this.listaImoveis.remove(indice);
            if (!this.escreverArquivo()) {
                return false;
            }
            return true;
        }
        return false;
    }
//</editor-fold>
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Ordenação">
    //<editor-fold defaultstate="collapsed" desc="Por codigo">
    @Override
    public List<Imovel> ordenarCodigo() {
        List<Imovel> aux = new ArrayList<>();
        aux.addAll(this.listaImoveis);
        for (int i = 0; i < aux.size(); i++) {
            for (int j = 0; j < aux.size() - 1; j++) {
                if (aux.get(j).getCodigoObj() > aux.get(j + 1).getCodigoObj()) {
                    Imovel a = aux.get(j);
                    aux.set(j, aux.get(j + 1));
                    aux.set(j + 1, a);
                }
            }
        }
        return aux;

    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Por valor">
    @Override
    public List<Imovel> ordenarValor() {
        List<Imovel> aux = new ArrayList<>();
        aux.addAll(this.listaImoveis);
        for (int i = 0; i < aux.size(); i++) {
            Imovel imovel = aux.get(i);
            for (int j = i - 1; j >= 0 && aux.get(j).getValor() > imovel.getValor(); j--) {
                aux.set(j + 1, aux.get(j));
                aux.set(j, imovel);
            }
        }
        return aux;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Area total">
    @Override
    public List<Imovel> ordenarArea() {
        for (int i = 0; i < listaImoveis.size(); i++) {
            int menor = i;
            for (int j = i + 1; j < listaImoveis.size(); j++) {
                if (listaImoveis.get(j).areaTotal < listaImoveis.get(menor).areaTotal) {
                    menor = j;
                }
            }
            Imovel aux = listaImoveis.get(i);
            listaImoveis.set(i, listaImoveis.get(menor));
            listaImoveis.set(menor, aux);

        }
        return listaImoveis;
    }
    //</editor-fold>
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Pesquisa">
    //<editor-fold defaultstate="collapsed" desc="Por valor">
    /*
     @Override
     public List<Imovel> pesquisaValor(double valor) {
     List<Imovel> aux = this.ordenarValor();
     int m, e = 0;
     int d = this.listaImoveis.size();
     while (e <= d) {
     m = (e + d) / 2;
     if (this.listaImoveis.get(m).getValor() == valor) {
     aux.add(this.listaImoveis.get(m));
     } else if (this.listaImoveis.get(m).getValor() < valor) {
     e = m + 1;
     } else {
     d = m - 1;
     }
     }
     if (aux.size() < 0) {
     return aux;
     }
     return null;
     }
     */
    @Override
    public List<Imovel> pesquisaValor(double valor) {
        List<Imovel> aux = new ArrayList();
        for (int i = 0; i < this.listaImoveis.size(); i++) {
            if (this.listaImoveis.get(i).getValor() <= valor) {
                aux.add(this.listaImoveis.get(i));
            }
        }
        if (aux.size() > 0) {
            return aux;
        }
        return null;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Por bairro">
    @Override
    public List<Imovel> pesquisaBairro(String bairro) {
        List<Imovel> aux = new ArrayList<>();
        for (Imovel im : this.listaImoveis) {
            if (im.getBairro().equalsIgnoreCase(bairro)) {
                aux.add(im);
            }
        }
        return aux;
    }
    //</editor-fold>
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Escrever arquivo">
    /**
     * metodo que escreve dois arquivos, um arquivo .csv com a lista de imoveis
     * em formato de texto e um outro arquivo para o sistema que vai conter o
     * objeto List listaImoveis para facilitar a montagem da lista ao iniciar o
     * programa
     *
     * @return true caso consiga, false caso não encontre o arquivo ou algum
     * erro aconteça
     */
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
            // fecha o arquivo
            buff.close();
            outFile.close();

            //serializando a lista!
            try {
                this.escreveArquivoBinario(dir);
            } catch (Exception e) {
                e.getMessage();
            }
            this.gravaDataCriacao(dir);
            this.gravaCodigo(dir);
            return true;

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }

    private void escreveArquivoBinario(String dir) throws FileNotFoundException, IOException {
        String fileNameSe = "\\listaImoveis";
        FileOutputStream outFileSe = new FileOutputStream(dir + fileNameSe);
        ObjectOutputStream out = new ObjectOutputStream(outFileSe);
        out.writeObject(this.listaImoveis);
        outFileSe.close();
        out.close();
    }

    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Ler arquivo">
    /**
     * metodo que lê o arquivo listaImoveis que contem o objeto List
     * listaImoveis e monta a lista, o arquivo que vai ser lido depende do tipo
     * de imovel da lista
     *
     * @return
     */
    public boolean lerArquivo() {
        try {
            String dir = null;
            if (tipoImovel.getTipo() == 1) {
                dir = dirName("apartamento");
            } else if (tipoImovel.getTipo() == 2) {
                dir = dirName("chacara");
            } else if (tipoImovel.getTipo() == 3) {
                dir = dirName("sala_comercial");
            } else if (tipoImovel.getTipo() == 4) {
                dir = dirName("terreno");
            }
            File files = new File(dir + "\\listaImoveis");
            if (files.exists() && FileLastModifiedDate.lastModifiedIsEqual(dir)) {
                System.out.println(FileLastModifiedDate.lastModifiedIsEqual(dir));
                FileInputStream fileIn = new FileInputStream(dir + "\\listaImoveis");
                ObjectInputStream in = new ObjectInputStream(fileIn);
                this.listaImoveis = (List<Imovel>) in.readObject();
                in.close();
                fileIn.close();
                return true;
            } else {
                if (this.lerArquivoCsv()) {
                    this.escreverArquivo();
                    return true;
                }
            }
        } catch (IOException i) {
            i.printStackTrace();
            return false;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ImobiliariaCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Funçoes auxiliares">
    //<editor-fold defaultstate="collapsed" desc="cria string com o caminho baseado no tipo da lista">
    /**
     * Recebendo o dipo da lista por parametro o metodo retorna uma string com o
     * diretorio que deve ser usa para separar os arquivos do sistema
     *
     * @param tipo string determinada pelo tipo de imovel
     * @return
     */
    public String dirName(String tipo) {
        Properties p = System.getProperties();
        return p.getProperty("user.home") + "\\projetoRPII\\" + tipo;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Cria String das propriedades do objeto">
    /**
     * Recebe um imovel por parametro e retorna uma string separada por ";" com
     * os nomes dos atributos desse imovel
     *
     * @param im imovel
     * @return string separada por ; com os atributos do imovel
     */
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
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="cria string com os valores do objeto">
    /**
     * Recebe um imovel por parametro e retorna uma string separada por ";" com
     * os valores dos atributos desse imovel
     *
     * @param im imovel
     * @return string separada por ; com os valores dos atributos do imovel
     */
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
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="grava um arquivo com o ultimo codigo de imovel da lista">
    /**
     * Cria um arquivo com o ultimo codigo de imovel dependendo do tipo de
     * imovel
     *
     * @param dir caminho diretorio aonde o arquivo com o valor deve ser criado
     * @throws FileNotFoundException
     * @throws IOException
     */
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
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="grava data do csv">
    private void gravaDataCriacao(String dir) throws FileNotFoundException, IOException {
        String fileName = "\\datacriacao.csv";
        /*
         escreve o ultimo codigo em um arquivo para continuar a contagem ao
         reiniciar o programa
         */
        File file = new File(dir + "\\listaImoveis.csv");
        FileOutputStream outFileCont = new FileOutputStream(dir + fileName);
        BufferedWriter buffCont = new BufferedWriter(new FileWriter(dir + fileName));
        int i = Imovel.getCodigoClasse();
        buffCont.write(Long.toString(file.lastModified()));
        outFileCont.close();
        buffCont.close();
    }

//</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="lê e seta o ultimo codigo de imovel da lista dentro da classe imovel">
    /**
     * lê um arquivo com o ultimo codigo de imovel dependendo do tipo de imovel
     * e seta o ultimo codigo dentro da classe imovel
     *
     * @param dir caminho diretorio aonde o arquivo com o valor deve ser lido
     * @throws FileNotFoundException
     * @throws IOException
     */
    private void setUltimoCodigo(String dir) throws FileNotFoundException, UnsupportedEncodingException, IOException {
        File file = new File(dir + "\\ultimoCod.csv");
        if (file.exists()) {
            FileInputStream inFile = new FileInputStream(new File(dir + "\\ultimoCod.csv"));
            BufferedReader buff = new BufferedReader(new InputStreamReader(inFile, "UTF-8"));
            int cod = Integer.parseInt(buff.readLine());
            buff.close();
            Imovel.setUltimoCodigo(cod);
        }
    }

    /**
     * metodo de acesso para o metodo setUltimoCodigo
     */
    public void setLastCod() {
        try {
            if (tipoImovel.getTipo() == 1) {
                this.setUltimoCodigo(this.dirName("apartamento"));
            } else if (tipoImovel.getTipo() == 2) {
                this.setUltimoCodigo(this.dirName("chacara"));
            } else if (tipoImovel.getTipo() == 3) {
                this.setUltimoCodigo(this.dirName("sala_comercial"));
            } else if (tipoImovel.getTipo() == 4) {
                this.setUltimoCodigo(this.dirName("terreno"));
            }
        } catch (Exception e) {
            if (countcodTrys < 10) {
                countcodTrys++;
                this.setLastCod();
            }
        }
    }
    //</editor-fold>
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Ler Arquivo CSV">
    public boolean lerArquivoCsv() {
        FileInputStream inFile;
        BufferedReader buff;
        String linha, aux;
        Imovel imovel;
        int nroImoveis, codigoObj, nroBanheiros, nroSala, andar, numero = 0, numeroDeQuartos, anoDeConstrucao, numeroDeVagasNaGaragem, numeroDoApartamento;
        String nomeEdificio, logradouro = null, bairro = null, cidade = null, descricao = null;
        double areaTotal = 0, valor = 0, valorCondominio, dimensaoFrente, dimensaoLado, distanciaCidade, areaConstruida;
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
            File files = new File(dir + "\\listaImoveis.csv");
            if (files.exists()) {
                inFile = new FileInputStream(new File(dir + "\\listaImoveis.csv"));
                buff = new BufferedReader(new InputStreamReader(inFile, "UTF-8"));
                String line;
                String[] objetoLinha;
                String att = buff.readLine();
                String[] atts = att.split(";");
                while ((line = buff.readLine()) != null) {
                    if (!line.equalsIgnoreCase("")) {
                        String[] conteudo = line.split(";");
                        //Atributos do imovel
                        codigoObj = Integer.parseInt(conteudo[0].trim());
                        if (tipoImovel.getTipo() == 1) {
                            nomeEdificio = conteudo[1];
                            logradouro = conteudo[2];
                            numero = Integer.parseInt(conteudo[3].trim());
                            bairro = conteudo[4];
                            cidade = conteudo[5];
                            descricao = conteudo[6];
                            areaTotal = Double.parseDouble(conteudo[7].trim().replace("m²", ""));
                            valor = Double.parseDouble(conteudo[8].trim().replace("R$", ""));
                            andar = Integer.parseInt(conteudo[9].trim());
                            valorCondominio = Double.parseDouble(conteudo[10].trim().replace("R$", ""));
                            numeroDeQuartos = Integer.parseInt(conteudo[11].trim());
                            anoDeConstrucao = Integer.parseInt(conteudo[12].trim());
                            numeroDeVagasNaGaragem = Integer.parseInt(conteudo[13].trim());
                            numeroDoApartamento = Integer.parseInt(conteudo[14].trim());
                            a = new Apartamento(codigoObj, numeroDeQuartos, anoDeConstrucao, numeroDeVagasNaGaragem, numeroDoApartamento, nomeEdificio, andar, valorCondominio, logradouro, numero, bairro, cidade, descricao, areaTotal, valor);
                            this.incluir(a);
                        } else if (tipoImovel.getTipo() == 2) {
                            logradouro = conteudo[1];
                            numero = Integer.parseInt(conteudo[2].trim());
                            bairro = conteudo[3];
                            cidade = conteudo[4];
                            descricao = conteudo[5];
                            areaTotal = Double.parseDouble(conteudo[6].trim().replace("m²", ""));
                            valor = Double.parseDouble(conteudo[7].trim().replace("R$", ""));
                            areaConstruida = Double.parseDouble(conteudo[8].trim());
                            numeroDeQuartos = Integer.parseInt(conteudo[9].trim());
                            anoDeConstrucao = Integer.parseInt(conteudo[10].trim());
                            distanciaCidade = Double.parseDouble(conteudo[11].trim());
                            ch = new Chacara(codigoObj, logradouro, numero, bairro, cidade, descricao, areaTotal, valor, areaConstruida, numeroDeQuartos, anoDeConstrucao, distanciaCidade);
                            this.incluir(ch);
                        } else if (tipoImovel.getTipo() == 3) {
                            nomeEdificio = conteudo[1];
                            logradouro = conteudo[2];
                            numero = Integer.parseInt(conteudo[3].trim());
                            bairro = conteudo[4];
                            cidade = conteudo[5];
                            descricao = conteudo[6];
                            areaTotal = Double.parseDouble(conteudo[7].trim().replace("m²", ""));
                            valor = Double.parseDouble(conteudo[8].trim().replace("R$", ""));
                            andar = Integer.parseInt(conteudo[9].trim());
                            valorCondominio = Double.parseDouble(conteudo[10].trim().replace("R$", ""));
                            nroBanheiros = Integer.parseInt(conteudo[11].trim());
                            nroSala = Integer.parseInt(conteudo[12].trim());
                            sc = new SalaComercial(codigoObj, nroSala, nroBanheiros, nomeEdificio, andar, valorCondominio, logradouro, numero, bairro, cidade, descricao, areaTotal, valor);
                            this.incluir(sc);
                        } else if (tipoImovel.getTipo() == 4) {
                            logradouro = conteudo[1];
                            numero = Integer.parseInt(conteudo[2].trim());
                            bairro = conteudo[3];
                            cidade = conteudo[4];
                            descricao = conteudo[5];
                            areaTotal = Double.parseDouble(conteudo[6].trim().replace("m²", ""));
                            valor = Double.parseDouble(conteudo[7].trim().replace("R$", ""));
                            dimensaoFrente = Double.parseDouble(conteudo[8].trim());
                            dimensaoLado = Double.parseDouble(conteudo[9].trim());
                            t = new Terreno(codigoObj, dimensaoFrente, dimensaoLado, logradouro, numero, bairro, cidade, descricao, areaTotal, valor);
                            this.incluir(t);
                        }
                    }
                }
                return true;
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
//</editor-fold>
}
