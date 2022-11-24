package com.example.firmamalumot.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Ishchi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String nomi;
    @Column(nullable = false)
    private String telRaqam;

    @OneToOne
    private Manzil manzil;
    @ManyToOne
    private Bolim bolim;
}
