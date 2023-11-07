package org.atamertc;

import org.atamertc.repository.entity.Musteri;
import org.atamertc.repository.entity.Urun;
import org.atamertc.utility.HibernateUtility;

import javax.persistence.EntityManager;
import javax.persistence.Tuple;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.List;

public class CriteriaOrnekKullanimlar {
    private EntityManager entityManager;
    private CriteriaBuilder criteriaBuilder;

    public CriteriaOrnekKullanimlar() {
        entityManager = HibernateUtility.getSessionFactory().createEntityManager();
        criteriaBuilder = entityManager.getCriteriaBuilder();
    }

    public List<Musteri> findAll() {
        // oncelikle kullanilacak olan sinifi criteriaQuery ye belirtiyoruz
        CriteriaQuery<Musteri> criteriaQuery = criteriaBuilder.createQuery(Musteri.class);
        // hangi tablo'ya select atacaksak onu yaziyoruz
        Root<Musteri> root = criteriaQuery.from(Musteri.class);
        // select islemini tanimladik root -> yildiz gibi gorev yapar
        criteriaQuery.select(root);

        List<Musteri> musteriList = entityManager.createQuery(criteriaQuery).getResultList();
        musteriList.forEach(m -> {
            System.out.println("Musteri id:" + m.getId() + " musteri ad:" + m.getAd() + " soyad: " + m.getSoyad());
        });
        return musteriList;
    }

    public void selectOneColumn() {
        //select musteriad from tblmusteri where soyad= 'Cakir'
        //geriye donecek tip string olmali
        CriteriaQuery<String> criteriaQuery = criteriaBuilder.createQuery(String.class);
        Root<Musteri> root = criteriaQuery.from(Musteri.class);
        criteriaQuery.select(root.get("ad"));
        criteriaQuery.where(criteriaBuilder.equal(root.get("soyad"), "Sertoglu"));

        List<String> adList = entityManager.createQuery(criteriaQuery).getResultList();
        adList.forEach(System.out::println);
    }

    public void selectManyColumn() {
        //select id, ad, soyad from tblmusteri
        CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);
        Root<Musteri> root = criteriaQuery.from(Musteri.class);

        Path<Integer> idPath = root.get("id");
        Path<String> adPath = root.get("ad");
        Path<String> soyadPath = root.get("soyad");

