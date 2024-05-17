/* Curso Técnólogo de Análise e Desenvolvimento de Sistemas
* Nome do Desenvolvedor: Victor Fregni Gogorza
* Versão1.0
* 
* Projeto de Agenda de Contatos utilizando Java
*/

package agendaDeContatos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

class CapitalizarPrimeiraLetra {

	public static String capitalizarPrimeiraLetra(String nome) {
		if (nome == null || nome.isEmpty()) {
			return nome;
		} else {
			return nome.substring(0, 1).toUpperCase() + nome.substring(1).toLowerCase();
		}
	}
}

class ComparadorPorNome implements Comparator<Contato> {
	@Override
	public int compare(Contato contato1, Contato contato2) {
		return contato1.getNome().compareTo(contato2.getNome());
	}
}

class Contato {

	private String nome;
	private String sobrenome;
	private String telefone;
	private String email;
	private int dataDeNascimento;

	private String formatarTelefone(String telefone) {
		if (telefone.length() == 10) {
			return String.format("(%s) %s-%s", telefone.substring(0, 2), telefone.substring(2, 6),
					telefone.substring(6));
		} else if (telefone.length() == 11) {
			return String.format("(%s) %s-%s", telefone.substring(0, 2), telefone.substring(2, 7),
					telefone.substring(7));
		}
		return telefone;
	}

	public Contato(String nome, String sobrenome, String telefone, String email, int dataDeNascimento) {

		this.nome = nome;
		this.sobrenome = sobrenome;
		this.telefone = telefone;
		this.email = email;
		this.dataDeNascimento = dataDeNascimento;
	}

	public String getNome() {
		return nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public String getTelefone() {
		return telefone;
	}

	public String getEmail() {
		return email;
	}

	public int getDataDeNascimento() {
		return dataDeNascimento;
	}

	public void setNome(String setNome) {
		nome = setNome;
	}

	public void setSobrenome(String setSobrenome) {
		sobrenome = setSobrenome;
	}

	public void setTelefone(String setTelefone) {
		telefone = setTelefone;
	}

	public void setEmail(String setEmail) {
		email = setEmail;
	}

	public void setDataDeNascimento(int setDataDeNascimento) {
		dataDeNascimento = setDataDeNascimento;
	}

	@Override

	public String toString() {

		String dataFormatada = String.format("%02d/%02d/%04d", dataDeNascimento / 1000000,
				(dataDeNascimento / 10000) % 100, dataDeNascimento % 10000);
		String telefoneFormatado = formatarTelefone(telefone);
		String nomeCapitalizado = CapitalizarPrimeiraLetra.capitalizarPrimeiraLetra(nome);
		String sobrenomeCapitalizado = CapitalizarPrimeiraLetra.capitalizarPrimeiraLetra(sobrenome);

		return "Contato {" + "Nome - " + nomeCapitalizado + " " + sobrenomeCapitalizado + ", Telefone - "
				+ telefoneFormatado + ", E-mail - " + email + ", Data de Nascimento - " + dataFormatada + "}";
	}
}

public class AgendaContatos {

