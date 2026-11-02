package org.example.app;

import org.example.cardapio.ItemCardapio;
import org.example.pedido.Pedido;

public class Main {
    static void main() {
        MenuConsole menu = new MenuConsole();
        RestauranteFacade organizadorDoRestaurante = new RestauranteFacade();
        boolean restauranteAberto = true;

        while (restauranteAberto){
            menu.exibirMenu();
            int opcao = menu.lerOpcoes();

            switch (opcao){
                case 1:
                    registrarNovoPedido(menu, organizadorDoRestaurante);
                    break;
                case 2:
                    menu.exibirPedidosDoDia(organizadorDoRestaurante.getPedidosRecebidosNoDia());
                    break;
                case 3:
                    double totalDoDia = organizadorDoRestaurante.calcularTotalDoDia();
                    menu.exibirFechamentoDoDia(totalDoDia);
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
    private static void registrarNovoPedido(MenuConsole menu, RestauranteFacade organizadorDoRestaurante){
        Pedido pedido = null;

        while(pedido == null){
            String nomeDoCliente = menu.lerNomeDoCliente();
            try {
                pedido = organizadorDoRestaurante.criarPedido(nomeDoCliente);
            } catch (IllegalArgumentException iae) {
                menu.exibirMensagemDeErro(iae.getMessage());
            }
        }

        do {
            int opcaoDeLanche = menu.lerOpcaoDeLanche();

            try {
                ItemCardapio itemCardapio = organizadorDoRestaurante.criarLanche(opcaoDeLanche);

                int opcaoDeAdicional = menu.lerOpcaoDeAdicionais(itemCardapio.getDescricao());
                while (opcaoDeAdicional != 0){
                    try {
                        itemCardapio = organizadorDoRestaurante.colocarAdicional(itemCardapio, opcaoDeAdicional);
                    } catch (IllegalArgumentException iae) {
                        menu.exibirMensagemDeErro(iae.getMessage());
                    }
                    opcaoDeAdicional = menu.lerOpcaoDeAdicionais(itemCardapio.getDescricao());
                }
                //depois de tudo, oq fazer? adicionar o item na lista do pedido
                organizadorDoRestaurante.adicionarItemNoPedido(pedido, itemCardapio);

            } catch (IllegalArgumentException iae) {
                menu.exibirMensagemDeErro(iae.getMessage());
            }
        }
        while (menu.lerSeQuerAdicionarMaisUmItem(pedido.getNomeDoCliente()));

        menu.exibirPedido(pedido);
        menu.exibirMensagemPedidoRegistradoComSucesso(pedido);
    }
}
