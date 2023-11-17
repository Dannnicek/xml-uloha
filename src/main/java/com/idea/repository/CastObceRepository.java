package com.idea.repository;

import com.idea.entity.CastObce;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CastObceRepository extends CrudRepository<CastObce, Long> {
}