	public static void main(String[] args) {

		ArrayList<Contato> contatos = new ArrayList<>();
		Scanner sc = new Scanner(System.in);

		while (true) {

			System.out.println("Menu:");
			System.out.println("1. Adicionar contato");
			System.out.println("2. Mostrar todos os contatos");
			System.out.println("3. Editar um contato");
			System.out.println("4. Pesquisar um contato");
			System.out.println("5. Deletar um contato");
			System.out.println("6. Sair");
			System.out.print("Escolha uma opção: ");

			int opcao = sc.nextInt();
			System.out.println();

			if (opcao == 1) {
				System.out.print("Digite o Nome do novo contato: ");
				String nome = sc.next();

				System.out.print("Digite o Sobrenome do novo contato:");
				String sobrenome = sc.next();

				System.out.print("Digite o Telefone do novo contato, apenas números, no formato (00) 00000-0000: ");
				String telefone = sc.next();

				System.out.print("Digite o E-mail do novo contato: ");
				String email = sc.next();

				System.out
						.print("Digite a Data de Nascimento do novo contato, apenas números, no formato 00/00/0000: ");
				int dataDeNascimento = sc.nextInt();

				Contato contato = new Contato(nome, sobrenome, telefone, email, dataDeNascimento);
				contatos.add(contato);
				System.out.println("Contato adicionado com sucesso!");
				System.out.println();

			} else if (opcao == 2) {
				Collections.sort(contatos, new ComparadorPorNome());
				System.out.println("Contatos em ordem alfabética:");
				for (Contato contato : contatos) {
					System.out.println(contato);
				}
				System.out.println();

			} else if (opcao == 3) {
				System.out.println("Digite o nome do contato que deseja editar");
				String nomeContatoEditavel = sc.next();
				Contato contatoParaEditar = null;

				if (contatos.isEmpty()) {
					System.out.println("Ainda não há nenhum contato registrado!");
					System.out.println();
				} else {
					for (Contato contato : contatos) {
						if (contato.getNome().equals(nomeContatoEditavel)) {
							contatoParaEditar = contato;
							break;
						}
					}

					if (contatoParaEditar == null) {
						System.out.println("Não há nenhum contato com esse nome!");
						System.out.println();
					} else {
						System.out.println("E o quê você deseja editar?");
						System.out.println("1. Editar Nome");
						System.out.println("2. Editar Sobrenome");
						System.out.println("3. Editar Telefone");
						System.out.println("4. Editar E-mail");
						System.out.println("5. Editar Data de Nascimento");
						System.out.println("6. Voltar ao menu");
						int opcaoEditar = sc.nextInt();
						System.out.println();
						if (opcaoEditar == 1) {
							System.out.println("Digite o novo Nome do contato escolhido");
							contatoParaEditar.setNome(sc.next());
							System.out.println("Contato editado com sucesso!");
							System.out.println();
						} else if (opcaoEditar == 2) {
							System.out.println("Digite o novo Sobrenome do contato escolhido");
							contatoParaEditar.setSobrenome(sc.next());
							System.out.println("Contato editado com sucesso!");
							System.out.println();
						} else if (opcaoEditar == 3) {
							System.out.println("Digite o novo Telefone do contato escolhido");
							contatoParaEditar.setTelefone(sc.next());
							System.out.println("Contato editado com sucesso!");
							System.out.println();
						} else if (opcaoEditar == 4) {
							System.out.println("Digite o novo E-mail do contato escolhido");
							contatoParaEditar.setEmail(sc.next());
							System.out.println("Contato editado com sucesso!");
							System.out.println();
						} else if (opcaoEditar == 5) {
							System.out.println("Digite a nova Data de Nascimento do contato escolhido");
							contatoParaEditar.setDataDeNascimento(sc.nextInt());
							System.out.println("Contato editado com sucesso!");
							System.out.println();
						} else if (opcaoEditar == 6) {
							System.out.println();
						} else {
							System.out.println("Opção inválida. Tente novamente.");
							System.out.println();
						}
					}
				}
			} else if (opcao == 4) {
				System.out.println("Digite o nome do contato que deseja encontrar");
				String nomeContato = sc.next();
				Contato encontrarContato = null;

				if (contatos.isEmpty()) {
					System.out.println("Ainda não há nenhum contato registrado!");
					System.out.println();
				} else {
					for (Contato contato : contatos) {
						if (contato.getNome().equals(nomeContato)) {
							encontrarContato = contato;
							break;
						}
					}
				}

				if (encontrarContato == null) {
					System.out.println("Não há nenhum contato com esse nome!");
					System.out.println();
				} else {
					System.out.println(encontrarContato);

				}

			} else if (opcao == 5) {
				if (contatos.isEmpty()) {
					System.out.println("Ainda não há nenhum contato registrado!");
					System.out.println();
				}
				System.out.println("Qual o nome do contato que deseja excluir?");
				String removerContato = sc.next();
				System.out.println("Você tem certeza que deseja excluir? Use S para sim e N para não");
				String resposta = sc.next();
				if (resposta.equals("s") || resposta.equals("S")) {

					for (Contato contato : contatos) {
						if (contato.getNome().equalsIgnoreCase(removerContato)) {
							contatos.remove(contato);
							System.out.println("Contato removido com sucesso!");
							System.out.println();
							break;
						} else {
							System.out.println("Nenhum contato com esse nome!");
							System.out.println();
							break;
						}

					}
				} else {
					opcao = 1;
				}
			} else if (opcao == 6) {
				System.out.println("Saindo...");
				break;

			} else {
				System.out.println("Opção inválida. Tente novamente.");
				System.out.println();
			}

		}
		sc.close();
	}

}