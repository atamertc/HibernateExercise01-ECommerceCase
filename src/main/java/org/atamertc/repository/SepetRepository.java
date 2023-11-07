package org.atamertc.repository;

import org.atamertc.repository.entity.Sepet;
import org.atamertc.utility.MyFactoryRepository;

public class SepetRepository extends MyFactoryRepository<Sepet, Integer> {
    public SepetRepository() {
        super(new Sepet());
    }
}
