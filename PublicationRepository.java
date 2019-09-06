package com.test.persistance;

import org.springframework.data.jpa.repository.JpaRepository;


import com.test.model.Publications;

public interface PublicationRepository extends JpaRepository<Publications, Long>{

}
