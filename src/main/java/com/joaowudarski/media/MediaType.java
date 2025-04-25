package com.joaowudarski.media;

import com.joaowudarski.media.repository.MovieRepository;
import com.joaowudarski.media.entity.MovieEntity;
import com.joaowudarski.media.request.MovieRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Arrays;
import java.util.Optional;

@Getter
@AllArgsConstructor
public enum MediaType {

    MOVIE("movie_type", MovieEntity.class, MovieRepository.class, MovieRequest.class, Object.class);

    private final String name;
    private final Class<? extends AbstractMedia> entity;
    private final Class<? extends JpaRepository<?, String>> repository;
    private final Class<?> dtoInput;
    private final Class<? extends ResponseDto> dtoOutput;

    public static Optional<MediaType> findByName(String name) {
        return Arrays.stream(values()).filter(x -> x.name.equals(name))
                .findFirst();
    }

    public ResponseDto getNewOutputInstance() {
        try {
            return this.dtoOutput.getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new InvalidTypeException(e.getMessage(), e);
        }
    }
}
