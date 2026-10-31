package org.example.cardapio;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LancheTest {

    @Test
    void lancheValidoQueGuardaNomeEPreco(){
        Lanche lanche=new Lanche(" X-bacon ",16.00);

        assertEquals("X-bacon",lanche.getNomeDoLanche());
        assertEquals(16.0, lanche.getPrecoDoLanche(), 0.001);
    }

    @Test
    void lancheInvalidoComNomeEmBranco(){
        Lanche lanche = new Lanche("", 16);

        assertEquals("X-burguer",lanche.getNomeDoLanche());
        assertEquals(16.0, lanche.getPrecoDoLanche(), 0.001);

    }

    @Test
    void lancheInvalidoComPrecoNegativo(){
        Lanche lanche = new Lanche("X-burguer", -9);

        assertEquals("X-burguer",lanche.getNomeDoLanche());
        assertEquals(16.0, lanche.getPrecoDoLanche(), 0.001);

    }


}