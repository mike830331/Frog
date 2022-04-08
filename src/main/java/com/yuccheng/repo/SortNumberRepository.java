package com.yuccheng.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.yuccheng.model.SortNumber;

@Repository
public interface SortNumberRepository extends JpaRepository<SortNumber, String> {

	@Query("from SortNumber where user_name =?1")
	public List<SortNumber> findByUsername(String name);

}
