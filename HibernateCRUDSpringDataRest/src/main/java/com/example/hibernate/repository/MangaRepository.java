package com.example.hibernate.repository;

import org.springframework.stereotype.Repository;

import com.example.hibernate.entity.Manga;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@Repository
@RepositoryRestResource(path = "mangas")
public interface MangaRepository extends JpaRepository<Manga, Integer> {

}
