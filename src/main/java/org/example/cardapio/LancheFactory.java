package org.example.cardapio;

public class LancheFactory {

    //METODOS

    //criar cada lanche do cardapio
    public static Lanche xBurger(){
        return new Lanche("X-Burguer", 32.90);
    }
    public static Lanche xSalada(){
        return new Lanche("X-Salada", 37.60);
    }
    public static Lanche xBacon(){
        return new Lanche("X-Bacon", 44.80);
    }
    public static Lanche xBig(){
        return new Lanche("X-Big", 44.80);
    }
}
