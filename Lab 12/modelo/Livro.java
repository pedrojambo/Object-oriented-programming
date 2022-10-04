package modelo;

import java.util.Objects;

public class Livro {

    private String titulo;

    private String autor;

    private int anodePublicacao;

    public int quantidade;

    public Livro(String titulo, String autor, int anoDePublicacao) {

        this.titulo = titulo;

        this.autor = autor;

        this.anodePublicacao = anoDePublicacao;

        this.quantidade = 0;
    }

    public String getTitulo() {
        return this.titulo;
    }

    public String getAutor() {
        return this.autor;
    }

    public int getAnoDePublicacao() {
        return this.anodePublicacao;
    }

    public int getQuantidade(){
        return this.quantidade;
    }

    public void setQuantidade(int qnt){
        this.quantidade = qnt;
    }
}