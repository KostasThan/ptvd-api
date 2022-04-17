package com.mixas.dao.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "MIXAS")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class MixasEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
}
