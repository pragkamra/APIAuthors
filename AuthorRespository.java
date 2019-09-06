package com.test.persistance;
import java.lang.annotation.Native;
import java.util.*;

import javax.persistence.NamedNativeQuery;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.test.model.*;
public interface AuthorRespository extends JpaRepository<Authors, Long>{
	
	@Query(value = "SELECT * FROM tbl_author WHERE AUTHOR_NAME = :authorName", nativeQuery = true)
	Authors findByName(@Param("authorName") String authorName);

	
}
