/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilitarios;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lucas
 * @param <T>
 */
public class ListaDuploEncadeamento<T> {

    private NoDuplo<T> primeiro;
    private NoDuplo<T> ultimo;

    public boolean ehVazio() {
        return primeiro == null && ultimo == null;
    }

    protected void inserirInicio(T valor) {
        primeiro = new NoDuplo<>(valor, primeiro);
    }

    public void inserir(T valor) {
        if (ehVazio()) {
            inserirInicio(valor);
            ultimo = primeiro;
        } else {
            ultimo = new NoDuplo<>(valor, null, ultimo);
        }
    }

    protected NoDuplo<T> pesquisar(T valor) {
        NoDuplo<T> atual = primeiro;
        while (atual != null) {
            if (atual.getValor().equals(valor)) {
                break;
            }
            atual = atual.getProximo();
        }
        return atual;
    }

    /**
     * Exemplo de interação utilizando linguagem funcional
     *
     * @param consumer
     */
    public void para(Consumidor<T> consumer) {
        NoDuplo<T> atual = primeiro;
        while (atual != null) {
            consumer.enviarConteudo(atual.getValor());
            atual = atual.getProximo();
        }
    }

    /**
     * Interação utilizando linguage funcional com a lógica de tras para frente
     *
     * @param consumer
     */
    public void paraInvertido(Consumidor<T> consumer) {
        NoDuplo<T> atual = ultimo;
        while (atual != null) {
            consumer.enviarConteudo(atual.getValor());
            atual = atual.getAnterior();
        }
    }

    /**
     * Removendo um no da lista encadeada
     *
     * @param valor
     * @return
     */
    public boolean excluir(T valor) {
        if (!ehVazio()) {
            NoDuplo<T> atual = pesquisar(valor);
            //Verificando se encontrou
            if (atual != null) {
                /**
                 * Atualizando as referencias da lista
                 */
                if (atual == primeiro) {
                    primeiro = atual.getProximo();
                }
                if (atual == ultimo) {
                    ultimo = atual.getAnterior();
                }
                /**
                 * Atualizando o encadeamento
                 */
                if (atual.getProximo() != null) {
                    atual.getProximo().setAnterior(atual.getAnterior());
                }
                if (atual.getAnterior() != null) {
                    atual.getAnterior().setProximo(atual.getProximo());
                }
                return true;
            }
        }
        return false;
    }

    /**
     *
     * Busca utilizando reflection
     *
     *
     *
     * @param <ST>
     * @param dado
     * @param clazz
     * @param campo
     * @return Caso o retorno seje nulo e porque não foi encontrado o registro
     * Caso encontre e retornado o Objeto da lista
     *
     */
    public <ST> T pesquisar(ST dado, Class<ST> clazz, String campo) {
        if (!ehVazio()) {
            NoDuplo<T> noAtual = primeiro;
            while (noAtual != null) {
                try {
                    String campoFormatado = campo.substring(0, 1).toUpperCase() + campo.substring(1);
                    Method method = noAtual.getValor().getClass().getDeclaredMethod("get" + campoFormatado);
                    Object retorno = method.invoke(noAtual.getValor());
                    if (retorno.equals(dado)) {
                        return noAtual.getValor();
                    }
                } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                    Logger.getLogger(ListaDuploEncadeamento.class.getName()).log(Level.SEVERE, null, ex);
                }
                noAtual = noAtual.getProximo();
            }
        }
        return null;
    }

}
