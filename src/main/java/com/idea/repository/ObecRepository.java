package com.idea.repository;

import com.idea.entity.Obec;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ObecRepository extends CrudRepository<Obec, Long> {
    Obec findByCode(String code);
}
