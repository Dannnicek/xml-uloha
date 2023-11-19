package com.idea.repository;

import com.idea.entity.Village;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VillageRepository extends CrudRepository<Village, Long> {
    Village findByCode(String code);
}
