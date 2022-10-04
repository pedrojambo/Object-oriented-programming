import java.util.ArrayList;
import java.util.HashMap;

/**
 * Esta classe implementa um álbum (de figurinhas, selos, etc.) online, permitindo
 * colecionar itens que possuem uma posição específica no álbum.
 *
 * Itens são acrescentados ao álbum por meio de "pacotinhos" contendo uma quantidade
 * fixa, pré-feterminada de itens.
 *
 * Itens já existentes no álbum e recebidos novamente em pacotinhos posteriores são
 * armazenados para eventuais trocas com outro usuários.
 *
 * @param <T> o tipo de objeto colecionável que o álbum irá armazenar
 */
public class Album<T extends Colecionavel> {

    /**
     * Construtor.
     *
     * @param tamanhoDoAlbum O tamanho do álbum sendo criado (note que os itens que serão colecionados
     *                       terão posições entre 1 e o tamanho do álbum)
     *
     * @param quantItensPorPacotinho A quantidade de itens em cada pacotinho adquirido para este álbum
     */
    int tamanhoAlbum;
    int quantItensPorPacotinho;
    HashMap<Integer, T> figurinhasColadas;
    HashMap<Integer, T> figurinhasGuardadas;
    ArrayList<Colecionavel> totalFigurinhasGuardadas;

    public Album(int tamanhoDoAlbum, int quantItensPorPacotinho) {
        this.quantItensPorPacotinho = quantItensPorPacotinho;
        this.tamanhoAlbum = tamanhoDoAlbum;
        figurinhasColadas = new HashMap<>();
        figurinhasGuardadas = new HashMap<>();
        totalFigurinhasGuardadas = new ArrayList();
    }

    /**
     * Recebe novos itens que serão imediatamente "colados" ao álbum, se inéditos,
     * ou guardados para troca, se repetidos.
     *
     * @param pacotinho Um pacotinho de itens, que poderão ser inéditos ou repetidos
     *
     * @throws PacotinhoInvalidoException se o pacotinho contiver uma quantidade errada de itens,
     *                                    ou se contiver algum item que não pertença ao álbum
     *                                    (por indicar uma posição menor que 1 ou maior que seu tamanho)
     */
    public void receberNovoPacotinho(Pacotinho<T> pacotinho) throws PacotinhoInvalidoException {
        if(pacotinho.size() != this.quantItensPorPacotinho){
            throw new PacotinhoInvalidoException();
        }

        if(pacotinho.contains(0)){
            throw  new PacotinhoInvalidoException();
        }

        for(int i = 0; i < pacotinho.size(); i++) {
            if (pacotinho.get(i).getPosicao() > tamanhoAlbum) {
                throw new PacotinhoInvalidoException();
            }
        }
        for(int i = 0; i < pacotinho.size(); i++) {
            if(figurinhasColadas.containsKey(pacotinho.get(i).getPosicao())){
                figurinhasGuardadas.put(pacotinho.get(i).getPosicao(), pacotinho.get(i));
            }
            figurinhasColadas.put(pacotinho.get(i).getPosicao(), pacotinho.get(i));
            totalFigurinhasGuardadas.add(pacotinho.get(i));
        }
    }

    /**
     * @return a quantidade total de figurinhas que este álbum apresenta quando se encontra completo
     */
    public int getTamanho() {
        return this.tamanhoAlbum;
    }

    /**
     * @return a quantidade total de itens que estão "colados" no álbum,
     * isto é, o total de itens distintos que foram recebidos até o momento
     */
    public int getQuantItensColados() {
        return figurinhasColadas.size();
    }

    /**
     * @return o total de itens repetidos que foram acumulados até o momento
     */
    public int getQuantItensRepetidos() {
        return figurinhasGuardadas.size();
    }

    public boolean possuiItemColado(int posicao) {
        if (figurinhasColadas.containsKey(posicao)){
            return true;
        }
        return false;
    }

    public boolean possuiItemRepetido(int posicao) {
        if (figurinhasGuardadas.containsKey(posicao)){
            return true;
        }
        return false;
    }

    /**
     * @return a quantidade de itens que faltam para o álbum ficar completo
     */
    public int getQuantItensFaltantes() {
        return tamanhoAlbum-figurinhasColadas.size();
    }

    /**
     * @param posicao a posição do iten desejado
     * @return o item que está colado na posição especificada, se houver; null, caso contrário
     */
    public T getItemColado(int posicao) {
        if(figurinhasColadas.containsKey(posicao)){
            return (T) figurinhasColadas.get(posicao);
        }
        return null;
    }
}