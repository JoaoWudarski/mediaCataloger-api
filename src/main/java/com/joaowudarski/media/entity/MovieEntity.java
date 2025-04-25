package com.joaowudarski.media.entity;

import com.joaowudarski.media.AbstractMedia;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Data
@Entity
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class MovieEntity extends AbstractMedia {

    private LocalDate publishDate;
    private String gender;
    private String director;
    private Integer duration;

}
