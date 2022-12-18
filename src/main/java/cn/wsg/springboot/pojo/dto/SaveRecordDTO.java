package cn.wsg.springboot.pojo.dto;

import cn.wsg.springboot.pojo.enums.RecordType;
import java.time.LocalDateTime;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Parameters to save a record.
 */
@Getter
@Setter
@ToString
public class SaveRecordDTO {

    @NotNull
    @Positive
    private Integer userId;
    @NotBlank
    private String recordName;
    @NotNull
    private RecordType recordType;
    @NotNull
    private LocalDateTime recordTime;
}
