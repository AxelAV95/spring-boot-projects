package com.example.hibernate.service;

import java.util.List;
import java.util.Optional;

import com.example.hibernate.entity.Manga;

public interface MangaService {
	
	List<Manga> findAll();
    public Optional<Manga> findById(int id);
	public Manga save(Manga employee);
	public void deleteById(int id);	

}
