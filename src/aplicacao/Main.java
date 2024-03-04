package aplicacao;

import java.util.Scanner;

import modulo.Livros;
import dao.LivroDao;

public class Main {

	public static void main(String[] args) {

		LivroDao livroDao = new LivroDao();

		Scanner sc = new Scanner(System.in);
		Livros livro = new Livros();
		
		while (true) {
			
			int resposta = 0;
			System.out.println("Escolha uma opção\n1 - Adicionar livro\n2 - Exibir livros\n3 - Atualizar livro\n4 - Deletar um livro\n5 - SAIR");
			resposta = sc.nextInt();
			
			if (resposta == 1) {
				sc.nextLine();
				System.out.println("Informe o Titulo do livro");
				livro.setTitulo(sc.nextLine());
				System.out.println("Informe a Editora do livro");
				livro.setEditora(sc.nextLine());
				System.out.println("Informe o Autor do livro");
				livro.setAutor(sc.nextLine());

				livroDao.create(livro);
			}
			else if (resposta == 2) {
				for (Livros l : livroDao.getLivros()) {
					System.out.println("Id: " + l.getId() +  "\nTitulo: " + l.getTitulo() + "\nEditora: " + l.getEditora() + "\nAutor: " + l.getAutor() + "\n------------------");
				}
			}
			else if (resposta == 3) {

				Livros bl = new Livros();
				
				System.out.println("Informe o Id que deseja alterar");
				bl.setId(sc.nextInt());
				
				sc.nextLine(); // Consome uma linha
				System.out.println("Informe o novo Titulo");
				bl.setTitulo(sc.nextLine());
				System.out.println("Informe a nova Editora");
				bl.setEditora(sc.nextLine());
				System.out.println("Informe o novo Autor");
				bl.setAutor(sc.nextLine());

				livroDao.update(bl);
			} 
			else if(resposta == 4) {
				System.out.println("Informe o Id que será deletado");
				int deletar = sc.nextInt();
				System.out.println("Certeza que deseja deletar o livro:\n*************");
				
				livroDao.read(deletar);
				
				System.out.println("*************\nSIM - Deletar\nN - Para não");
				sc.nextLine();
				String confirmacao = sc.nextLine();				
				if(confirmacao.equalsIgnoreCase("SIM")) {
					livroDao.deleteByID(deletar);
					System.out.println("Livro Deletado com sucesso!");
				} else {
					System.out.println("Não deletado");
				}
				
			} 
			else if (resposta == 5) {
				break;
			}
		}

		

		System.out.println("Programa encerrado");
		sc.close();

	}

}
