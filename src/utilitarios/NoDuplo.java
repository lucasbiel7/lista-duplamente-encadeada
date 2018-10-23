/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilitarios;

/**
 *
 * @author lucas
 * @param <T>
 */
public class NoDuplo<T> {

    public T valor;
    private NoDuplo<T> proximo;
    private NoDuplo<T> anterior;

    public NoDuplo(T valor, NoDuplo<T> proximo, NoDuplo<T> anterior) {

        this.valor = valor;
        this.proximo = proximo;
        this.anterior = anterior;
        if (proximo != null) {
            proximo.setAnterior(this);
        }
        if (anterior != null) {
            anterior.setProximo(this);
        }
    }

    public NoDuplo(T valor, NoDuplo<T> proximo) {
        this.valor = valor;
        this.proximo = proximo;
        if (proximo != null) {
            proximo.setAnterior(this);
        }
    }

    public NoDuplo(T valor) {
        this.valor = valor;
    }

    public T getValor() {
        return valor;
    }

    public void setValor(T valor) {
        this.valor = valor;
    }

    public NoDuplo<T> getProximo() {
        return proximo;
    }

    public void setProximo(NoDuplo<T> proximo) {
        this.proximo = proximo;
    }

    public NoDuplo<T> getAnterior() {
        return anterior;
    }

    public void setAnterior(NoDuplo<T> anterior) {
        this.anterior = anterior;
    }

}
