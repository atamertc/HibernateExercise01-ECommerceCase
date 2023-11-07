package org.atamertc.repository.entity;

import lombok.*;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Table;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class BaseEntity {
    Long olusturulmatarihi;
    Long guncellemetarihi;
    Integer state;
}
