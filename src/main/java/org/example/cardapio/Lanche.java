package org.example.cardapio;

public class Lanche {
    private String nomeDoLanche;
    private double precoDoLanche;


    //SETS
    public void setNomeDoLanche(String nomeDoLanche) {
        if (nomeDoLanche == null|| nomeDoLanche.trim().isEmpty()){
            //não pode nenhum tipo de espaço vazio
            throw new IllegalArgumentException("O nome do lanche não pode ser em branco");
        }
        this.nomeDoLanche = nomeDoLanche.trim();
    }

    public void setPrecoDoLanche(double precoDoLanche) {
        if (precoDoLanche <0){
            throw new IllegalArgumentException("O preço não pode ser negativo!");
        }
        this.precoDoLanche = precoDoLanche;
    }

    //GETS
    public String getNomeDoLanche() {
        return nomeDoLanche.trim();
    }

    public double getPrecoDoLanche(){
        return precoDoLanche;
    }

    public Lanche(String nomeDoLanche, double precoDoLanche){
        setNomeDoLanche(nomeDoLanche);
        setPrecoDoLanche(precoDoLanche);
    }
}
