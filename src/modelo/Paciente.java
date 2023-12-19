package modelo;

import java.util.ArrayList;
import java.util.TreeMap;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="tb_paciente")
public class Paciente {
	@Id
	String CPF;
	String Nome;
	@OneToMany(mappedBy="paciente",cascade={CascadeType.PERSIST, CascadeType.MERGE},
			fetch=FetchType.LAZY)
	ArrayList<Atendimento> lista= new ArrayList<>();
	
	public Paciente(String CPF,String Nome) {
		this.CPF=CPF;
		this.Nome=Nome;
	}
	
	public String getCPF() {
		return CPF;
	}
	
	public void setCPF(String cpf) {
		this.CPF=cpf;
	}
	
	public String getNome() {
		return Nome;
	}
	
	public void setNome(String Nome) {
		this.Nome=Nome;
	}
	
	public void adicionar(Atendimento a){
		this.lista.add(a);
	}
	
	public void remover(Atendimento atendimento) throws Exception {
		lista.remove(atendimento);
}
	public ArrayList<Atendimento> getAtendimentos(){
			return this.lista;
		}
	
	public void SetAtendimentos(ArrayList<Atendimento> lista ) {
			this.lista= lista;
		}

	@Override
	public String toString() {
		return "Paciente [CPF=" + CPF + ", Nome=" + Nome + ", lista=" + this.lista.toString() + "]";
	}
	
	public String ToStringPattern() {
		return "Paciente [CPF=" + CPF + ", Nome=" + Nome + "]";
	}	
}
	