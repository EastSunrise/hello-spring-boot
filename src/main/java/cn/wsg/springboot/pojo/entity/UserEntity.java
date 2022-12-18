package cn.wsg.springboot.pojo.entity;

import java.time.LocalDateTime;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

/**
 * @author Kingen
 */
@Getter
@Setter
@Entity
@ToString
@Table(name = "user", schema = "hello")
public class UserEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic
    @Column(name = "username", nullable = false, length = 8)
    private String username;
    @Basic
    @Column(name = "password", nullable = false, length = 64)
    private String password;
    @Basic
    @CreatedDate
    @Column(name = "gmt_created", nullable = false)
    private LocalDateTime gmtCreated;
    @Basic
    @LastModifiedDate
    @Column(name = "gmt_modified", nullable = false)
    private LocalDateTime gmtModified;
}
