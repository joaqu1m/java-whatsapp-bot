package manuall.dto;

public class Usuario {

    private String nome;
    private String email;
    private String senha;

    public Usuario() {
        this.nome = "Nome Padrão";
        this.email = "Email Padrão";
        this.senha = "Senha Padrão";
    }

    public Usuario(String nome, String email, String senha) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    public String getNome() {
        return this.nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return this.email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return this.senha;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }
}
