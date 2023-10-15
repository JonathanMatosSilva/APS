package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import connection.ConnectionFactory;
import model.bean.Pet;

public class PetDAO {

	public void create(Pet p) {
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		try {
			stmt = con.prepareStatement("INSERT INTO pet (NOME, TUTOR, PORTE, RACA, SEXO) VALUES (?, ?, ?, ?, ?)");
			stmt.setString(1, p.getNome());
			stmt.setString(2, p.getTutor());
			stmt.setString(3, p.getPorte());
			stmt.setString(4, p.getRaca());
			stmt.setString(5, p.getSexo());
			stmt.executeUpdate();
			JOptionPane.showMessageDialog(null, "Salvo com sucesso.");
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Erro ao salvar" + ex);
		}
		finally {
			ConnectionFactory.closeConnection(con, stmt);
		}
	}

	public List<Pet> read() {
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<Pet> Pets = new ArrayList<>();
		try {
			stmt = con.prepareStatement("SELECT * FROM pet");
			rs = stmt.executeQuery();
			while (rs.next()) {
				Pet Pet = new Pet();
				Pet.setCodigo(rs.getInt("ID"));
				Pet.setNome(rs.getString("NOME"));
				Pet.setTutor(rs.getString("TUTOR"));
				Pet.setPorte(rs.getString("PORTE"));
				Pet.setRaca(rs.getString("RACA"));
				Pet.setSexo(rs.getString("SEXO"));
				Pets.add(Pet);
			}
		} catch (SQLException ex) {
			ex.getMessage();
		} finally {
		}
		return Pets;

	}

	public void update(Pet p) {
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		try {
			stmt = con.prepareStatement(
					"UPDATE pet SET NOME = ?, TUTOR = ?, PORTE = ?, RACA = ?, SEXO = ? WHERE ID = ?");
			stmt.setString(1, p.getNome());
			stmt.setString(2, p.getTutor());
			stmt.setString(3, p.getPorte());
			stmt.setString(4, p.getRaca());
			stmt.setString(5, p.getSexo());
			stmt.setInt(6, p.getCodigo());
			stmt.executeUpdate();
			JOptionPane.showMessageDialog(null, "Atualizado com sucesso.");
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Erro ao atualizar " + ex);
		}
		finally {
			ConnectionFactory.closeConnection(con, stmt);
		}
	}

	public void delete(Pet p) {
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		try {
			stmt = con.prepareStatement("DELETE FROM pet WHERE ID = ?");
			stmt.setInt(1, p.getCodigo());
			stmt.executeUpdate();
			JOptionPane.showMessageDialog(null, "Excluido com sucesso.");
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Erro ao exlcuir " + ex);
		}
		finally {
			ConnectionFactory.closeConnection(con, stmt);
		}
	}
}