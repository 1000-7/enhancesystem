package org.whusim.enhancesystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.whusim.enhancesystem.bean.Mention;

import java.util.List;


public interface MentionRepository extends PagingAndSortingRepository<Mention, Integer> {
    Mention findByMentionvalue(String mentionvalue);
}
