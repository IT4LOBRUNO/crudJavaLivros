package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.List;
import java.util.ArrayList;

import com.mysql.jdbc.PreparedStatement;

import factory.ConnectionFactory;
import modulo.Livros;

public class LivroDao {
	// CRUD

	public void create(Livros livro) {
		String sql = "INSERT INTO livros(titulo, editora, autor) VALUES(?, ?, ?)";

		Connection conn = null;
		PreparedStatement pstm = null;

		try {
			conn = ConnectionFactory.createConnectionToMySQL();

			pstm = (PreparedStatement) conn.prepareStatement(sql);

			pstm.setString(1, livro.getTitulo());
			pstm.setString(2, livro.getEditora());
			pstm.setString(3, livro.getAutor());
			pstm.execute();

			System.out.println("Livro salvo com sucesso!");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			try {
				if (pstm != null) {
					pstm.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public List<Livros> getLivros() {
		String sql = "SELECT * FROM livros";
		List<Livros> livros = new ArrayList<>();
		try (Connection conn = ConnectionFactory.createConnectionToMySQL();
				PreparedStatement pstm = (PreparedStatement) conn.prepareStatement(sql);
				ResultSet rset = pstm.executeQuery()) {

			while (rset.next()) {
				Livros livro = new Livros();
				livro.setId(rset.getInt("id"));
				livro.setTitulo(rset.getString("titulo"));
				livro.setEditora(rset.getString("editora"));
				livro.setAutor(rset.getString("autor"));
				livros.add(livro);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return livros;
	}

	public void update(Livros livro) {

		String sql = "UPDATE livros SET titulo = ?, editora = ?, autor = ?" + " WHERE id = ?";

		Connection conn = null;
		PreparedStatement pstm = null;

		try {
			conn = ConnectionFactory.createConnectionToMySQL();

			pstm = (PreparedStatement) conn.prepareStatement(sql);

			pstm.setInt(4, livro.getId());

			pstm.setString(1, livro.getTitulo());
			pstm.setString(2, livro.getEditora());
			pstm.setString(3, livro.getAutor());
			pstm.execute();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstm != null) {
					pstm.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void deleteByID(int id) {
		String sql = "DELETE FROM livros WHERE id =?";

		Connection conn = null;
		PreparedStatement pstm = null;

		try {
			conn = ConnectionFactory.createConnectionToMySQL();

			pstm = (PreparedStatement) conn.prepareStatement(sql);

			pstm.setInt(1, id);

			pstm.execute();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstm != null) {
					pstm.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e) {

				e.printStackTrace();
			}
		}
	}

	public void read(int id) {
		String sql = "SELECT titulo, editora, autor FROM livros WHERE id = ?";
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rset = null;
		try {
			conn = ConnectionFactory.createConnectionToMySQL();

			pstm = (PreparedStatement) conn.prepareStatement(sql);

			pstm.setInt(1, id);

			rset = pstm.executeQuery();

			if (rset.next()) {

				String titulo = rset.getString("titulo");
				String editora = rset.getString("editora");
				String autor = rset.getString("autor");
				System.out.println("Titulo: " + titulo + "\nEditora: " + editora + "\nautor: " + autor);
			} else {
				System.out.println("Livro não encontrado com o id: " + id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rset != null) {
					rset.close();
				}
				if (pstm != null) {
					pstm.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
