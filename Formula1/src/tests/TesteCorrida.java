//package tests;
//
//import java.util.Scanner;
//
//import entities.Corrida;
//
//public class TesteCorrida {
//
//	public static void main(String[] args) {
//
//		Scanner sc = new Scanner(System.in);
//		Corrida corrida = new Corrida();
//		int opcao, corridaId, vencedorId;
//		String nome, local;
//		boolean resultado;
//
//		do {
//			System.out.println("1 - Cadastrar corrida");
//			System.out.println("2 - Consultar corrida");
//			System.out.println("3 - Atualizar vencedor");
//			System.out.println("4 - Apagar corrida");
//			System.out.println("5 - Encerrar");
//			System.out.print("Entre com uma opção: ");
//			opcao = Integer.parseInt(sc.nextLine());
//			if (opcao < 1 || opcao > 5)
//				System.out.println("Opção incorreta!");
//			else {
//				if (opcao != 5) {
//					try {
//						System.out.print("Entre com a ID da Corrida: ");
//						corridaId = Integer.parseInt(sc.nextLine());
//						switch (opcao) {
//						case 1:
//							resultado = corrida.consultarCorrida(corridaId);
//							if (resultado)
//								System.out.println("Corrida já cadastrada!");
//							else {
//								System.out.print("Entre com o nome da corrida: ");
//								nome = sc.nextLine();
//								System.out.print("Entre com o local da corrida: ");
//								local = sc.nextLine();
//								System.out.print("Entre com o Id do vencedor: ");
//								vencedorId = Integer.parseInt(sc.nextLine());
//								corrida.cadastrarCorrida(corridaId, nome, local, vencedorId);
//							}
//							break;
//						case 2:
//							resultado = corrida.consultarCorrida(corridaId);
//							if (resultado) {
//								System.out.println("ID: " + corrida.getIdCorrida());
//								System.out.println("Nome: " + corrida.getNome());
//								System.out.println("Local: " + corrida.getLocal());
//								System.out.println("Vencedor: " + corrida.getVencedor());
//							}
//							break;
//						case 3:
//							resultado = corrida.consultarCorrida(corridaId);
//							if (resultado) {
//								System.out.println("ID: " + corrida.getIdCorrida());
//								System.out.println("Nome: " + corrida.getNome());
//								System.out.println("Numero de voltas: " + corrida.getVencedor());
//								System.out.print("Entre com o novo numero de voltas: ");
//								vencedorId = Integer.parseInt(sc.nextLine());
//								if (!corrida.atualizarVencedor(corridaId, vencedorId))
//									System.out.println("Erro na atualização do vencedor! Cadastre o piloto primeiro!");
//							}
//							break;
//						case 4:
//							resultado = corrida.consultarCorrida(corridaId);
//							if (resultado) {
//								System.out.println("ID: " + corrida.getIdCorrida());
//								System.out.println("Nome: " + corrida.getNome());
//								System.out.println("Local: " + corrida.getLocal());
//								System.out.println("Vencedor: " + corrida.getVencedor());
//								System.out.print("Tem certeza que quer apagar a corrida (s/n)? ");
//								char decisao = sc.nextLine().charAt(0);
//								if (decisao == 's') {
//									if (!corrida.apagarCorrida(corridaId)) {
//										System.out.println("Erro, corrida não apagada");
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
//	}
//}
