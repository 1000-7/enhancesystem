package org.whusim.enhancesystem.repository;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.whusim.enhancesystem.bean.Tag;

import java.util.List;


public interface TagRepository extends PagingAndSortingRepository<Tag,Integer> {
    List<Tag> findAllByEntityid(int entityid);
}
