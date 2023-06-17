package application;

import java.util.Scanner;

import entities.Piloto;

public class TestePiloto1 {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		Piloto piloto = new Piloto();
		int opcao, pilotoId, equipeId;
		String nome, nacionalidade;
		boolean resultado;

		do {
			System.out.println("1 - Cadastrar piloto");
			System.out.println("2 - Consultar piloto");
			System.out.println("3 - Atualizar equipe do piloto");
			System.out.println("4 - Apagar piloto");
			System.out.println("5 - Encerrar");
			System.out.print("Entre com uma opção: ");
			opcao = Integer.parseInt(sc.nextLine());
			if (opcao < 1 || opcao > 5)
				System.out.println("Opção incorreta!");
			else {
				if (opcao != 5) {
					try {
						System.out.print("Entre com o ID do Piloto: ");
						pilotoId = Integer.parseInt(sc.nextLine());
						System.out.print("Entre com o nome do piloto: ");
						nome = sc.nextLine();
						switch (opcao) {
						case 1:
							resultado = piloto.consultarPiloto(pilotoId, nome);
							if (resultado)
								System.out.println("Piloto já cadastrado!");
							else {
								System.out.print("Entre com a nacionalidade do piloto: ");
								nacionalidade = sc.nextLine();
								System.out.print("Entre com a ID de equipe do piloto: ");
								equipeId = Integer.parseInt(sc.nextLine());
								piloto.cadastrarPiloto(pilotoId, nome, nacionalidade, equipeId);
							}
							break;
						}
					} catch (NumberFormatException erro) {
						System.out.println("Entre com o formato correto dos dados!");
					} catch (Exception erro) {
						System.out.println("Erro n�o identificado: " + erro.toString());
					}
				}

			}
		} while (opcao != 5);
		System.out.println("Programa encerrado!");
		sc.close();
	}
}
