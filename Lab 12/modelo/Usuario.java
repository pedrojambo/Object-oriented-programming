package modelo;

import java.util.*;

public class Usuario {

    private String nome;

    private long cpf;

    private String endereco;

    private Map<Integer, Livro> objetosADevolver = new HashMap<>();

    public Usuario(String nome, long cpf) {

        this.nome = nome;

        this.cpf = cpf;
    }

    public long getCpf() {
        return this.cpf;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
        return;
    }

    public String getEndereco() {
        return this.endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
        return;
    }

    public void devolverLivro( Livro livro) {
        objetosADevolver.remove(getKeyByValue(objetosADevolver, livro));
        return;
    }

    public void emprestarLivro (Livro livro) {
        objetosADevolver.put(livro.getQuantidade(), livro);
        return;
    }

    public boolean possuiObjeto (Object obj){
        if(objetosADevolver.containsValue(obj)){
            return true;
        }else{
            return false;
        }
    }

    public int getQntLivros() {
        return objetosADevolver.size();
    }

    @Override
    public boolean equals(Object o){
        Usuario usuario = (Usuario) o;
        return cpf == usuario.cpf;
    }

    public static <T, E> T getKeyByValue(Map<T, E> map, E value) {
        for (Map.Entry<T, E>  entry : map.entrySet()){
            if (Objects.equals(value, entry.getValue())) {
                return entry.getKey();
            }
        }
        return null;
    }
}