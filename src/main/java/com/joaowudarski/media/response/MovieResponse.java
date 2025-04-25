package com.joaowudarski.media.response;

import com.joaowudarski.media.AbstractMedia;
import com.joaowudarski.media.entity.MovieEntity;
import com.joaowudarski.media.response.interfaces.ResponseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.format.DateTimeFormatter;

import static java.util.Objects.nonNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MovieResponse implements ResponseDto {

    private String id;
    private String name;
    private String publishDate;
    private String gender;
    private String director;
    private Integer duration;
    private Float rate;
    private String dateWatch;
    private String status;

    @Override
    public ResponseDto byEntity(AbstractMedia abstractMedia) {
        MovieEntity movieEntity = (MovieEntity) abstractMedia;
        return MovieResponse.builder()
                .id(movieEntity.getId())
                .name(movieEntity.getName())
                .publishDate(nonNull(movieEntity.getPublishDate()) ? movieEntity.getPublishDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) : null)
                .gender(movieEntity.getGender())
                .director(movieEntity.getDirector())
                .duration(movieEntity.getDuration())
                .rate(movieEntity.getRate())
                .dateWatch(nonNull(movieEntity.getInitialDate()) ? movieEntity.getInitialDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) : null)
                .status(movieEntity.getStatus())
                .build();
    }
}
