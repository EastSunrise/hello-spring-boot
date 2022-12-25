package cn.wsg.springboot.pojo.dto;

import cn.wsg.springboot.pojo.enums.RecordType;
import java.time.YearMonth;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Conditions to query records.
 *
 * @author Kingen
 */
@Getter
@Setter
@ToString
public class QueryRecordDTO {

    private String username;
    private String recordName;
    private RecordType recordType;
    private YearMonth month;
}
