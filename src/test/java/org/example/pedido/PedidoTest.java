package org.example.pedido;

import org.example.cardapio.Lanche;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PedidoTest {
    @Test
    void somaTotalDosPrecosVindoDaListaDosLanchesParaAListaDosPedidos(){
        Pedido pedido = new Pedido("Magi",1);
        pedido.adicionarItemAoPedido(new Lanche("X-burguer", 38.90));
        pedido.adicionarItemAoPedido(new Lanche("X-burguer", 38.90));
        pedido.adicionarItemAoPedido(new Lanche("X-salada", 18.79));
        pedido.adicionarItemAoPedido(new Lanche("X-salada", 18.79));

        assertEquals(115.38, pedido.calcularOTotalDoPedido(), 0.001);
    }


}