        criteriaQuery.select(criteriaBuilder.array(idPath, adPath, soyadPath));
        List<Object[]> mList = entityManager.createQuery(criteriaQuery).getResultList();
        mList.stream().forEach(x -> {
            for (Object o : x) {
                System.out.print(o + " ");
            }
            System.out.println();
        });
    }

    public void tupleOrnek() {
        //select id, ad, soyad from tblmusteri
        CriteriaQuery<Tuple> criteriaQuery = criteriaBuilder.createQuery(Tuple.class);
        Root<Musteri> root = criteriaQuery.from(Musteri.class);

        Path<Integer> idPath = root.get("id");
        Path<String> adPath = root.get("ad");
        Path<String> soyadPath = root.get("soyad");

        criteriaQuery.multiselect(idPath, adPath, soyadPath);
        List<Tuple> mList = entityManager.createQuery(criteriaQuery).getResultList();
        mList.stream().forEach(x -> {
            System.out.println(x.get(idPath) + " " + x.get(1) + " " + x.get(2));
        });
    }

    public List<Tuple> selectManyTable() {
        //select id, ad, soyad from tblmusteri
        CriteriaQuery<Tuple> criteriaQuery = criteriaBuilder.createQuery(Tuple.class);
        Root<Musteri> musteriRoot = criteriaQuery.from(Musteri.class);
        Root<Urun> urunRoot = criteriaQuery.from(Urun.class);

        criteriaQuery.multiselect(musteriRoot, urunRoot);
        List<Tuple> tupleList = entityManager.createQuery(criteriaQuery).getResultList();
        return tupleList;
    }

    public void parametreKullanim(String musteriAd) {
        CriteriaQuery<Musteri> criteriaQuery = criteriaBuilder.createQuery(Musteri.class);
        Root<Musteri> root = criteriaQuery.from(Musteri.class);

        ParameterExpression<String> parameter = criteriaBuilder.parameter(String.class);
        criteriaQuery.where(criteriaBuilder.equal(root.get("ad"), parameter));

        TypedQuery<Musteri> typedQuery = entityManager.createQuery(criteriaQuery);
        typedQuery.setParameter(parameter, musteriAd);

        List<Musteri> musteriList = typedQuery.getResultList();
        musteriList.forEach(x -> {
            System.out.println(x.getId() + " " + x.getAd());
        });
    }

    public void predicateKullanim() {
        //select * from tblmusteri where (ad like 'G%' and soyad is not null) or id > 2
        CriteriaQuery<Musteri> criteriaQuery = criteriaBuilder.createQuery(Musteri.class);
        Root<Musteri> root = criteriaQuery.from(Musteri.class);

        Predicate predicate1 = criteriaBuilder.and(
                criteriaBuilder.like(root.get("ad"), "G%"),
                criteriaBuilder.isNotNull(root.get("soyad"))
        );
        Predicate predicate2 = criteriaBuilder.greaterThan(root.get("id"), 2);
        criteriaQuery.where(criteriaBuilder.or(predicate1, predicate2));

        List<Musteri> musteriList = entityManager.createQuery(criteriaQuery).getResultList();
        musteriList.forEach(x -> {
            System.out.println(x.getId() + " " + x.getAd());
        });
    }

    public void groupBy() {
        //select cinsiyet,count(cinsiyet) from tblmusteri groupby cinsiyet
        CriteriaQuery<Tuple> criteriaQuery = criteriaBuilder.createQuery(Tuple.class);
        Root<Musteri> root = criteriaQuery.from(Musteri.class);

        criteriaQuery.groupBy(root.get("cinsiyet"));
        criteriaQuery.multiselect(root.get("cinsiyet"), criteriaBuilder.count(root));

        List<Tuple> list = entityManager.createQuery(criteriaQuery).getResultList();
        list.forEach(x -> {
            System.out.println(x.get(0) + " " + x.get(1));
        });
    }

    //Native SQL ile direk sql sorgusunu hibernate e gondermek
    public void nativeSqlKullanim() {
        String SQL = "select * from tblmusteri";
        List<Object[]> musteriList = entityManager.createNativeQuery(SQL).getResultList();
        musteriList.forEach(x -> {
            for (Object o : x) {
                System.out.print(o + " ");
            }
            System.out.println();
        });
    }

    //HQL : Hibernate Query Language
    //NamedQueryler HQL kullanilarak yazilir.
    public void namedQueryFindAll() {
        TypedQuery<Musteri> typedQuery = entityManager.createNamedQuery("Musteri.findAll", Musteri.class);
        List<Musteri> musteriList = typedQuery.getResultList();
        musteriList.forEach(x -> {
            System.out.println(x.getId() + " " +x.getAd() + " " + x.getSoyad());
        });
    }

    public void namedQueryFindAllPagination(int page, int count) {
        TypedQuery<Musteri> typedQuery = entityManager.createNamedQuery("Musteri.findAll", Musteri.class);
        typedQuery.setMaxResults(count);
        typedQuery.setFirstResult(page * count);
        List<Musteri> musteriList = typedQuery.getResultList();
        musteriList.forEach(x -> {
            System.out.println(x.getId() + " " +x.getAd() + " " + x.getSoyad());
        });
    }

    public void namedQueryFindByAd(String ad) {
        TypedQuery<Musteri> typedQuery = entityManager.createNamedQuery("Musteri.findByAd", Musteri.class);
        typedQuery.setParameter("birdegiskenadi", ad);
        List<Musteri> musteriList = typedQuery.getResultList();
        musteriList.forEach(x -> {
            System.out.println(x.getId() + " " +x.getAd() + " " + x.getSoyad());
        });
    }

    public void namedQueryFindById(Integer id) {
        TypedQuery<Musteri> typedQuery = entityManager.createNamedQuery("Musteri.findById", Musteri.class);
        typedQuery.setParameter("musteriid", id);
        List<Musteri> musteriList = typedQuery.getResultList();
        System.out.println(musteriList.get(0) + " " + musteriList.get(1));
    }

    public void namedQueryGetCount() {
        TypedQuery<Long> typedQuery = entityManager.createNamedQuery("Musteri.getCount", Long.class);
        Long count = typedQuery.getSingleResult();
        System.out.println("Musteri sayisi+ " + count);
    }

    public void namedQueryGetAdSoyad() {
        TypedQuery<String> typedQuery = entityManager.createNamedQuery("Musteri.getAdSoyad", String.class);
        List<String> adsoyad = typedQuery.getResultList();
        adsoyad.forEach(System.out::println);
    }


}



















