package org.atamertc.repository;

import org.atamertc.repository.entity.Urun;
import org.atamertc.utility.MyFactoryRepository;

public class UrunRepository extends MyFactoryRepository<Urun,Integer> {
    public UrunRepository() {
        super(new Urun());
    }
}
