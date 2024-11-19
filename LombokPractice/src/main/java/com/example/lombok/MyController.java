package com.example.lombok;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class MyController {
	
	
	@GetMapping("/name");
	public String getMangaName() {
		
		/*Manga manga = new Manga(1,"One piece", "Shonen", 1997);
		
		return manga.getNombre();*/
		
		return "s";
		
	}

}
