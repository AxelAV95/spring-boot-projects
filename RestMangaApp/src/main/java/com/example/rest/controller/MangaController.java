package com.example.rest.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.rest.entity.Manga;

@RestController
@RequestMapping("/api")
public class MangaController {

	@GetMapping("/mangas")
	public List<Manga> getMangas(){
		List<Manga> mangas = new ArrayList<Manga>();
		
		mangas.add(new Manga(1,"Bleach","Shonen", 500, 2001));
		mangas.add(new Manga(1,"One piece","Shonen", 1120, 1997));
		mangas.add(new Manga(1,"Naruto","Shonen", 700, 1999));
		return mangas;
		
		
	}
}
