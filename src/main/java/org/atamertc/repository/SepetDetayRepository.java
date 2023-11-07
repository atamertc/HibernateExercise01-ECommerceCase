package org.atamertc.repository;

import org.atamertc.repository.entity.SepetDetay;
import org.atamertc.utility.MyFactoryRepository;

public class SepetDetayRepository extends MyFactoryRepository<SepetDetay, Integer> {
    public SepetDetayRepository() {
        super(new SepetDetay());
    }
}
