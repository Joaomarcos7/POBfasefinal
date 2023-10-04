package regras_negocio;
/**********************************
 * IFPB - Curso Superior de Tec. em Sist. para Internet
 * POB - Persistencia de Objetos
 * Prof. Fausto Ayres
 *
 */

import java.util.List;


import daodb4o.DAO;
import daodb4o.DAOAtendimento;
import daodb4o.DAOPaciente;
import daodb4o.DAOPlano;
import daodb4o.DAOUsuario;
import modelo.*;

public class Fachada {
	private Fachada() {}
	private static DAOUsuario daousuario = new DAOUsuario();
	private static DAOPlano daoplano = new DAOPlano();
	private static DAOPaciente daopaciente = new DAOPaciente();
	private static DAOAtendimento daoatendimento = new DAOAtendimento();
	public static Usuario logado;	//contem o objeto Usuario logado em TelaLogin.java

	public static void inicializar(){
		DAO.open();
	}
	public static void finalizar(){
		DAO.close();
	}


	public static Paciente cadastrarPaciente(String CPF, String Nome) throws Exception{
		DAO.begin();
		Paciente paciente = daopaciente.read(CPF);
		if (paciente!=null) {
			throw new Exception("paciente ja cadastrado:" + Nome);
		}
		paciente = new Paciente(CPF,Nome);

		daopaciente.create(paciente);
		DAO.commit();
		return paciente;
	}

	public static Atendimento CriarAtendimento(String CPF, String data, String nomeplano) throws Exception{
		DAO.begin();
		Paciente paciente= daopaciente.read(CPF);
		Plano plano= daoplano.read(nomeplano);
		if(paciente==null) 
			throw new Exception ("paciente não existe no sistema!");
		if(plano==null) 
			throw new Exception ("plano não existe no sistema!");
		if(!paciente.getAtendimentos().isEmpty()) {
		for(Atendimento atendimento: paciente.getAtendimentos()) {
			if(atendimento.getData().equals(data)) {
				throw new Exception("Paciente não pode ter mais de 1 consulta no dia");
				}
			}
		}
		Atendimento atendimento = new Atendimento(data,paciente,plano);
		plano.adicionar(atendimento);
		paciente.adicionar(atendimento);

		daoatendimento.create(atendimento);
		daopaciente.update(paciente);
		daoplano.update(plano);
		DAO.commit();
		return atendimento;
	} //paciente so pode ter 1 consulta por dia

	public static void CancelarAtendimento(int id) throws Exception{
		DAO.begin();
		Atendimento atendimento =  daoatendimento.read(id);
		if(atendimento==null) 
			throw new Exception ("Atendimento não existe para ser cancelado!");

		if(atendimento.getPaciente()==null) 
			throw new Exception ("Atendimento não possui atendimento relacionado");
		if(atendimento.getPlano()==null) 
			throw new Exception ("Atendimento não possui plano relacionado");
	
		Paciente paciente = daopaciente.read(atendimento.getPaciente().getCPF());
		Plano plano= daoplano.read(atendimento.getPlano().getNome());
		
		
		daoatendimento.delete(atendimento);
		paciente.remover(id);
		plano.remover(id);
		
		daopaciente.update(paciente);
		daoplano.update(plano);
		DAO.commit();
	}

	public static void excluirPaciente(String CPF) throws Exception{
		DAO.begin();
		Paciente paciente =daopaciente.read(CPF);
		if(paciente==null) 
			throw new Exception ("Paciente desse cpf requerido não existe :" + CPF);
		
		if(paciente.getAtendimentos().isEmpty()) { //caso paciente n esteja nenhum atendimento marcado
			daopaciente.delete(paciente);
			DAO.commit();
		}
		else {
			for(Atendimento atendimento : paciente.getAtendimentos()) {
				atendimento.setPaciente(null); //atendimentos continuam existindo no sistema, mas tem paciente null
			}
			daopaciente.delete(paciente);
			DAO.commit();
		}
	}

