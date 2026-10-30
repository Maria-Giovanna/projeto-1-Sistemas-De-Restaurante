package org.example.cardapio;

public class Lanche {
    private String nome;
    private double preco;


    //SETS
    public void setNome(String nome) {
        if (nome == null|| nome.isEmpty()){
            //não pode nenhum tipo de espaço vazio
            throw new IllegalArgumentException("O nome do lanche não pode ser em branco");
        }
        this.nome = nome.trim();
    }

    public void setPreco(double preco) {
        if (preco<0){
            throw new IllegalArgumentException("O preço não pode ser negativo!");
        }
        this.preco = preco;
    }

    //GETS
    public String getNome() {
        return nome.trim();
    }

    public double getPreco(){
        return preco;
    }

    public Lanche(String nome, double preco){
        setNome(nome);
        setPreco(preco);
    }
}
