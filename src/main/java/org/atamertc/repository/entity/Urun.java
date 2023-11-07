package org.atamertc.repository.entity;

import lombok.*;

import javax.persistence.*;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "tblurun")
public class Urun {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String ad;
    String marka;
    Double fiyat;
    @Embedded
    BaseEntity baseEntity;
}
