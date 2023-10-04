package modelo;

import java.util.ArrayList;

public class Plano {

	String nome;
	ArrayList<Atendimento> lista = new ArrayList<Atendimento>();

	public Plano(String nome) {
		this.nome=nome;
	}
	
	public String getNome() {
		return this.nome;
	}
	
	public void setNome(String nome) {
		this.nome=nome;
	}
	
	public void adicionar(Atendimento a){
		lista.add(a);
	}

	public void remover(Atendimento a) throws Exception {
		this.lista.remove(a);
}
	
	public ArrayList<Atendimento> getAtendimentos(){
		return this.lista;
	}
	@Override
	public String toString() {
		return "Plano [nome=" + nome + ", lista=" + lista.toString() + "]";
	}
	
	public String ToStringPattern() {
		return "Plano [  " +  "Nome=" + this.nome + "]";
	}
	
}