package daodb4o;

import java.util.List;

import com.db4o.query.Query;

import modelo.Atendimento;

public class DAOAtendimento extends DAO<Atendimento>{

	public Atendimento read (Object chave) {
		int id = (int) chave;
		Query q = manager.query();
		q.constrain(Atendimento.class);
		q.descend("id").constrain(id);
		
		List<Atendimento> atendimentos = q.execute();
		if (atendimentos.size() > 0) {
			return atendimentos.get(0);
		} else {
			return null;
		}
	}
	
	public void create(Atendimento obj) {
		int novoId = super.gerarId();
		obj.setId(novoId);
		manager.store(obj);
	}
	
	public List<Atendimento> AtendimentosdeN(String CPF){
		Query q=manager.query();
		q.constrain(Atendimento.class);
		
		q.descend("paciente").descend("CPF").constrain(CPF);
		List<Atendimento> atendimentos = q.execute();
		return atendimentos;
	}
	
	public List<Atendimento> AtendimentosDataN(String Data){
		Query q=manager.query();
		q.constrain(Atendimento.class);
		q.descend("data").constrain("Data");
		
		List<Atendimento> atendimentos= q.execute();
		return atendimentos;
	}
	
	
}
