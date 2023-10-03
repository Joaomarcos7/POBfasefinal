package daodb4o;

import java.util.List;

import com.db4o.query.Query;

import modelo.Plano;

public class DAOPlano extends DAO<Plano>{

	public Plano read (Object chave) {
		String nome = (String) chave;
		Query q = manager.query();
		q.descend("nome").constrain(nome);
		
		List<Plano> planos = q.execute();
		if (planos.size() > 0) {
			return planos.get(0);
		} else {
			return null;
		}
	}
	
	public void create(Plano obj) {
		manager.store(obj);
	}
}
