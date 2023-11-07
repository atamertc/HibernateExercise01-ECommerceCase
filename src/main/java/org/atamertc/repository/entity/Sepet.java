package org.atamertc.repository.entity;

import lombok.*;

import javax.persistence.*;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "tblsepet")
public class Sepet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    long date;
    double toplamfiyat;
    double toplamkdv;
    @Embedded
    BaseEntity baseEntity;
}
