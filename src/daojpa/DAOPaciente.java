package daojpa;

import java.util.List;

import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import modelo.Paciente;

public class DAOPaciente extends DAO<Paciente> {
	public Paciente read(Object chave) {
		try {
			String cpf = (String) chave;
			TypedQuery<Paciente> q = manager.createQuery("select c from Paciente c where c.CPF=:n", Paciente.class);
			q.setParameter("n", cpf);
			Paciente p = q.getSingleResult();
			return p;
		} catch (NoResultException e) {
			return null;
		}
	}
	
	public List<Paciente> readAll(){
		TypedQuery<Paciente> query = manager.createQuery("select c from Paciente c LEFT JOIN FETCH c.atendimentos order by c.CPF",Paciente.class);
		return  query.getResultList();
	}
	
	// --------------------------------------------
	// consultas
	// --------------------------------------------

	public List<Paciente> ConsultaPacienteAtendimentos() {
		// cliestes com 3 alugueis
		TypedQuery<Paciente> q = manager.createQuery("select c from Paciente c where size(c.atendimentos) >1", Paciente.class);
		return q.getResultList();
	}
}
