package cn.wsg.springboot.pojo.enums;

import cn.wsg.springboot.common.CodeSupplier;
import cn.wsg.springboot.common.Displayable;
import lombok.Getter;

/**
 * Type of records.
 *
 * @author Kingen
 */
@Getter
public enum RecordType implements Displayable, CodeSupplier {
    INVALID(5, "invalid"),
    VALID(6, "valid");

    private final int code;
    private final String displayName;

    RecordType(int code, String displayName) {
        this.code = code;
        this.displayName = displayName;
    }
}
