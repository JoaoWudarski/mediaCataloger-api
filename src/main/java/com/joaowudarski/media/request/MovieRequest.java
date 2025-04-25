package com.joaowudarski.media.request;

import com.joaowudarski.media.MediaType;
import com.joaowudarski.media.entity.MovieEntity;
import com.joaowudarski.media.request.interfaces.RequestDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovieRequest implements RequestDto {

    private String id;
    private String name;
    private String publishDate;
    private String gender;
    private String director;
    private Integer duration;
    private Float rate;
    private String dateWatch;
    private String status;

    public MovieEntity toEntity() {
        return MovieEntity.builder()
                .id(id)
                .name(name)
                .mediaType(MediaType.MOVIE)
                .publishDate(publishDate == null ? null : LocalDate.parse(publishDate))
                .gender(gender)
                .director(director)
                .duration(duration)
                .rate(rate)
                .initialDate(dateWatch == null ? null : LocalDate.parse(dateWatch))
                .finalDate(dateWatch == null ? null : LocalDate.parse(dateWatch))
                .status(status)
                .build();
    }
}
