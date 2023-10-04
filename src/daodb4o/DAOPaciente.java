package daodb4o;

import java.util.List;

import com.db4o.query.Candidate;
import com.db4o.query.Evaluation;
import com.db4o.query.Query;

import modelo.Paciente;

public class DAOPaciente extends DAO<Paciente>{

	public Paciente read (Object chave) {
		String cpf = (String) chave;
		Query q = manager.query();
		q.constrain(Paciente.class);
		q.descend("CPF").constrain(cpf);
		
		List<Paciente> pacientes = q.execute();
		if (pacientes.size() > 0) {
			return pacientes.get(0);
		} else {
			return null;
		}
	}
	
	public void create(Paciente obj) {
		manager.store(obj);
	}
	
	
	public List<Paciente> PacienteMaisConsultas() {
		Query q = manager.query();
		q.constrain(Paciente.class);
		q.constrain(new Filtro());
		
		List<Paciente> pacientes =q.execute();
		return pacientes;
	}
	
	
	
	
	private  class Filtro implements Evaluation {
		public void evaluate(Candidate candidate) {
			//obter cada objeto da classe Pessoa que esta no banco
			Paciente p = (Paciente) candidate.getObject(); 
			
			if(p.getAtendimentos().size()>1) 
				candidate.include(true); 	//incluir objeto no resultado da consulta
			else		
				candidate.include(false);	//excluir objeto do resultado da consulta
			}
		}
}


