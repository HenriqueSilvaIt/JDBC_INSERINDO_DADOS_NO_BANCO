package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import db.DB;
import db.DbException;

public class ProgramJDBC {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		Connection conn = null;
		PreparedStatement ps = null; // Permite você montar usa consulta SQL deixando os
		//parâmetros para colocar depoiS
		
		try {
			conn = DB.getConnection();
			// No comando abaixo vamos colocar como argumento
			// a iserção do SQL
			/*ps = conn.prepareStatement( 
					"INSERT INTO seller " // O ID do vendedor não é inserido
					// porque ele é autoincrement
					+ "(Name, Email, BirthDate, BaseSalary, DepartamentId)"
					+ "VALUES "
					+ "(?,?,?,?,?)",
					Statement.RETURN_GENERATED_KEYS); // O simbolo de interrogação é o placeHolder é o lugar onde
					// depois você vai colocar o valor, você coloca varios
					// que cada ? reprsenta uma coluna
			ps.setString(1,"Carl Purple"); // ID, VALOR (AQUI é estilo chave/valor, você passa o ID e o  valor;
			// esse primeiro campo é o nome
			ps.setString(2, "carl@gmail.com"); // estamos dizendo que o segundo ?
			// será substituido pelo campo email
			ps.setDate(3, new java.sql.Date(sdf.parse("22/04/1984").getTime())); // no sql para datas n pode ser o data.java.util
			// tem que ser o java.sql.date, e para funcionar com java.sql tem que colocar
			// depois do argumento getTime()
			ps.setDouble(4, 3000);
			ps.setInt(5, 4);*/
			
			ps = conn.prepareStatement("insert into departament (Name) values ('D1'),('D2')",
					Statement.RETURN_GENERATED_KEYS);
			
			// para executar comando de alteração de dados no banco é executeUpdate:
			int rowsAffected = ps.executeUpdate(); // ele retorna
			// a quantidade de linhas que foi afetada, por isso criamos
			// essa variavel rowsAffected
			
			/*System.out.println("Done! RowsAffected: " + rowsAffected);
			// lembrando que como coloquei  os dados de só um funcionario
			/estamos trabalhando somente com uma linha*/
			
			if (rowsAffected > 0) {
				ResultSet rs = ps.getGeneratedKeys(); // essa função retorna um objeto 
				//do tipo resultset
				
				while(rs.next()) {
					int id = rs.getInt(1); // aqui ele retorna uma tabela auxiliar (nova
					// que contem só os ID
					//  por isso que colocamos 1 para pegar o valor da primeira coluna
					// que é o ID
					System.out.println("Done! ID " + id);
				}
				
			}else { 
				System.out.println("No rows affected");
			}
			
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} /*catch (ParseException p) {
			System.out.println(p.getMessage());
		} */ finally {
			//Fechando PreparedStatement
			DB.closeStatement(ps);
			// Fechando conexão com o banco
			DB.closeConnection();
		}
		
		
	}

}
