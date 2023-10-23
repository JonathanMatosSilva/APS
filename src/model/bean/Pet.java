package model.bean;

import java.awt.Image;

public class Pet {

	private int codigo;
	private String nome;
	private String tutor;
	private String porte;
	private String raca;
	private String sexo;
	private Image foto;
	
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getTutor() {
		return tutor;
	}
	public void setTutor(String tutor) {
		this.tutor = tutor;
	}
	public String getPorte() {
		return porte;
	}
	public void setPorte(String porte) {
		this.porte = porte;
	}
	public String getRaca() {
		return raca;
	}
	public void setRaca(String raca) {
		this.raca = raca;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	public Image getFoto() {
		return foto;
	}
	public void setFoto(Image foto) {
		this.foto = foto;
	}

	
}