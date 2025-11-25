package Model;

public class Admin extends Usuario {
    private String ultimoAcesso;
    private int nivelAcesso;

    public Admin() {}

    public Admin(String CPF, String usuario, String senha, String nome,
                 String ultimoAcesso, int nivelAcesso) {
        super(CPF, usuario, senha, nome);
        this.ultimoAcesso = ultimoAcesso;
        this.nivelAcesso = nivelAcesso;
    }

    public String getUltimoAcesso() { return ultimoAcesso; }
    public void setUltimoAcesso(String ultimoAcesso) { this.ultimoAcesso = ultimoAcesso; }

    public int getNivelAcesso() { return nivelAcesso; }
    public void setNivelAcesso(int nivelAcesso) { this.nivelAcesso = nivelAcesso; }
}
