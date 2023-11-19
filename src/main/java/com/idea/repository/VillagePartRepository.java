package com.idea.repository;

import com.idea.entity.VillagePart;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VillagePartRepository extends CrudRepository<VillagePart, Long> {
}
