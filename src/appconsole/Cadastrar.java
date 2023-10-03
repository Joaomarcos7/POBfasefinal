/**********************************
 * IFPB - Curso Superior de Tec. em Sist. para Internet
 * POB - Persistencia de Objetos
 * Prof. Fausto Ayres
 *
 */

package appconsole;

import regras_negocio.Fachada;

public class Cadastrar {

	public Cadastrar() {
		try {
			Fachada.inicializar();
			System.out.println("cadastrando paciente...");
			Fachada.cadastrarPaciente("14059714445", "João");
			Fachada.cadastrarPaciente("14621459752", "Clodoaldo");
			Fachada.cadastrarPaciente("14059765432", "Maria");
			Fachada.cadastrarPaciente("14059731137", "Leticia");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		try {
			System.out.println("cadastrando plano...");
			Fachada.CriarPlano("Premium");
			Fachada.CriarPlano("Básico");
			Fachada.CriarPlano("Promocional");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		try {
			System.out.println("cadastrando atendimento...");
			Fachada.CriarAtendimento(Fachada.localizarPaciente("14059714445"),"03-10-2023",Fachada.localizarPlano("Premium"));
			Fachada.CriarAtendimento(Fachada.localizarPaciente("14059714531"),"03-10-2023",Fachada.localizarPlano("Básico"));
			Fachada.CriarAtendimento(Fachada.localizarPaciente("14059731137"),"03-10-2023",Fachada.localizarPlano("Premium"));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		Fachada.finalizar();
		System.out.println("\nfim do programa !");
	}


	public static void main(String[] args) {
		new Cadastrar();
	}
}
