package org.atamertc.repository.entity;

import lombok.*;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Table;
/*
@Embeddable: Bir sinifin baska bir entity sinifi icinde kullanilabilmesini saglar
Yeni bir tablo olusturmaz.
Bunlar eklenen entity icinde column olarak gelir.
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Embeddable
public class Iletisim {
    String telefon;
    String email;
    String instagram;
}
