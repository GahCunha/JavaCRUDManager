import java.io.Serial;
import java.io.Serializable;

public class Usuario implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    private String nome;
    private String email;
    private String senha;

    public Usuario(String nome, String email, String senha) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return " nome:" + nome + " email:" + email + " senha:" + senha;
    }

}