	public static void MudarPlano(int id,String nome) throws Exception{
		DAO.begin();
		Atendimento atendimento = daoatendimento.read(id);
		Plano plano=daoplano.read(nome);
		if (atendimento==null)
			throw new Exception("Atendimento  não existe : " + id);
		if(plano==null) 
			throw new Exception("Plano não definido! : " + nome );
		if(atendimento.getPlano().getNome().equals(nome))
			throw new Exception("O plano já é igual ao que ta : "+ nome);
		
		Plano planoanterior = atendimento.getPlano();
		atendimento.setPlano(plano);
		planoanterior.remover(id);
		plano.adicionar(atendimento);
		
		DAO.commit();
	}
	public static Plano CriarPlano(String nome) throws Exception{
		DAO.begin();
		Plano plano = daoplano.read(nome);
		
		if (plano == null) {
			// O plano não existe no banco de dados
			plano = new Plano(nome);
			daoplano.create(plano);
		} else {
			// O plano já existe no banco de dados
			throw new Exception ("Plano já criado " + nome);
		}
		
		DAO.commit();
		return plano;
	}

	public static void excluirPlano(String nome) throws Exception{
		DAO.begin();
		Plano plano= daoplano.read(nome);
		if(plano==null)
			throw new Exception("Plano não existe :" + nome);
		
		for(Atendimento atendimento : plano.getAtendimentos() ) {
			atendimento.setPlano(null);
		}
		daoplano.delete(plano);
		DAO.commit();
	}

	public static List<Plano>  listarPlanos(){
		DAO.begin();
		List<Plano> resultados=  daoplano.readAll();
		DAO.commit();
		return resultados;
	} 

	public static List<Paciente>  listarPacientes(){
		DAO.begin();
		List<Paciente> resultados =  daopaciente.readAll();
		DAO.commit();
		return resultados;
	}

	public static List<Atendimento> listarAtendimentos(){
		DAO.begin();
		List<Atendimento> resultados =  daoatendimento.readAll();
		DAO.commit();
		return resultados;
	}

	public static List<Usuario>  listarUsuarios(){
		DAO.begin();
		List<Usuario> resultados =  daousuario.readAll();
		DAO.commit();
		return resultados;
	} 

	////////////////// CRIAR QUERY ESPECIFICAS ///////////////////////////////////
	
	public static List<Paciente> PacienteSemConsulta(){ //pacientes com nenhum atendimento agendado
		DAO.begin();
		List<Paciente> pacientes =daopaciente.PacientesSemConsulta();
		DAO.commit();
		return pacientes;
	}
	
	
	public static List<Atendimento> AtendimentosdeN(String CPF){
		DAO.begin();
		List<Atendimento> atendimentos= daoatendimento.AtendimentosdeN(CPF);
		DAO.commit();
		return atendimentos;
	}
	
	public static List<Atendimento> AtendimentosDataN(String Data){
		DAO.begin();
		List<Atendimento> atendimentos= daoatendimento.AtendimentosDataN(Data);
		DAO.commit();
		return atendimentos;
	}
	
	
	////////////////////////////////////////////////////////////////////////////

	public static Paciente localizarPaciente(String CPF){
		return daopaciente.read(CPF);
	}
	public static Atendimento localizarAtendimento(int id){
		return daoatendimento.read(id);
	}
	public static Plano localizarPlano(String nome){
		return daoplano.read(nome);
	}
	
	
	
	//------------------Usuario------------------------------------
	public static Usuario cadastrarUsuario(String nome, String senha) throws Exception{
		DAO.begin();
		Usuario usu = daousuario.read(nome);
		if (usu!=null)
			throw new Exception("Usuario ja cadastrado:" + nome);
		usu = new Usuario(nome, senha);

		daousuario.create(usu);
		DAO.commit();
		return usu;
	}
	public static Usuario localizarUsuario(String nome, String senha) {
		Usuario usu = daousuario.read(nome);
		if (usu==null)
			return null;
		if (! usu.getSenha().equals(senha))
			return null;
		return usu;
	}
}
