package com.example.hibernate.repository;

import org.springframework.stereotype.Repository;

import com.example.hibernate.entity.Manga;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface MangaRepository extends JpaRepository<Manga, Integer> {

}
