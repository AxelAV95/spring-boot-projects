package com.example.hibernate.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.hibernate.entity.Manga;
import com.example.hibernate.service.MangaService;

@RestController
@RequestMapping("/api")
public class MangaController {
	
	@Autowired
	private MangaService mangaService;
	
	@GetMapping("/mangas")
	public  List<Manga>getMangas() {
		return mangaService.findAll();
	}
	
	@GetMapping("/mangas/{id}")
	public  Optional<Manga> getManga(@PathVariable int id) {
		return mangaService.findById(id);
	}
	
	@PostMapping("/mangas")
	public Manga addManga(@RequestBody Manga manga) {
		
		Manga dbManga = mangaService.save(manga); 
		return dbManga;
	}
	
	@PutMapping("/mangas")
	public Manga updateManga(@RequestBody Manga manga) {
		Manga dbManga = mangaService.save(manga);
		return dbManga;
	}
	
	@DeleteMapping("/mangas/{id}")
	public String deleteEmployee(@PathVariable int id) {
		 Optional<Manga> tempManga = mangaService.findById(id);
		if(tempManga == null) {
			throw new RuntimeException("Manga ID not found: "+id);
		}
		else
			mangaService.deleteById(id);
	
		return "Manga with ID:"+id+" deleted"; 
	}

}
