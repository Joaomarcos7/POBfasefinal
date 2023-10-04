/**********************************
 * IFPB - Curso Superior de Tec. em Sist. para Internet
 * POB - Persistencia de Objetos
 * Prof. Fausto Ayres
 *
 */

package appconsole;
import modelo.Atendimento;
import modelo.Paciente;
import regras_negocio.Fachada;

public class Consultar {

	public Consultar() {
		try {
			Fachada.inicializar();

			System.out.println("consultas... \n");
			System.out.println("Consultar Pacientes com mais de 1 Atendimento agendados!");
			for(Paciente p : Fachada.PacienteMaisConsultas())
				System.out.println(p);


			System.out.println("Consulta Atendimentos do Paciente de CPF 14059714445");
			for(Atendimento a : Fachada.AtendimentosdeN("14059714445"))
				System.out.println(a);


			System.out.println("Consulta Atendimentos marcados na data de hoje 03-10-2023");
			for(Atendimento at : Fachada.AtendimentosDataN("03-06-2023"))
				System.out.println(at);


			//System.out.println("clientes que possuem 2 alugueis");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		Fachada.finalizar();
		System.out.println("\nfim do programa !");
	}

	public static void main(String[] args) {
		new Consultar();
	}
}

