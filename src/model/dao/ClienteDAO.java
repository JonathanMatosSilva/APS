package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import connection.ConnectionFactory;
import model.bean.Cliente;

public class ClienteDAO {

	public void create(Cliente c) {

		Connection con = ConnectionFactory.getConnection();

		PreparedStatement stmt = null;

		try {

			stmt = con.prepareStatement(
					"INSERT INTO agenda (Nome, Endereco, Fone, Celular, Sexo, Obs)VALUES(?,?,?,?,?,?)"); // “preparar
																											// Declaração”

			stmt.setString(1, c.getNome());

			stmt.setString(2, c.getEndereco());

			stmt.setString(3, c.getFone());

			stmt.setString(4, c.getCelular());

			stmt.setString(5, c.getSexo());

			stmt.setString(6, c.getObs());

			stmt.executeUpdate();

			JOptionPane.showMessageDialog(null, "Salvo com sucesso.");

		} catch (SQLException ex) {

//Logger.getLogger(ClienteDAO.class.getNam()).log(Level.SEVERE, null, ex);

			JOptionPane.showMessageDialog(null, "Erro ao salvar" + ex);

		}

		finally {

//ConnectionFactory.closeConnection(con, stmt);

		}

	}

	/**
	 *
	 * 
	 * 
	 * @return
	 * 
	 */

	public List<Cliente> read() {

		Connection con = ConnectionFactory.getConnection();// abre a conexao

		PreparedStatement stmt = null;

		ResultSet rs = null;

		List<Cliente> clientes = new ArrayList<>();

		try {

			stmt = con.prepareStatement("SELECT * FROM agenda");

			rs = stmt.executeQuery();

			while (rs.next()) {

				Cliente cliente = new Cliente();

				cliente.setCodigo(rs.getInt("Codigo"));

				cliente.setNome(rs.getString("Nome"));

				cliente.setEndereco(rs.getString("Endereco"));

				cliente.setFone(rs.getString("Fone"));

				cliente.setCelular(rs.getString("Celular"));

				cliente.setSexo(rs.getString("Sexo"));

				cliente.setObs(rs.getString("Obs"));

				clientes.add(cliente);
				System.out.println(cliente);
			}

		} catch (SQLException ex) {

			ex.getMessage();

		} finally {
		}

		return clientes;

	}

	public void update(Cliente c) {

		Connection con = ConnectionFactory.getConnection();

		PreparedStatement stmt = null;

		try {

			stmt = con.prepareStatement(
					"UPDATE agenda SET Nome = ?, Endereco = ?, Fone = ?, Celular = ?, Sexo = ?, Obs = ? WHERE Codigo = ?");

			stmt.setString(1, c.getNome());

			stmt.setString(2, c.getEndereco());

			stmt.setString(3, c.getFone());

			stmt.setString(4, c.getCelular());

			stmt.setString(5, c.getSexo());

			stmt.setString(6, c.getObs());

			stmt.setInt(7, c.getCodigo());

			stmt.executeUpdate();

			JOptionPane.showMessageDialog(null, "Atualizado com sucesso.");

		} catch (SQLException ex) {

//Logger.getLogger(ClienteDAO.class.getNam()).log(Level.SEVERE, null, ex);

			JOptionPane.showMessageDialog(null, "Erro ao atualizar " + ex);

		}

		finally {

//ConnectionFactory.closeConnection(con, stmt);

		}

	}

	public void delete(Cliente c) {

		Connection con = ConnectionFactory.getConnection();

		PreparedStatement stmt = null;

		try {

			stmt = con.prepareStatement("DELETE FROM agenda WHERE Codigo = ?");

			stmt.setInt(1, c.getCodigo());

			stmt.executeUpdate();

			JOptionPane.showMessageDialog(null, "Excluido com sucesso.");

		} catch (SQLException ex) {

//Logger.getLogger(ClienteDAO.class.getNam()).log(Level.SEVERE, null, ex);

			JOptionPane.showMessageDialog(null, "Erro ao exlcuir " + ex);

		}

		finally {

//ConnectionFactory.closeConnection(con, stmt);

		}

	}

	private void setCodigo(String string) {

		throw new UnsupportedOperationException("Not supported yet."); // To change body of generated methods, choose
																		// Tools | Templates.

	}

	private void setNome(String string) {

		throw new UnsupportedOperationException("Not supported yet."); // To change body of generated methods, choose
																		// Tools | Templates.

	}

	private void setEndereco(String string) {

		throw new UnsupportedOperationException("Not supported yet."); // To change body of generated methods, choose
																		// Tools | Templates.

	}

	private void setFone(String string) {

		throw new UnsupportedOperationException("Not supported yet."); // To change body of generated methods, choose
																		// Tools | Templates.

	}

	private void setCelular(String string) {

		throw new UnsupportedOperationException("Not supported yet."); // To change body of generated methods, choose
																		// Tools | Templates.

	}

	private void setSexo(String string) {

		throw new UnsupportedOperationException("Not supported yet."); // To change body of generated methods, choose
																		// Tools | Templates.

	}

	private void setObs(String string) {

		throw new UnsupportedOperationException("Not supported yet."); // To change body of generated methods, choose
																		// Tools | Templates.

	}

}