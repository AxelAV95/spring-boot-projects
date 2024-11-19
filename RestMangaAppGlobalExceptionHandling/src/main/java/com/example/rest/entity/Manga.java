package com.example.rest.entity;

public class Manga {

	private int id;
	private String nombre;
	private String genero;
	private int totalEpisodios;
	private int anio;
	
	public Manga() {
		
	}

	public Manga(int id, String nombre, String genero, int totalEpisodios, int anio) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.genero = genero;
		this.totalEpisodios = totalEpisodios;
		this.anio = anio;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public int getTotalEpisodios() {
		return totalEpisodios;
	}

	public void setTotalEpisodios(int totalEpisodios) {
		this.totalEpisodios = totalEpisodios;
	}

	public int getAnio() {
		return anio;
	}

	public void setAnio(int anio) {
		this.anio = anio;
	}
	
	
	
	
}
