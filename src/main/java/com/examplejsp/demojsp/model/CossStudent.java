package com.examplejsp.demojsp.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
public class CossStudent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cossName")
    private String cossName;

    @Column(name = "cossEmail")
    private String cossEmail;

    @Column(name = "cossMobile")
    private String cossMobile;

}
