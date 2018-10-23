/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teste;

import java.util.Scanner;
import utilitarios.ListaDuploEncadeamento;

/**
 *
 * @author lucas
 */
public class Testes {

    public static void main(String[] args) {
        ListaDuploEncadeamento<Aluno> listaDE = new ListaDuploEncadeamento<>();
        char op;
        Scanner scanner = new Scanner(System.in);
        Aluno aluno = null;
        do {

            System.out.println("A- Criar novo aluno");
            System.out.println("B- Inserir aluno na lista");
            System.out.println("C- Buscar aluno por matrícula");
            System.out.println("D- Remover aluno da lista");
            System.out.println("E- Imprimir alunos da lista");
            op = scanner.nextLine().toUpperCase().charAt(0);
            switch (op) {
                case 'A':
                    System.out.println("Digite a matricula");
                    aluno = new Aluno(scanner.nextLine());
                    System.out.println("Digite o nome");
                    aluno.setNome(scanner.nextLine());
                    break;
                case 'B':
                    if (aluno != null) {
                        if (listaDE.pesquisar(aluno.getMatricula(), String.class, "matricula") == null) {
                            listaDE.inserir(aluno);
                        } else {
                            System.out.println("Já existe esse aluno na lista!");
                        }
                    } else {
                        System.out.println("Crie um usuário primeiro");
                    }
                    break;
                case 'C':
                    System.out.println("Digite a matricula");
                    aluno = listaDE.pesquisar(scanner.nextLine(), String.class, "matricula");
                    if (aluno == null) {
                        System.out.println("Aluno não encontrado!");
                    } else {
                        linhaTela();
                        System.out.println(aluno);
                        linhaTela();
                    }
                    break;
                case 'D':
                    System.out.println("Digite a matricula do usuário que você quer remover");
                    if (listaDE.excluir(new Aluno(scanner.nextLine()))) {
                        System.out.println("Removido com sucesso!");
                    } else {
                        System.out.println("Não foi possível remover esse aluno!");
                    }
                    break;
                case 'E':
                    linhaTela();
                    listaDE.para(t -> {
                        System.out.println(t);
                        linhaTela();
                    });
                    linhaTela();
                    break;
            }
        } while (op != 0);

    }

    public static final void linhaTela() {
        System.out.println("-----------------------------------------------------------------------");
    }
}
