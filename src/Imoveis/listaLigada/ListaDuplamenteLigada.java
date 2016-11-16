/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Imoveis.listaLigada;

import Imoveis.Imovel;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 *
 * @author leona_000
 */
public class ListaDuplamenteLigada<E> implements List {

    private No<E> inicio, fim, atual, aux;
    private int indice = 0, size = 0;

    public ListaDuplamenteLigada() {
        this.inicio = null;
        this.fim = null;
        this.atual = null;
    }

    //<editor-fold defaultstate="collapsed" desc="Aluno 1">
    @Override
    public boolean add(Object e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean remove(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Aluno 2">
    //<editor-fold defaultstate="collapsed" desc="Adicionar em uma posição especifica">
    @Override
    public void add(int i, Object im) {
        E imovel = (E) im;
        if (!this.isEmpty()) {
            No<E> aux = this.inicio;
            while (aux != this.fim) {
                if (aux.getIndice() == i) {
                    break;
                } else {
                    aux = aux.getProximo();
                    if (aux.getIndice() == this.inicio.getIndice()) {
                        break;
                    }
                }
            }
            No<E> auxAnterior = aux.getAnterior();
            No<E> NovoNo = new No(i, imovel, aux, auxAnterior);
            this.indice++;
            this.size++;
            this.incrementarLista(aux);
        } else {
            throw new ListaVaziaException();
        }

    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Remove de uma posição especifica">
    /**
     * Navega na lista até encontrar a posição do imovel que vai ser removido
     *
     * @param i indice do imovel dentro da lista para remoção
     * @return
     */
    @Override
    public Object remove(int i) {
        if (!this.isEmpty()) {
            if (this.size == 1) {
                this.clear();
                return this.inicio;
            } else {
                No<E> aux = this.inicio;
                while (aux.getIndice() != i) {
                    aux = aux.getProximo();
                }
                No<E> anterior = aux.getAnterior();
                No<E> proximo = aux.getProximo();
                anterior.setProximo(proximo);
                proximo.setAnterior(anterior);
                this.indice--;
                this.size--;
                this.decrementarLista(proximo);
                return aux.getImovel();
            }
        } else {
            throw new ListaVaziaException();
        }
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Pegar imovel pela posição da lista">
    /**
     * Recebe um indice da lista e deve retornar um imovel;
     *
     * @param i
     * @return retorna um imovel pelo indice do imovel NA LISTA, não pelo
     * codigo;
     */
    @Override
    public Object get(int i) {
        if (!this.isEmpty()) {
            No<E> aux = this.inicio;
            while (aux.getIndice() != i) {
                aux = aux.getProximo();
            }
            return aux.getImovel();
        } else {
            throw new ListaVaziaException();
        }
    }

    //</editor-fold>
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Aluno 3">
    //asdas
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Aluno 4">
    /**
     * Recebe um imovel, testa se o imovel já não existe na lista ,cria um novo
     * nó sendo o proximo desse novo nó o inicio da lista e anterior o fim e o
     * indice desse no como 0(zero) já que ele é o primeiro da lista, chama uma
     * função que icrementa o indice da lista em 1(um), e aponta o inicio para
     * esse novo nó
     *
     * @param im
     * @return
     */
    public boolean addFirst(Imovel im) {
        if (this.indexOf(im) == -1) {
            aux = new No(0, im, this.inicio, this.fim);
            this.incrementarLista(this.inicio);
            this.inicio = aux;
            /*
             apenas um teste para ver se a lista não está vazia
             se estiver ele apenas vai dizer que o fim é o inicio;
             */
            if (this.isEmpty()) {
                this.fim = this.inicio;
                this.inicio.setProximo(fim);
            }
            this.size++;
            return true;
        } else {
            return false;
        }
    }

    public boolean removeFirst() {
        return true;
    }

    public E getFirst() {
        No aux = null;
        return (E) aux.getImovel();
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Aluno 5">
    //<editor-fold defaultstate="collapsed" desc="isEmpty">
    /**
     * testa se a lista tem algum elemento
     *
     * @return true para lista lista vazia ou seja inicio é null, false caso
     * contrario ou seja um elemento ao menos na lista
     */
    @Override
    public boolean isEmpty() {
        return this.inicio == null;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="indexOf">
    /**
     * recebe um imovel e retorna o index dele dentro da lista
     *
     * @param im
     * @return o indice o imovel, caso o imovel passado esteja na lista, se não
     * ele retorna -1
     */
    public int indexOf(Imovel im) {
        No aux = inicio;
        while (aux != fim) {
            if (aux.getImovel().equals(im)) {
                return aux.getIndice();
            }
        }
        if (aux.getImovel().equals(im)) {
            return aux.getIndice();
        } else {
            return -1;
        }
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="removeLast">
    /**
     * remove o ultimo elemento da lista, testa se a lista não está vazia, se
     * não estiver faz outro teste para uma lista menor que 3 elementos, se for
     * o caso, entra e testa novamente, so que para uma lista com apenas um
     * elemento, se for o caso, zera a lista se não, ou seja dois elementos,
     * remove o fim seta o fim como inicio e ajusta os proximo e anterior do
     * inicio casa haja mais de 3 ou mais elementos, ele remove 1 e aponta o fim
     * para o anterior e ajusta proximo.
     *
     * @return true para lista nao vazia, false para lista vazia
     */
    public boolean removeLast() {
        if (!this.isEmpty()) {
            if (this.size < 3) {
                if (this.size == 1) {
                    this.clear();
                    return true;
                } else {
                    this.fim = this.inicio;
                    this.inicio.setAnterior(this.fim);
                    this.inicio.setProximo(this.fim);
                    this.indice--;
                    return true;
                }
            } else {
                No aux = this.fim;
                this.fim = aux.getAnterior();
                this.fim.setProximo(this.inicio);
                this.indice--;
                return true;
            }
        } else {
            return false;
        }
    }
    //</editor-fold>
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Metodos Extras">
    //<editor-fold defaultstate="collapsed" desc="aux">
    private void decrementarLista(No n) {
        this.indice--;
        No aux = n;
        while (aux != this.fim) {
            aux.decrementarIndice();
            aux = aux.getProximo();
        }
        aux.decrementarIndice();
    }

    private void incrementarLista(No n) {
        this.indice++;
        No aux = n;
        while (aux != this.fim) {
            aux.incrementarIndice();
            aux = aux.getProximo();
        }
        aux.decrementarIndice();
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Limpar lista">
    @Override
    public void clear() {
        this.inicio = null;
        this.fim = null;
        this.atual = null;
        this.indice = 0;
    }
    //</editor-fold>
    //</editor-fold>

    //PEGAR ASSINATURA AQUI
    //<editor-fold defaultstate="collapsed" desc="Metodos do List">
    @Override
    public int size() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean contains(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Iterator iterator() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object[] toArray() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object[] toArray(Object[] ts) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean containsAll(Collection clctn) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean addAll(Collection clctn) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean addAll(int i, Collection clctn) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean removeAll(Collection clctn) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean retainAll(Collection clctn) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object set(int i, Object e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int indexOf(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int lastIndexOf(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ListIterator listIterator() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ListIterator listIterator(int i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List subList(int i, int i1) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
//</editor-fold>

}
