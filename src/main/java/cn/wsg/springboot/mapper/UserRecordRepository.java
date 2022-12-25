package cn.wsg.springboot.mapper;

import cn.wsg.springboot.pojo.entity.UserRecordEntity;
import cn.wsg.springboot.pojo.entity.UserRecordEntity_;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;

/**
 * @author Kingen
 */
@Repository
public interface UserRecordRepository extends JpaRepositoryImplementation<UserRecordEntity, Integer> {

    List<UserRecordEntity> findAllByUserId(int userId);

    @Override
    @EntityGraph(type = EntityGraphType.FETCH, attributePaths = UserRecordEntity_.USER)
    Page<UserRecordEntity> findAll(Specification<UserRecordEntity> spec, Pageable pageable);
}
