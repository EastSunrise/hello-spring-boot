package cn.wsg.springboot.mapper;

import cn.wsg.springboot.pojo.entity.AdminUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Kingen
 */
@Repository
public interface AdminUserRepository extends JpaRepository<AdminUserEntity, Integer> {

    AdminUserEntity findByUsername(String username);
}
