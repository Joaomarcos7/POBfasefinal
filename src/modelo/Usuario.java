package modelo;

public class Usuario {
	
	private String nome;
	private String senha;
	
	public Usuario(String nome, String senha) {
		this.nome = nome;
		this.senha = senha;
	}
	
	public String getNome() {
		return this.nome;
	}
	
	public void setNome(String novoNome) {
		this.nome = novoNome;
	}
	
	public String getSenha() {
		return this.senha;
	}
	
	public void setSenha(String novaSenha) {
		this.senha = novaSenha;
	}
	
	@Override
	public String toString() {
		return "Usuario [nome = " + nome + "]" + "[senha = " + senha + "]";
	}
}
