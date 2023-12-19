package daojpa;

import java.util.List;

import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import modelo.Atendimento;

public class DAOAtendimento extends DAO<Atendimento>{
	
	public Atendimento read (Object chave){
		try{
			int id = (int) chave;
			TypedQuery<Atendimento> q = manager.createQuery("select a from Atendimento a where a.id = :n ",Atendimento.class);
			q.setParameter("n", id);

			return q.getSingleResult();
		}catch(NoResultException e){
			return null;
		}
	}

	public List<Atendimento> readAll(){
		TypedQuery<Atendimento> q = manager.createQuery("select a from Atendimento a LEFT JOIN FETCH a.paciente JOIN FETCH a.plano order by a.id", Atendimento.class);
		return  q.getResultList();
	}


	//--------------------------------------------
	//  consultas
	//--------------------------------------------

	public List<Atendimento> AtendimentosdeN(String CPF){
		//alugueis contendo carro de modelo 'palio'
		TypedQuery<Atendimento> q = manager.createQuery("select a from Atendimento a where a.paciente.CPF = :x", Atendimento.class);
		q.setParameter("x", CPF);
		
		return  q.getResultList();
	}

	
	public List<Atendimento> AtendimentosDataN(String Data){
		TypedQuery<Atendimento> q = manager.createQuery("select a from Atendimento a where a.data = :data", Atendimento.class);
		q.setParameter("data", Data);
		return  q.getResultList();
	}

}
