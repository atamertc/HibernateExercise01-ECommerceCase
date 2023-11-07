package org.atamertc.repository.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
@NamedQueries({
        @NamedQuery(name = "Musteri.findAll",query = "SELECT m from Musteri m"),
        @NamedQuery(name = "Musteri.findByAd",query = "SELECT m from Musteri m WHERE lower(m.ad) LIKE lower(:birdegiskenadi)"),
        @NamedQuery(name = "Musteri.findById",query = "SELECT m from Musteri m WHERE m.id= :musteriid"),
        @NamedQuery(name = "Musteri.getCount",query = "SELECT Count(m) from Musteri m"),
        @NamedQuery(name = "Musteri.getAdSoyad",query = "SELECT concat(m.ad,' ',m.soyad) from Musteri m")
})
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "tblmusteri")
public class Musteri {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    /*
    insertable nasil calisi:
    insert into tblmusteri(id,ad) => insertable true
    insert into tblmusteri(id) => insertable false

    precision: toplam basamak sayisi
    sclae: virgulden sonraki basamak sayisi
    */
    @Column(name = "musteriad",
            length = 30,
            nullable = false,
            unique = false,
            insertable = true,
            updatable = false)
    String ad;
    String soyad;
    @Lob // oid olarak gorunuyor pg'de
    byte[] resim;
    //@Lob: Large Object : Veritabani icinde buyuk veri tutmak icin kullanilir.
    //Blob: Binary       :
    //Clob: Character    :
    @Temporal(TemporalType.TIMESTAMP)
    Date kayittarihi;
    long baslangictarihi;

    //@Transient         : Musteri classinda olan bir alan veritabaninda olusmasin
    @Transient
    String adsoyad;
    //@ElementCollection : Vt'da liste seklinde bilgi saklamak icin kullanilir.
    @ElementCollection
    List<String> telefonlistesi;
    //@Embedded          : Embeddable ile isaretlenmis classi bu classa dahil eder.
    @Embedded
    Iletisim iletisim;
    @Embedded
    BaseEntity baseEntity;
    @Enumerated(EnumType.STRING)
    ECinsiyet cinsiyet;
}
