package com.honey.honeypot.repository.startup;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "Startup")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Startup {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Long id;

    @Column(name = "StartedAt")
    private LocalDate startedAt;
}
