package org.example.app;

import org.example.cardapio.ItemCardapio;
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
        System.out.println("1 - X-Burger  | R$ 32,90");
        System.out.println("2 - X-Salada  | R$ 37,60");
        System.out.println("3 - X-Bacon   | R$ 44,80");
        System.out.println("4 - X-Big     | R$ 44,80");
        System.out.print("\nQual lanche você quer? ");

        int opcaoDeLanche = scanner.nextInt();
        scanner.nextLine();

        return opcaoDeLanche;
    }

    public boolean lerSeQuerAdicionarMaisUmItem(String lerNomeDoCliente) {
        while (true) {
            System.out.printf("\nDeseja adicionar mais um item, %s?", lerNomeDoCliente);
            System.out.println("\nResponda com s (sim) ou n (não)!");
            System.out.println("========================");
            String resposta = scanner.nextLine().trim();

            if (resposta.equalsIgnoreCase("s")) {
                return true;
            } else if (resposta.equalsIgnoreCase("n")) {
                return false;
            } else {
                System.out.println("\nOps, responda apenas com s ou n\n");
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

        for (ItemCardapio lanche : pedido.getItensDoPedido()) {
            System.out.printf("%-12s --- R$%.2f%n", lanche.getDescricao(), lanche.getPreco());
        }
        System.out.printf("\nValor total do pedido: R$%.2f%n", pedido.calcularOTotalDoPedido());
    }

    //REVISAR
    public void exibirPedidosDoDia(Collection<Pedido> getTodosOsPedidosDoFichario) {
        System.out.println("\n==========================");
        System.out.println("\n===== PEDIDOS DO DIA =====");
        System.out.println("\n==========================");
        if (getTodosOsPedidosDoFichario.isEmpty()) {
            System.out.println("Ainda não temos nenhum pedido para hoje :(");
            return;
        }
        for (Pedido pedido : getTodosOsPedidosDoFichario) {
            exibirPedido(pedido);
        }
    }

    public void exibirFechamentoDoDia(double totalDoDia) {
        System.out.println("\n=================================");
        System.out.println("\n======= FECHAMENTO DO DIA =======");

        System.out.printf("\nTotal vendido hoje: R$ %.2f%n", totalDoDia);
    }

    //EXIBIR MENSAGENS
    public void exibirOpcaoInvalida() {
        System.out.printf("Ops, parece que essa opção não existe. Tente de novo! :)");
    }

    public void exibirMensagemDeErro(String mensagem) {
        System.out.printf("Erro ⚠ :" + mensagem);
    }

    public void exibirMensagemDeDespedida() {
        System.out.println("\nMuito obrigada pelo seu dia de trabalho! Até amanhã :3");
    }


}
