package modelo;

import java.util.ArrayList;
import java.util.List;

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
	@OneToMany(mappedBy="plano",cascade={CascadeType.PERSIST, CascadeType.MERGE})
	List<Atendimento> atendimentos=new ArrayList<>();

	public Plano(String nome) {
		this.nome=nome;
	}
	
	public Plano() {}
	public String getNome() {
		return this.nome;
	}
	
	public void setNome(String nome) {
		this.nome=nome;
	}
	
	public void adicionar(Atendimento a){
		atendimentos.add(a);
	}

	public void remover(Atendimento a) throws Exception {
		this.atendimentos.remove(a);
}
	
	public List<Atendimento> getAtendimentos(){
		return this.atendimentos;
	}
	@Override
	public String toString() {
		return "Plano [nome=" + nome + ", atendimentos=" + atendimentos.toString() + "]";
	}
	
	public String ToStringPattern() {
		return "Plano [  " +  "Nome=" + this.nome + "]";
	}
	
}