package org.atamertc.services;

import org.atamertc.repository.MusteriRepository;
import org.atamertc.repository.SepetRepository;
import org.atamertc.repository.entity.Musteri;
import org.atamertc.repository.entity.Sepet;
import org.atamertc.utility.MyFactoryService;

public class SepetService extends MyFactoryService<SepetRepository, Sepet, Integer> {

    public SepetService() {
        super(new SepetRepository());
    }
}
