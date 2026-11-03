package org.example.app;

import org.example.cardapio.ItemCardapio;
import org.example.cardapio.Lanche;
import org.example.pedido.Pedido;

import java.awt.*;

public class Main {
    static void main() {
        MenuConsole menu = new MenuConsole();
        RestauranteFacade organizadorDoRestaurante = new RestauranteFacade();
        boolean restauranteAberto = true;

        while (restauranteAberto) {
            menu.exibirMenu();
            int opcao = menu.lerOpcoes();

            switch (opcao) {
                case 1:
                    registrarNovoPedido(menu, organizadorDoRestaurante);
                    break;
                case 2:
                    menu.exibirPedidosDoDia(organizadorDoRestaurante.getPedidosRecebidosNoDia());
                    break;
                case 3:
                    consultarPedido(menu, organizadorDoRestaurante);
                    break;
                case 4:
                    double totalDoDia = organizadorDoRestaurante.calcularTotalDoDia();
                    menu.exibirFechamentoDoDia(totalDoDia, organizadorDoRestaurante.getTodosOsLanchesDoDiaSemRepetir());

                    restauranteAberto = false;
                    break;
                case 0:
                    menu.exibirMensagemDeDespedida();

                    restauranteAberto = false;
                    break;
                default:
                    menu.exibirOpcaoInvalida();
            }
        }
    }

    //METODOS
    private static void registrarNovoPedido(MenuConsole menu, RestauranteFacade organizadorDoRestaurante) {
        Pedido pedido = null;

        while (pedido == null) {
            String nomeDoCliente = menu.lerNomeDoCliente();
            try {
                pedido = organizadorDoRestaurante.criarPedido(nomeDoCliente);
            } catch (IllegalArgumentException iae) {
                menu.exibirMensagemDeErro(iae.getMessage());
            }
        }

        while (pedido.estaVazio()){
            try {
                ItemCardapio primeiroItem = montarUmItemDentroDoPedido(pedido,menu,organizadorDoRestaurante, pedido.getNomeDoCliente());
                organizadorDoRestaurante.adicionarItemNoPedido(pedido, primeiroItem);
            } catch (IllegalArgumentException iae) {
                menu.exibirMensagemDeErro(iae.getMessage());
            }
        }

        boolean pedidoConcluido = revisarPedido(menu, organizadorDoRestaurante, pedido);
        if (pedidoConcluido) {
            menu.exibirMensagemPedidoRegistradoComSucesso(pedido);
        }
    }

    private static void consultarPedido(MenuConsole menu, RestauranteFacade organizadorDoRestaurante){
        int numeroDoPedido = menu.lerNumeroDoPedido();
        if (!organizadorDoRestaurante.existeUmPedido(numeroDoPedido)){
            menu.exibirPedidoNaoEncontrado(numeroDoPedido);
            return;
        }
            Pedido pedido = organizadorDoRestaurante.buscarOPedido(numeroDoPedido);
            menu.exibirPedido(pedido);
        }


    //devolve true se a pessoa CONCLUIU, false se CANCELOU
    private static boolean revisarPedido(MenuConsole menu, RestauranteFacade organizadorDoRestaurante, Pedido pedido){
        while (true) {
            menu.exibirPedido(pedido); // resumo da comanda como está AGORA
            int opcaoDeRevisao = menu.lerOpcaoDeRevisao();

            switch (opcaoDeRevisao) {
                case 1: //ACRESCENTAR: monta um item novo
                    try {
                        ItemCardapio itemNovo = montarUmItemDentroDoPedido(pedido,menu, organizadorDoRestaurante, pedido.getNomeDoCliente());
                        organizadorDoRestaurante.adicionarItemNoPedido(pedido, itemNovo);
                        menu.exibirItemAdicionado();
                    } catch (IllegalArgumentException iae) {
                        menu.exibirMensagemDeErro(iae.getMessage());
                    }
                    break;

                case 2: //REMOVER: escolhe pelo número e apaga
                    try {
                        int posicao = menu.lerPosicaoDoItem(pedido, "remover");
                        organizadorDoRestaurante.removerItemDoPedido(pedido, posicao);
                        menu.exibirItemRemovido();
                    } catch (IllegalArgumentException iae) {
                        menu.exibirMensagemDeErro(iae.getMessage());
                    }
                    break;

                case 3: //TROCAR: remove o escolhido e monta um NOVO no lugar
                    try {
                        int posicao = menu.lerPosicaoDoItem(pedido, "trocar");
                        organizadorDoRestaurante.removerItemDoPedido(pedido, posicao);

                        ItemCardapio itemNovo = montarUmItemDentroDoPedido(pedido,menu, organizadorDoRestaurante, pedido.getNomeDoCliente());

                        organizadorDoRestaurante.adicionarItemNoPedido(pedido, itemNovo);
                        menu.exibirItemTrocado();

                    } catch (IllegalArgumentException iae) {
                        menu.exibirMensagemDeErro(iae.getMessage());
                    }
                    break;

                case 4: //CANCELAR: tira a comanda do fichário (e do total)
                    organizadorDoRestaurante.cancelarOPedido(pedido.getNumeroDoPedido());
                    menu.exibirPedidoCancelado(pedido.getNumeroDoPedido());
                    return false;

                case 0: //CONCLUIR: só deixa se tiver pelo menos um item
                    if (pedido.estaVazio()) {
                        menu.exibirPedidoVazio();
                    } else {
                        return true;
                    }
                    break;

                default:
                    menu.exibirOpcaoInvalida();
            }
        }
    }

    private static ItemCardapio montarUmItemDentroDoPedido( Pedido pedido ,MenuConsole menu, RestauranteFacade organizadorDoRestaurante, String nomeDoCliente){
        int opcaoDeLanche = menu.lerOpcaoDeLanche();
        Lanche lanche = organizadorDoRestaurante.criarLanche(opcaoDeLanche);
        ItemCardapio itemCardapio = lanche;

            //Pedir o lanche e alterar os ingredientes
            try {
                int opcaoDeIngrediente = menu.lerIngredientesParaRetirarDoLanche(lanche.getDescricao(), nomeDoCliente, lanche);

                while (opcaoDeIngrediente != 0) {
                    try {
                        organizadorDoRestaurante.retirarOuDevolverIngrediente(lanche, opcaoDeIngrediente);
                    } catch (IllegalArgumentException iae) {
                        menu.exibirMensagemDeErro(iae.getMessage());
                    }
                    opcaoDeIngrediente = menu.lerIngredientesParaRetirarDoLanche(lanche.getDescricao(), nomeDoCliente, lanche);
                }

                //Perguntar dos adicionais
                int opcaoDeAdicionais = menu.lerOpcaoDeAdicionais(itemCardapio.getDescricao());

                while (opcaoDeAdicionais != 0) {
                    try {
                        itemCardapio = organizadorDoRestaurante.colocarAdicional(itemCardapio, opcaoDeAdicionais);
                    } catch (IllegalArgumentException iae) {
                        menu.exibirMensagemDeErro(iae.getMessage());
                    }
                    opcaoDeAdicionais = menu.lerOpcaoDeAdicionais(itemCardapio.getDescricao());
                }
            } catch (IllegalArgumentException iae) {
                menu.exibirMensagemDeErro(iae.getMessage());
            }
        return itemCardapio;
    }

}