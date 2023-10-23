package model.dao;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.sql.rowset.serial.SerialBlob;
import javax.swing.JOptionPane;

import connection.ConnectionFactory;
import model.bean.Pet;

public class PetDAO {

	public void create(Pet p) {
	    Connection con = ConnectionFactory.getConnection();
	    PreparedStatement stmt = null;
	    try {
	        stmt = con.prepareStatement("INSERT INTO pet (NOME, TUTOR, PORTE, RACA, SEXO, FOTO) VALUES (?, ?, ?, ?, ?, ?)");
	        stmt.setString(1, p.getNome());
	        stmt.setString(2, p.getTutor());
	        stmt.setString(3, p.getPorte());
	        stmt.setString(4, p.getRaca());
	        stmt.setString(5, p.getSexo());

	        // Converte a imagem (Image) em um BufferedImage
	        BufferedImage bufferedImage = toBufferedImage(p.getFoto());
	        
	        ByteArrayOutputStream baos = new ByteArrayOutputStream();
	        ImageIO.write(bufferedImage, "png", baos);
	        byte[] imageBytes = baos.toByteArray();
	        
	        Blob imageBlob = new SerialBlob(imageBytes);
	        
	        stmt.setBlob(6, imageBlob);
	        
	        stmt.executeUpdate();
	        JOptionPane.showMessageDialog(null, "Salvo com sucesso.");
	    } catch (SQLException | IOException ex) {
	        JOptionPane.showMessageDialog(null, "Erro ao salvar" + ex);
	    } finally {
	        ConnectionFactory.closeConnection(con, stmt);
	    }
	}

	public BufferedImage toBufferedImage(Image img) {
	    if (img instanceof BufferedImage) {
	        return (BufferedImage) img;
	    }

	    BufferedImage bufferedImage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);
	    Graphics2D g = bufferedImage.createGraphics();
	    g.drawImage(img, 0, 0, null);
	    g.dispose();

	    return bufferedImage;
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

	            // Recupere o Blob da coluna "Foto"
	            Blob blob = rs.getBlob("Foto");

	            if (blob != null) {
	                byte[] blobData = blob.getBytes(1, (int) blob.length());
	                ByteArrayInputStream inputStream = new ByteArrayInputStream(blobData);
	                BufferedImage image = ImageIO.read(inputStream);
	                Pet.setFoto(image);
	            }

	            Pets.add(Pet);
	        }
	    } catch (SQLException ex) {
	        ex.printStackTrace();
	    } catch (IOException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.closeConnection(con, stmt);
	    }
	    return Pets;
	}


	public void update(Pet p) {
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		try {
			stmt = con.prepareStatement(
					"UPDATE pet SET NOME = ?, TUTOR = ?, PORTE = ?, RACA = ?, SEXO = ?, FOTO = ? WHERE ID = ?");
			stmt.setString(1, p.getNome());
			stmt.setString(2, p.getTutor());
			stmt.setString(3, p.getPorte());
			stmt.setString(4, p.getRaca());
			stmt.setString(5, p.getSexo());
			
			BufferedImage bufferedImage = toBufferedImage(p.getFoto());
	        ByteArrayOutputStream baos = new ByteArrayOutputStream();
	        ImageIO.write(bufferedImage, "png", baos);
	        byte[] imageBytes = baos.toByteArray();
	        Blob imageBlob = new SerialBlob(imageBytes);
	        stmt.setBlob(6, imageBlob);
	        
			stmt.setInt(7, p.getCodigo());
			stmt.executeUpdate();
			JOptionPane.showMessageDialog(null, "Atualizado com sucesso.");
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Erro ao atualizar " + ex);
		} catch (IOException e) {
			e.printStackTrace();
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