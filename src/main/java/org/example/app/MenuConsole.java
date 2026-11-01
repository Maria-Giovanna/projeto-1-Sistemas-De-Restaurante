package org.example.app;

import org.example.cardapio.Lanche;
import org.example.pedido.Pedido;

import java.util.Collection;
import java.util.Scanner;

public class MenuConsole {
    //ATRIBUTOS
    private Scanner scanner = new Scanner(System.in);

    //======================METODOS================================

    //LEITURA
    public int lerOpcoes() {
        System.out.println("\n=================");
        System.out.println("Digite uma opção:");
        int opcaoEscolhida = scanner.nextInt();
        scanner.nextLine();

        return opcaoEscolhida;
    }

    public String lerNomeDoCliente() {
        System.out.println("\n=================");
        System.out.println("Qual o nome do cliente");

        return scanner.nextLine();
    }

    public int lerOpcaoDeLanche() {
        System.out.println("\n======= CARDÁPIO =======");
        System.out.println("1 - X-Burger  | R$ 16,00");
        System.out.println("2 - X-Salada  | R$ 18,00");
        System.out.println("3 - X-Bacon   | R$ 22,00");
        System.out.println("4 - Big burger  | R$ 43,90");
        System.out.print("Qual lanche você quer? ");

        int opcaoDeLanche = scanner.nextInt();
        scanner.nextLine();

        return opcaoDeLanche;
    }

    public boolean lerSeQuerAdicionarMaisUmItem(String lerNomeDoCliente) {
        while (true) {
            System.out.printf("Deseja adicionar mais um item, %s?", lerNomeDoCliente);
            System.out.println("Responda com s (sim) ou n (não)!");
            System.out.println("========================");
            String resposta = scanner.nextLine().trim();

            if (resposta.equalsIgnoreCase("s")) {
                return true;
            } else if (resposta.equalsIgnoreCase("n")) {
                return false;
            } else {
                System.out.println("Ops, responda apenas com s ou n");
            }
        }
    }

    //EXIBIÇÃO
    public void exibirMenu() {
        System.out.println("\n======= RESTAURANTE =======");
        System.out.println("1 - Registrar novo pedido");
        System.out.println("2 - Listar pedidos do dia");
        System.out.println("3 - Fechar restaurante");
        System.out.println("0 - Sair");
    }


    public void exibirPedido(Pedido pedido) {
        System.out.println("========================");
        System.out.printf("\n====Pedido nº %d====\nCliente: %s%n", pedido.getNumeroDoPedido(), pedido.getNomeDoCliente());
        System.out.println("_________________________");

        for (Lanche lanche : pedido.getItensDoPedido()) {
            System.out.printf("\n%-s --- R$%.2f%n", lanche.getNomeDoLanche(), lanche.getPrecoDoLanche());
        }
        System.out.printf("Valor total do pedido: R$%.2f%n", pedido.calcularOTotalDoPedido());
    }


    //REVISAR
    public void exibirPedidosDoDia(Collection<Pedido> getTodosOsPedidosDoFichario) {
        System.out.println("\n===== PEDIDOS DO DIA =====");
        if (getTodosOsPedidosDoFichario.isEmpty()) {
            System.out.println("Ainda não temos nenhum pedido para hoje :(");
            return;
        }
        for (Pedido pedido : getTodosOsPedidosDoFichario) {
            exibirPedido(pedido);
        }
    }

    public void exibirFechamentoDoDia(double totalDoDia) {
        System.out.println("\n======= FECHAMENTO DO DIA =======");
        System.out.printf("Total vendido hoje: R$ %.2f%n", totalDoDia);
    }

    //EXIBIR MENSAGENS


    public void exibirOpcaoInvalida(String nome) {
        System.out.printf("Ops, parece que essa opção não existe %s. Tente de novo! :)", nome);
    }

    public void exibirMensagemDeErro(String mensagem) {
        System.out.printf("Erro ⚠ :" + mensagem);
    }

    public void exibirMensagemDeDespedida() {
        System.out.println("\nMuito obrigada pelo seu dia de trabalho! Até amanhã :3");
    }


}
