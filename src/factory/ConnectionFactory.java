package factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// CÓDIGO RESPONSÁVEL PELA CONEXÃO COM O BANCO DE DADOS
// EDITAR APENAS USERNAME, PASSWORD E DATABASE_URL

public class ConnectionFactory {
    
    // Nome do usuário do MySQL
    private static final String USERNAME = "root";
    
    // Senha do banco
    private static final String PASSWORD = "";
    
    // Caminho do Banco de Dados, porta, nome do Banco de dados
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/biblioteca";
    
    // Conexão com banco de dados
    public static Connection createConnectionToMySQL() throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        
        // Cria a conexão com o banco de dados
        Connection connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
        
        return connection;
    }
    
    public static void main(String[] args) throws SQLException {
        
        // Recuperar uma conexão com o banco de dados
        Connection con = null;
        try {
            con = createConnectionToMySQL();
            
            // Testar a conexão
            if (con != null) {
                System.out.println("Conexão obtida com sucesso");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (con != null) {
                con.close();
            }
        }
    }
}
