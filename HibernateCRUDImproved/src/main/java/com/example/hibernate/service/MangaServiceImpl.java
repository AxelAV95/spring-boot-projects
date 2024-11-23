package com.example.hibernate.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.hibernate.dao.MangaDao;
import com.example.hibernate.entity.Manga;

@Service
public class MangaServiceImpl implements MangaService {

	@Autowired
	private MangaDao mangaDao;
	
	@Override
	public List<Manga> findAll() {
		return mangaDao.findAll();
	}

	@Override
	public Manga findById(int id) {
		return mangaDao.findById(id);
	}

	@Override
	public Manga save(Manga manga) {
		return mangaDao.save(manga);
	}

	@Override
	public void deleteById(int id) {
		mangaDao.deleteById(id);
		
	}

}
