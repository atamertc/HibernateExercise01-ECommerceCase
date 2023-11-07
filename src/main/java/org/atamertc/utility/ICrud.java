package org.atamertc.utility;

import java.util.List;
import java.util.Optional;

public interface ICrud<T, ID> extends IMyRepository<T, ID> {
    // Kaydetme Islemleri
    <S extends T> S save(S entity);
    <S extends T> Iterable<S> saveAll(Iterable<S> entities);

    // Silme Islemleri
    boolean delete(T entity);
    boolean deleteById(ID id);

    // Arama ve Listeleme Islemleri
    Optional<T> findById(ID id);
    /**
     * Bu metod entity icinde bulunan herhangi bir alana gore otomatik sorgu yapacak.
     * Recleftion API kullanilacak.
     *
     * @param entity
     * @return
     */
    List<T> findByEntity(T entity);
    boolean existById(ID id);
    List<T> findAll();
    List<T> findAllByColumnNameAndValue(String column, String columnValue);

}
