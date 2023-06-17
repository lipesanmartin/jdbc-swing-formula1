package tests;

import java.util.Scanner;

import entities.Piloto;

public class TestePiloto {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		Piloto piloto = new Piloto();
		int opcao, pilotoId, numCarro;
		String nome, nacionalidade;
		boolean resultado;

		do {
			System.out.println("1 - Cadastrar piloto");
			System.out.println("2 - Consultar piloto");
			System.out.println("3 - Atualizar numero do carro");
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
						switch (opcao) {
						case 1:
							resultado = piloto.consultarPiloto(pilotoId);
							if (resultado)
								System.out.println("Piloto já cadastrado!");
							else {
								System.out.print("Entre com o nome do piloto: ");
								nome = sc.nextLine();
								System.out.print("Entre com a nacionalidade do piloto: ");
								nacionalidade = sc.nextLine();
								System.out.print("Entre com o numero do carro do piloto: ");
								numCarro = Integer.parseInt(sc.nextLine());
								piloto.cadastrarPiloto(pilotoId, nome, nacionalidade, numCarro);
							}
							break;
						case 2:
							resultado = piloto.consultarPiloto(pilotoId);
							if (resultado) {
								System.out.println("ID: " + piloto.getIdPiloto());
								System.out.println("Nome: " + piloto.getNome());
								System.out.println("Nacionalidade: " + piloto.getNacionalidade());
								System.out.println("Número do carro: " + piloto.getNumCarro());
							}
							break;
						case 3:
							resultado = piloto.consultarPiloto(pilotoId);
							if (resultado) {
								System.out.println("ID: " + piloto.getIdPiloto());
								System.out.println("Nome: " + piloto.getNome());
								System.out.println("Número do carro: " + piloto.getNumCarro());
								System.out.print("Entre com o novo numero do carro: ");
								numCarro = Integer.parseInt(sc.nextLine());
								if (!piloto.atualizarNumCarro(pilotoId, numCarro))
									System.out.println("Erro na atualização do numero do carro!");
							}
							break;
						case 4:
							resultado = piloto.consultarPiloto(pilotoId);
							if (resultado) {
								System.out.println("ID: " + piloto.getIdPiloto());
								System.out.println("Nome: " + piloto.getNome());
								System.out.println("Nacionalidade: " + piloto.getNacionalidade());
								System.out.println("Número do carro: " + piloto.getNumCarro());
								System.out.print("Tem certeza que quer apagar o piloto (s/n)? ");
								char decisao = sc.nextLine().charAt(0);
								if (decisao == 's') {
									if (!piloto.apagarPiloto(pilotoId)) {
										System.out.println("Erro, piloto não apagado");
									}

								} else {
									System.out.println("Operação cancelada!");
								}
							}
							break;
						}
					} catch (NumberFormatException erro) {
						System.out.println("Entre com o formato correto dos dados!");
					} catch (Exception erro) {
						System.out.println("Erro não identificado: " + erro.toString());
					}
				}

			}
			System.out.println();
		} while (opcao != 5);
		System.out.println("Programa encerrado!");
		sc.close();
	}
}
