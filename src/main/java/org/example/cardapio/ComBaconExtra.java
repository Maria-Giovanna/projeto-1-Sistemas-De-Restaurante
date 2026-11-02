package org.example.cardapio;

public class ComBaconExtra implements ItemCardapio{
    //ATRIBUTOS
    private ItemCardapio adicional;

    //CONSTRUTOR
    public ComBaconExtra(ItemCardapio adicional){
        if (adicional == null){
            throw new IllegalArgumentException("Não dá para colocar o bacon extra em nada");
        }
        this.adicional= adicional;
    }

    //METODO DO CONTRATO
    @Override
    public String getDescricao(){
        return adicional.getDescricao()+ " + bacon extra";
    }

    @Override
    public double getPreco() {
        return adicional.getPreco()+ 6.00;
    }



}
