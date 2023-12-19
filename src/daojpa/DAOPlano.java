package daojpa;

import java.util.List;

import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import modelo.Plano;

public class DAOPlano extends DAO<Plano> {
	
	public Plano read (Object chave){
		try{
			String nome = (String) chave;
			TypedQuery<Plano> q = manager.createQuery("select c from Plano c where c.nome=:nome",Plano.class);
			q.setParameter("nome", nome);
			Plano c =  q.getSingleResult();
			return c;
		}catch(NoResultException e){
			return null;
		}
	}

	public List<Plano> readAll(){
		TypedQuery<Plano> query = manager.createQuery("select c from Plano c LEFT JOIN FETCH c.atendimentos order by c.nome",Plano.class);
		return  query.getResultList();
	}
	
}
