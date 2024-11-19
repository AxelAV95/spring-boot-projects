package com.example.lombok;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Manga {

	private int id;
	private String nombre;
	private String genero;
	private int anio;
}
