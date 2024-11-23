package com.example.hibernate.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.hibernate.entity.Manga;
import com.example.hibernate.repository.MangaRepository;

@Service
public class MangaServiceImpl implements MangaService {

	@Autowired
	private MangaRepository mangaRepository;
	
	@Override
	public List<Manga> findAll() {
		return mangaRepository.findAll();
	}

	@Override
	public Optional<Manga> findById(int id) {
		return mangaRepository.findById(id);
	}

	@Override
	public Manga save(Manga manga) {
		return mangaRepository.save(manga);
	}

	@Override
	public void deleteById(int id) {
		mangaRepository.deleteById(id);
		
	}

}
