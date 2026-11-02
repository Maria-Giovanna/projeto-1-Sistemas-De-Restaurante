package org.example.cardapio;

public class ComCheddar implements ItemCardapio{
    //ATRIBUTOS
    private ItemCardapio adicional;

    //CONSTRUTOR
    public ComCheddar(ItemCardapio adicional){
        if (adicional == null){
            throw new IllegalArgumentException("Não dá pra colocar cheddar em nada!");
        }
        this.adicional = adicional;
    }

    //METODO DO CONTRATO
    @Override
    public String getDescricao(){
        return adicional.getDescricao()+ " + cheddar";
    }

    @Override
    public double getPreco() {
        return adicional.getPreco()+ 4.00;
    }
}
