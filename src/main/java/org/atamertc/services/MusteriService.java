package org.atamertc.services;

import org.atamertc.repository.MusteriRepository;
import org.atamertc.repository.entity.Musteri;
import org.atamertc.utility.IService;
import org.atamertc.utility.MyFactoryService;

import java.util.List;
import java.util.Optional;

public class MusteriService extends MyFactoryService<MusteriRepository,Musteri,Integer> {

    public MusteriService() {
        super(new MusteriRepository());
    }
}
