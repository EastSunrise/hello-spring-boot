package cn.wsg.springboot.pojo.entity;

import cn.wsg.springboot.pojo.enums.RecordType;
import java.time.LocalDateTime;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * @author Kingen
 */
@Getter
@Setter
@Entity
@ToString
@EntityListeners(AuditingEntityListener.class)
@Table(name = "user_record", schema = "hello")
public class UserRecordEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic
    @Column(name = "user_id", nullable = false)
    private Integer userId;
    @Basic
    @Column(name = "record_name", nullable = false, length = 16)
    private String recordName;
    @Basic
    @Column(name = "record_type", nullable = false)
    private RecordType recordType;
    @Basic
    @Column(name = "record_time", nullable = false)
    private LocalDateTime recordTime;
    @Basic
    @CreatedDate
    @Column(name = "gmt_created", nullable = false)
    private LocalDateTime gmtCreated;
    @Basic
    @LastModifiedDate
    @Column(name = "gmt_modified", nullable = false)
    private LocalDateTime gmtModified;
}
