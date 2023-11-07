package org.atamertc.services;

import org.atamertc.repository.MusteriRepository;
import org.atamertc.repository.UrunRepository;
import org.atamertc.repository.entity.Musteri;
import org.atamertc.repository.entity.Urun;
import org.atamertc.utility.MyFactoryService;

public class UrunService extends MyFactoryService<UrunRepository, Urun, Integer> {

    public UrunService() {
        super(new UrunRepository());
    }
}
