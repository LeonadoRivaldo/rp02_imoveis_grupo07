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

    
    
    public abstract class Imovel {
        protected static int codigoClasse = 0001;
        protected int codigoObj;
        protected String logradouro;
        protected int numero;
        protected String bairro;
        protected String cidade;
        protected String descricao;
        protected double areaTotal;
        protected double valor;
        
        public Imovel(){
        }

        public Imovel(String logradouro, int numero, String bairro, String cidade, String descricao, double areaTotal, double valor) {
            codigoObj = codigoClasse;
            codigoClasse++;
            this.logradouro = logradouro;
            this.numero = numero;
            this.bairro = bairro;
            this.cidade = cidade;
            this.descricao = descricao;
            this.areaTotal = areaTotal;
            this.valor = valor;
        }

        public double getValor() {
            return valor;
        }

        public int getCodigoObj() {
            return codigoObj;
        }

        public void setValor(double valor) {
            this.valor = valor;
        }

        public String getLogradouro() {
            return logradouro;
        }

        public void setLogradouro(String logradouro) {
            this.logradouro = logradouro;
        }

        public int getNumero() {
            return numero;
        }

        public void setNumero(int numero) {
            this.numero = numero;
        }

        public String getBairro() {
            return bairro;
        }

        public void setBairro(String bairro) {
            this.bairro = bairro;
        }

        public String getCidade() {
            return cidade;
        }

        public void setCidade(String cidade) {
            this.cidade = cidade;
        }

        public String getDescricao() {
            return descricao;
        }

        public void setDescricao(String descricao) {
            this.descricao = descricao;
        }

        public double getAreaTotal() {
            return areaTotal;
        }

        public void setAreaTotal(double areaTotal) {
            this.areaTotal = areaTotal;
        }

        @Override
        public String toString() {
            return "Imoveis{" + "codigo=" + codigoObj + ", logradouro=" + logradouro + ", numero=" + numero + ", bairro=" + bairro + ", cidade=" + cidade + ", descricao=" + descricao + ", areaTotal=" + areaTotal + ", valor=" + valor + "}";
        }
        
    }


    
    

