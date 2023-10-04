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

	public void remover(int id) throws Exception {
		if(!lista.isEmpty()) {
		for(Atendimento atendimento : lista) {
			if(atendimento.getId()==id) {
				boolean hasit=this.lista.remove(atendimento);
				if(!hasit) {
					throw new Exception("Não tem este atendimento na lista ");
					}
				}
			}
		}
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