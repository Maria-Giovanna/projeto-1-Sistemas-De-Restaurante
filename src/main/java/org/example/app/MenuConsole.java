package org.example.app;

import org.example.cardapio.ItemCardapio;
import org.example.cardapio.Lanche;
import org.example.pedido.Pedido;

import java.util.Collection;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuConsole {
    //ATRIBUTOS
    private Scanner scanner = new Scanner(System.in);

    //======================METODOS================================

    //LEITURA
    public int lerOpcoes() {
        System.out.println("\n=================");
        System.out.println("Digite uma opção:");
        int opcaoEscolhida = lerNumeroInteiro();

        return opcaoEscolhida;
    }

    public String lerNomeDoCliente() {
        System.out.println("\n=================");
        System.out.println("Qual o nome do cliente");
        String nomeDoCliente = scanner.nextLine();

        return nomeDoCliente;
    }

    public int lerNumeroDoPedido(){
        System.out.println("\nQual o número do pedido que procura?");
        int numeroDoPedido = lerNumeroInteiro();

        return numeroDoPedido;
    }

    public int lerOpcaoDeLanche() {
        System.out.println("\n======= CARDÁPIO =======");
        System.out.println("1 - X-Burger  | R$ 32,90");
        System.out.println("2 - X-Salada  | R$ 37,60");
        System.out.println("3 - X-Bacon   | R$ 44,80");
        System.out.println("4 - X-Big     | R$ 44,80");
        System.out.print("\nQual lanche você quer? ");
        int opcaoDeLanche = lerNumeroInteiro();

        return opcaoDeLanche;
    }

    public int lerOpcaoDeAdicionais(String descricaoAtual) {
        System.out.println("\n======= ADICIONAIS =======");
        System.out.println("Seu item até agora: " + descricaoAtual);
        System.out.println("1 - Cheddar      | + R$ 4,00");
        System.out.println("2 - Bacon extra  | + R$ 6,00");
        System.out.println("0 - Sem mais adicionais");
        System.out.print("Escolha um adicional: ");
        int opcaoDeAdicional = lerNumeroInteiro();

        return opcaoDeAdicional;
    }

    //metodo de tirar ou devolver
    public String textoDeOpcao(boolean temOuNaoOIngrediente, String nomeDoIngrediente) {
        if (temOuNaoOIngrediente) {
            return "Tirar " + nomeDoIngrediente;
        } else {
            return "Colocar " + nomeDoIngrediente + " (você removeu)";
        }
    }

    public int lerIngredientesParaRetirarDoLanche(String descricaoAtual, String nomeDoCliente, Lanche lanche) {
        System.out.println("\n======= INGREDIENTES =======");
        System.out.println("Seu lanche até agora: " + descricaoAtual);

        System.out.printf("\nDeseja retirar ou adicionar algum dos ingredientes, %s?", nomeDoCliente);
        System.out.printf("\n1 - %s", textoDeOpcao(lanche.isQueijo(), "Queijo"));
        System.out.printf("\n2 - %s", textoDeOpcao(lanche.isAlface(), "Alface"));
        System.out.printf("\n3 - %s", textoDeOpcao(lanche.isTomate(), "Tomate"));
        System.out.printf("\n4 - %s", textoDeOpcao(lanche.isCebola(), "Cebola"));
        System.out.println("\n0 - Manter o lanche do jeito que está");

        System.out.print("\nEscolha: ");
        int opcaoDeRetirarOIngrediente = lerNumeroInteiro();

        return opcaoDeRetirarOIngrediente;
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
                System.out.println("\nResponda apenas com s ou n\n");
            }
        }
    }

    public int lerOpcaoDeRevisao() {
        System.out.println("\n======= REVISAR PEDIDO =======");
        System.out.println("1 - Acrescentar item ao pedido");
        System.out.println("2 - Remover item do pedido");
        System.out.println("3 - Trocar um item do pedido");
        System.out.println("4 - Cancelar pedido inteiro");
        System.out.println("0 - Concluir pedido");
        System.out.print("Escolha: ");
        int opcaoDeRevisao = lerNumeroInteiro();

        return opcaoDeRevisao;
    }

    public int lerPosicaoDoItem(Pedido pedido, String acao){
        System.out.printf("\n======= ITENS DA COMANDA nº %d =======", pedido.getNumeroDoPedido());
        System.out.println("=======================================");

        int posicao = 1;
        for (ItemCardapio itemCardapio : pedido.getItensDoPedido()){
            System.out.printf("%d - %s ---> R$%.2f\n", posicao, itemCardapio.getDescricao(), itemCardapio.getPreco());
            posicao++;
        }

        System.out.printf("Qual item você quer alterar, %s?", pedido.getNomeDoCliente());
        int posicaoEscolhida = lerNumeroInteiro();

        return posicaoEscolhida;
    }

    //EXIBIÇÃO
    public void exibirMenu() {
        System.out.println("\n======= RESTAURANTE =======");
        System.out.println("1 - Registrar novo pedido");
        System.out.println("2 - Listar pedidos do dia");
        System.out.println("3 - Consultar um pedido");
        System.out.println("4 - Fechar restaurante");
        System.out.println("0 - Sair");
    }

    public void exibirPedido(Pedido pedido) {
        System.out.println("========================");
        System.out.printf("\n====Pedido nº %d====\nCliente: %s%n", pedido.getNumeroDoPedido(), pedido.getNomeDoCliente());
        System.out.println("_________________________");

        for (ItemCardapio lanche : pedido.getItensDoPedido()) {
            System.out.printf("%-40s --- R$%.2f%n", lanche.getDescricao(), lanche.getPreco());
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

    public void exibirFechamentoDoDia(double totalDoDia, HashSet<String> getTodosOsLanchesDoDiaSemRepetir) {
        System.out.println("\n=================================");
        System.out.println("\n======= FECHAMENTO DO DIA =======");

        System.out.printf("\nTotal vendido hoje: R$ %.2f%n", totalDoDia);

        System.out.printf("\nDiferentes tipos de lanches pedidos hoje: %d\n", getTodosOsLanchesDoDiaSemRepetir.size());
        for (String tiposDeLanches : getTodosOsLanchesDoDiaSemRepetir){
            System.out.println("--> "+ tiposDeLanches);
        }
    }

    //EXIBIR MENSAGENS
    public void exibirPedidoNaoEncontrado(int numeroDoPedido) {
        System.out.printf("Ops, não existe nenhum pedido nº %d :(\n", numeroDoPedido);
    }

    public void exibirItemAdicionado() {
        System.out.println("Item acrescentado no pedido com sucesso! ✅");
    }

    public void exibirItemRemovido() {
        System.out.println("\nItem removido do pedido com sucesso! ✅");
    }

    public void exibirItemTrocado() {
        System.out.println("\nItem trocado com sucesso! ✅");
    }

    public void exibirPedidoVazio() {
        System.out.println("\nSeu pedido está vazio! Acrescente pelo menos um item ou cancele todo o pedido.");
    }

    public void exibirPedidoCancelado(int numeroDoPedido) {
        System.out.printf("\nPedido nº %d cancelado. Ele não conta mais no total do dia.\n", numeroDoPedido);
    }

    public void exibirOpcaoInvalida() {
        System.out.println("\nOps, parece que essa opção não existe. Tente de novo! :)");
    }

    public void exibirMensagemDeErro(String mensagem) {
        System.out.println("\nErro ⚠ :" + mensagem);
    }

    public void exibirMensagemDeDespedida() {
        System.out.println("\nMuito obrigada pelo seu dia de trabalho! Até amanhã :3");
    }

    public void exibirMensagemPedidoRegistradoComSucesso(Pedido pedido) {
        System.out.println("========================");
        System.out.printf("\nPedido nº %d registrado com sucesso ✅", pedido.getNumeroDoPedido());
        System.out.printf("\nMuito obrigada, %s! Seu pedido ja foi enviado para a cozinha :)", pedido.getNomeDoCliente());
        System.out.println("\n========================");
    }

    private int lerNumeroInteiro(){
        while (true){
            try {
                int numero = scanner.nextInt();
                scanner.nextLine();

                return numero;
            } catch (InputMismatchException ime) {
               scanner.nextLine();
                System.out.println("Digite apenas números:");
            }
        }
    }

}
