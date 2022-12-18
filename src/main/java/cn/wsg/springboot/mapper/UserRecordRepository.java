package cn.wsg.springboot.mapper;

import cn.wsg.springboot.pojo.entity.UserRecordEntity;
import java.util.List;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;

/**
 * @author Kingen
 */
@Repository
public interface UserRecordRepository extends JpaRepositoryImplementation<UserRecordEntity, Integer> {

    List<UserRecordEntity> findAllByUserId(int userId);
}
