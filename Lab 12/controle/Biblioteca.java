package controle;

import excecao.DevolucaoInvalidaException;
import excecao.LimiteEmprestimosExcedidoException;
import excecao.UsuarioNaoCadastradoException;
import modelo.Livro;
import modelo.Usuario;

import javax.swing.*;
import java.util.*;

public class Biblioteca {

    private Map<Long, Usuario> Usuarios;
    private ArrayList<Livro> Acervo;
    private int TotalLivros;

    /** quantidade mínima de unidades de um livro que precisam existir nas estantes da biblioteca para
     que o livro possa ser emprestado */
    public static final int MIN_COPIAS_PARA_PODER_EMPRESTAR = 2;

    /** quantidade máxima de livros da biblioteca que podem estar emprestados, a qualquer tempo, a um mesmo usuário */
    public static final int MAX_LIVROS_DEVIDOS = 3;

    public Biblioteca() {
        this.Usuarios = new HashMap<>();
        this.Acervo = new ArrayList<>();
        TotalLivros = 0;
    }

    /**
     * Cadastra um usuário. Caso o usuário já exista, atualiza seu nome e seu endereço.
     *
     * @param usuario o usuário a ser inserido/atualizado.
     */
    public void cadastrarUsuario(Usuario usuario) {
        if(Usuarios.containsKey(usuario.getCpf())){
                    Usuarios.get(usuario.getCpf()).setNome(usuario.getNome());
                    Usuarios.get(usuario.getCpf()).setEndereco(usuario.getEndereco());
                    return;

        }
        Usuarios.put(usuario.getCpf(), usuario);
    }

    /**
     * Retorna um usuário previamente cadastrado, a partir do CPF informado.
     *
     * @param cpf o CPF do usuário desejado
     * @return o usuário, caso exista; ou null, caso não exista nenhum usuário cadastrado com aquele CPF
     */
    public Usuario getUsuario(long cpf) {
        if(Usuarios.containsKey(cpf)){
            return Usuarios.get(cpf);
        }
        return null;
    }

    /**
     * @return o número total de usuários cadastrados na biblioteca
     */
    public int getTotalDeUsuariosCadastrados() {
        return Usuarios.size();
    }

    /**
     * Adquire `quantidade` cópias do livro informado e as inclui no acervo da biblioteca.
     *
     * @param livro o livro sendo adquirido
     * @param quantidade o número de cópias do livro sendo adquiridas
     */
    public void incluirLivroNoAcervo(Livro livro, int quantidade) {
        if(Acervo.contains(livro)){
            livro.setQuantidade(livro.getQuantidade()+quantidade);
            this.TotalLivros += quantidade;
            return;
        }
        livro.setQuantidade(quantidade);
        Acervo.add(livro);
        this.TotalLivros += quantidade;
    }

    /**
     * Empresta um livro para um usuário da biblioteca.
     *
     * @param livro o livro a ser emprestado
     * @param usuario o usuário que está pegando emprestado o livro
     *
     * @return true, se o empréstimo foi bem-sucedido;
     *         false, se o livro não está disponível para empréstimo
     *
     *         (IMPORTANTE: um livro é considerado disponível para empréstimo se há pelo menos
     *                      controle.Biblioteca.MIN_COPIAS_PARA_PODER_EMPRESTAR cópias daquele livro nas estantes da biblioteca;
     *                      isto é, as estantes da biblioteca jamais poderão ficar com menos do que
     *                      controle.Biblioteca.MIN_COPIAS_PARA_PODER_EMPRESTAR-1 cópias de qualquer livro de seu acervo)
     *
     * @throws UsuarioNaoCadastradoException se o usuário informado não está cadastrado na biblioteca
     * @throws LimiteEmprestimosExcedidoException se o usuário já está com muitos livros emprestados no momento
     */
    public boolean emprestarLivro(Livro livro, Usuario usuario)
            throws UsuarioNaoCadastradoException, LimiteEmprestimosExcedidoException {
        if(Usuarios.containsValue(usuario)){
            if(Acervo.contains(livro)){
                if(Usuarios.get(usuario.getCpf()).getQntLivros()<MAX_LIVROS_DEVIDOS){
                    if(livro.getQuantidade()>=MIN_COPIAS_PARA_PODER_EMPRESTAR){
                        Usuarios.get(usuario.getCpf()).emprestarLivro(livro);
                        livro.setQuantidade(livro.getQuantidade() - 1);
                        this.TotalLivros -= 1;
                        return true;
                    }
                    return false;
                }
                throw new LimiteEmprestimosExcedidoException();
            }
            return false;
        }
        throw new UsuarioNaoCadastradoException();
    }

    /**
     * Recebe de volta um livro que havia sido emprestado.
     *
     * @param livro o livro sendo devolvido
     * @param usuario o usuário que havia tomado emprestado aquele livro
     *
     * @throws DevolucaoInvalidaException se o livro informado não se encontra emprestado para o usuário informado
     */
    public void receberDevolucaoLivro(Livro livro, Usuario usuario) throws DevolucaoInvalidaException {
        if(Usuarios.get(usuario.getCpf()).possuiObjeto(livro)){
            Usuarios.get(usuario.getCpf()).devolverLivro(livro);
            livro.setQuantidade(livro.getQuantidade()+1);
            this.TotalLivros =+ 1;
            return;
        }
        throw new DevolucaoInvalidaException();
    }

    /**
     * Retorna a quantidade de livros emprestados ao usuário informado.
     *
     * @param usuario o usuário desejado
     * @return a quantidade de livros emprestados àquele usuário; caso o usuário não esteja devendo nenhum livro,
     *         ou não seja nem mesmo um usuário cadastrado na biblioteca, retorna zero, não deve nada
     */
    public int getQuantidadeDeLivrosDevidos(Usuario usuario) {
        return usuario.getQntLivros();
    }

    /**
     * @return a quantidade total de livros nas estantes da biblioteca (isto é, a soma das quantidades de cópias
     *         não-emprestadas de todos os livros do acervo da biblioteca).
     */
    public int getQuantidadeDeLivrosNaEstante() {
        return this.TotalLivros;
    }

    /**
     * Retorna o número de cópias do livro informado que existem na estante da biblioteca
     * (isto é, que não estão emprestados).
     *
     * @param livro o livro desejado
     * @return o número de cópias não-emprestadas daquele livro
     */
    public int getQuantidadeDeLivrosNaEstante(Livro livro) {
        return livro.getQuantidade();
    }
}