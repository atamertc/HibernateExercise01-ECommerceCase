package org.atamertc;

import org.atamertc.repository.MusteriRepository;
import org.atamertc.repository.entity.ECinsiyet;
import org.atamertc.repository.entity.Musteri;
import org.atamertc.repository.entity.Urun;
import org.atamertc.services.MusteriService;
import org.atamertc.utility.HibernateUtility;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.EntityManager;
import javax.persistence.Tuple;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Arrays;
import java.util.List;

public class ETicaretApplication {
    public static void main(String[] args) {
        //save();
        //list();
        //criteriaList();

        //CriteriaOrnekKullanimlar cr = new CriteriaOrnekKullanimlar();
        //cr.findAll();
        //cr.selectOneColumn();
        //cr.selectManyColumn();
        //saveUrun();
        /*
        cr.selectManyTable().forEach(x -> {
            System.out.println(((Musteri) x.get(0)).toString() + ((Urun) x.get(1)).toString());
        });
         */
        //cr.parametreKullanim("Alperen");
        //cr.predicateKullanim();
        //cr.groupBy();
        //cr.nativeSqlKullanim();
        //cr.namedQueryFindAll();
        //cr.namedQueryFindByAd("Gulsu");
        //cr.namedQueryFindById(3);
        //cr.namedQueryFindAllPagination(0, 2);

//        MusteriRepository repository = new MusteriRepository();
//        repository.save(m1);

//        System.out.println(repository.findById(10).get());
//        System.out.println(repository.findAllByColumnNameAndValue("ad", "Atamert"));
//        repository.deleteById(6);
        MusteriService service = new MusteriService();
        service.findAll().forEach(x->{
            System.out.println(x.getId() + " " + x.getAd());
        });
//        Musteri m1 = Musteri.builder()
//                .ad("e")
//                .soyad("a")
//                .build();
//        repository.findByEntity(m1).forEach(x -> {
//            System.out.println(x.getId() + " " + x.getAd() + " " + x.getSoyad());
//        });
    }

    private static void criteriaList() {
        EntityManager entityManager = HibernateUtility.getSessionFactory().createEntityManager();
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Musteri> criteriaQuery = criteriaBuilder.createQuery(Musteri.class);
        Root<Musteri> root = criteriaQuery.from(Musteri.class);
        criteriaQuery.select(root);

        List<Musteri> musteriList = entityManager.createQuery(criteriaQuery).getResultList();
        musteriList.forEach(m -> {
            System.out.println("Musteri ad:" + m.getAd() + "| soyad: " + m.getSoyad());
        });
    }

    private static void list() {
        Session session = HibernateUtility.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(Musteri.class);
        List<Musteri> musteriList = criteria.list();
        session.close();
        musteriList.forEach(m -> {
            System.out.println("Musteri ad:" + m.getAd() + "| soyad: " + m.getSoyad());
        });
    }

    private static void save() {
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        List<String> telefonlist = Arrays.asList("545 264 54 53", "522 922 22 46");
        Musteri m1 = Musteri.builder()
                .ad("Atamert")
                .soyad("Cakir")
                .telefonlistesi(telefonlist)
                .cinsiyet(ECinsiyet.ERKEK)
                .build();

        session.save(m1);

        transaction.commit();
        session.close();
    }

    private static void saveUrun() {
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        Urun urun = Urun.builder()
                .ad("Sut")
                .fiyat(19.99)
                .marka("Pinar")
                .build();

        session.save(urun);

        transaction.commit();
        session.close();
    }
}