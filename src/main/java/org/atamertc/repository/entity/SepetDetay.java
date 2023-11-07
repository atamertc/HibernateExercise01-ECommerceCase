package org.atamertc.repository.entity;

import lombok.*;

import javax.persistence.*;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "tblsepetdetay")
public class SepetDetay {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    int sepetid;
    int urunid;
    int adet;
    double toplamfiyat;
    int kdv;
    double kdvtutari;
    @Embedded
    BaseEntity baseEntity;

}
