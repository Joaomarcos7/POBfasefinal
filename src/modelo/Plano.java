package modelo;

import java.util.ArrayList;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
@Entity
@Table(name="tb_plano")
public class Plano {
	@Id
	String nome;
	@OneToMany(mappedBy="plano",cascade={CascadeType.PERSIST, CascadeType.MERGE},
			fetch=FetchType.LAZY)
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