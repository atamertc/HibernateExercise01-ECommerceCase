package org.atamertc.services;

import org.atamertc.repository.MusteriRepository;
import org.atamertc.repository.SepetDetayRepository;
import org.atamertc.repository.entity.Musteri;
import org.atamertc.repository.entity.Sepet;
import org.atamertc.repository.entity.SepetDetay;
import org.atamertc.utility.MyFactoryService;

public class SepetDetayService extends MyFactoryService<SepetDetayRepository, SepetDetay, Integer> {

    public SepetDetayService() {
        super(new SepetDetayRepository());
    }
}
