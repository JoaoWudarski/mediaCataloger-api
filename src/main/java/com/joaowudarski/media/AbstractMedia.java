package com.joaowudarski.media;

import com.joaowudarski.gateway.repository.data.UserEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Data
@Entity
@SuperBuilder
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class AbstractMedia {

    @Id
    protected String id;
    protected String name;
    protected MediaType mediaType;
    protected Float rate;
    protected String status;
    protected LocalDate initialDate;
    protected LocalDate finalDate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;
}
