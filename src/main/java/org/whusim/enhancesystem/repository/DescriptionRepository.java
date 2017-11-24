package org.whusim.enhancesystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.whusim.enhancesystem.bean.Description;


public interface DescriptionRepository extends JpaRepository<Description, Integer> {
}
