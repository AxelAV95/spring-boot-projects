package com.example.hibernate.controller;

import java.util.List;

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
	
	@PostMapping("/mangas")
	public String addManga(@RequestBody Manga manga) {
		
		mangaService.createManga(manga);
		
		return "Manga guardado con éxito";
	}
	
	@GetMapping("/mangas/{id}")
	public Manga getManga(@PathVariable Integer id) {
			
		return mangaService.getManga(id);
	}
	
	@GetMapping("/mangas")
	public List<Manga> getAllMangas(){
		return mangaService.getAllMangas();
	}
	
	@PutMapping("/mangas")
	public String updateManga(@RequestBody Manga manga) {
		
		mangaService.updateManga(manga);
		
		return "Manga actualizado con éxito";
	}
	
	@DeleteMapping("/mangas/{id}")
	public String deleteManga(@PathVariable Integer id) {
		mangaService.deleteManga(id);
		
		return "Manga borrado con éxito";
	}

}
