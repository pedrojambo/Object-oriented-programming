import java.util.ArrayList;

public class Jogador{

    public String username;
    private String senha;
    public int pontuação;
    public ArrayList<Partida> histórico;
    public boolean status;
    public boolean atividade;

    public Jogador(String user,String password){
        this.username = user;
        this.senha = password;
        this.pontuação = 1000;
        this.histórico = new ArrayList<>();
        this.status = false;
        this.atividade = false;
    }

    public String getUsername(){
        return this.username;
    }
    public String getSenha(){
        return this.senha;
    }
    public int getPontuação(){
        return this.pontuação;
    }
    public void setPontuação(String result){
        if(result == "win"){
            this.pontuação += 1;
        }
        if(result == "lose"){
            this.pontuação -= 1;
        }
    }
    public ArrayList getHistórico(){
        return this.histórico;
    }
    public void addHistórico(Partida partida){
        this.histórico.add(partida);
    }
    public boolean getStatus(){
        return status;
    }
    public boolean setStatus(boolean status){
        this.status = status;
        return status;
    }
    public boolean getAtividade(){
        return this.atividade;
    }
    public boolean setAtividade(boolean atividade){
        this.atividade = atividade;
        return atividade;
    }

    @Override
    public String toString(){
        return  ("User: " + this.username + " | ") +
                ("Senha: " + this.senha+ " | ") +
                ("Online: " + this.status+ " | ") +
                ("Em partida: " + this.atividade + "\n");
    }
}
