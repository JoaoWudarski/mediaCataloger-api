package com.joaowudarski.media.repository;

import com.joaowudarski.media.entity.MovieEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<MovieEntity, String> {

}
