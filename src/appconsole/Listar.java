/**********************************
 * IFPB - Curso Superior de Tec. em Sist. para Internet
 * POB - Persistencia de Objetos
 * Prof. Fausto Ayres
 *
 */

package appconsole;

import daodb4o.Util;
import modelo.*;
import regras_negocio.Fachada;

public class Listar {

	public Listar() {
		try {
			Fachada.inicializar();
			System.out.println("\n---listagem de Atendimentos:");
			for(Atendimento a: Fachada.listarAtendimentos())
				System.out.println(a);

			System.out.println("\n---listagem de Pacientes:");
			for(Paciente p: Fachada.listarPacientes())
				System.out.println(p);
			
			System.out.println("\n---listagem de Planos:");
			for(Plano pl: Fachada.listarPlanos())
				System.out.println(pl);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		Util.desconectar();
		System.out.println("\nfim do programa !");
	}

	public static void main(String[] args) {
		new Listar();
	}
}
