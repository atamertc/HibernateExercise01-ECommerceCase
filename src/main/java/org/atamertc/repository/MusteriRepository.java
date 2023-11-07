package org.atamertc.repository;

import org.atamertc.repository.entity.Musteri;
import org.atamertc.utility.MyFactoryRepository;

public class MusteriRepository extends MyFactoryRepository<Musteri, Integer> {
    public MusteriRepository() {
        super(new Musteri());
    }

}
