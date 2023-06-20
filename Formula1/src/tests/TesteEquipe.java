package tests;

import java.util.ArrayList;
import java.util.List;

import entities.Equipe;

public class TesteEquipe {

	public static void main(String[] args) {

//		Scanner sc = new Scanner(System.in);
		Equipe equipe = new Equipe();
//		int opcao, equipeId;
//		String nome, nacionalidade, nomeChefe;
//		boolean resultado;
		List<Integer> lista = new ArrayList<>();
		for (Integer id : equipe.getEquipeIdList()) {
			lista.add(id);
		}
		System.out.println(lista);
//
//		do {
//			System.out.println("1 - Cadastrar equipe");
//			System.out.println("2 - Consultar equipe");
//			System.out.println("3 - Atualizar equipe");
//			System.out.println("4 - Apagar equipe");
//			System.out.println("5 - Encerrar");
//			System.out.print("Entre com uma opção: ");
//			opcao = Integer.parseInt(sc.nextLine());
//			if (opcao < 1 || opcao > 5)
//				System.out.println("Opção incorreta!");
//			else {
//				if (opcao != 5) {
//					try {
//						System.out.print("Entre com a ID da Equipe: ");
//						equipeId = Integer.parseInt(sc.nextLine());
//						switch (opcao) {
//						case 1:
//							resultado = equipe.consultarEquipe(equipeId);
//							if (resultado)
//								System.out.println("Equipe já cadastrada!");
//							else {
//								System.out.print("Entre com o nome da equipe: ");
//								nome = sc.nextLine();
//								System.out.print("Entre com a nacionalidade da equipe: ");
//								nacionalidade = sc.nextLine();
//								System.out.print("Entre com o nome do chefe de equipe: ");
//								nomeChefe = sc.nextLine();
//								equipe.cadastrarEquipe(equipeId, nome, nacionalidade, nomeChefe);
//							}
//							break;
//						case 2:
//							resultado = equipe.consultarEquipe(equipeId);
//							if (resultado) {
//								System.out.println("ID: " + equipe.getIdEquipe());
//								System.out.println("Nome: " + equipe.getNome());
//								System.out.println("Nacionalidade: " + equipe.getNacionalidade());
//								System.out.println("Nome do chefe: " + equipe.getChefe());
//							}
//							break;
//						case 3:
//							resultado = equipe.consultarEquipe(equipeId);
//							if (resultado) {
//								System.out.print("Entre com o nome da equipe: ");
//								nome = sc.nextLine();
//								System.out.print("Entre com a nacionalidade da equipe: ");
//								nacionalidade = sc.nextLine();
//								System.out.print("Entre com o nome do chefe de equipe: ");
//								nomeChefe = sc.nextLine();
//								if (!equipe.atualizarEquipe(equipeId, nome, nacionalidade, nomeChefe))
//									System.out.println("Erro na atualização da equipe!");
//							}
//							break;
//						case 4:
//							resultado = equipe.consultarEquipe(equipeId);
//							if (resultado) {
//								System.out.println("ID: " + equipe.getIdEquipe());
//								System.out.println("Nome: " + equipe.getNome());
//								System.out.println("País: " + equipe.getNacionalidade());
//								System.out.println("Chefe: " + equipe.getChefe());
//								System.out.print("Tem certeza que quer apagar a equipe (s/n)? ");
//								char decisao = sc.nextLine().charAt(0);
//								if (decisao == 's') {
//									if (!equipe.apagarEquipe(equipeId)) {
//										System.out.println("Erro, equipe não apagada");
//									}
//
//								} else {
//									System.out.println("Operação cancelada!");
//								}
//							}
//							break;
//						}
//					} catch (NumberFormatException erro) {
//						System.out.println("Entre com o formato correto dos dados!");
//					} catch (Exception erro) {
//						System.out.println("Erro não identificado: " + erro.toString());
//					}
//				}
//
//			}
//			System.out.println();
//		} while (opcao != 5);
//		System.out.println("Programa encerrado!");
//		sc.close();
		
		
	}

}
