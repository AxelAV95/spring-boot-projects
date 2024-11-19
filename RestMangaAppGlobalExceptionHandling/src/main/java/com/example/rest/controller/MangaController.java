package com.example.rest.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.rest.entity.Manga;
import com.example.rest.entity.MangaErrorResponse;
import com.example.rest.exceptions.MangaNotFoundException;

import jakarta.annotation.PostConstruct;

@RestController
@RequestMapping("/api")
public class MangaController {

	List<Manga> mangas = new ArrayList<Manga>();
	
	@PostConstruct
	public void loadMangas() {
		mangas.add(new Manga(1,"Bleach","Shonen", 500, 2001));
		mangas.add(new Manga(1,"One piece","Shonen", 1120, 1997));
		mangas.add(new Manga(1,"Naruto","Shonen", 700, 1999));
	}
	
	@GetMapping("/mangas")
	public List<Manga> getMangas(){
		
		return mangas;
				
	}
	
	@GetMapping("/mangas/{mangaid}")
	public Manga getManga(@PathVariable int mangaid) {
		
		if((mangaid >= mangas.size() || mangaid < 0)) {
			throw new MangaNotFoundException("manga id not found - "+ mangaid);
		}
		
		return mangas.get(mangaid);
	}
	
	
}
