package cn.wsg.springboot.config;

import cn.wsg.springboot.common.CodeEnumAttributeConverter;
import cn.wsg.springboot.pojo.enums.RecordType;
import javax.persistence.Converter;

/**
 * Converters used to convert entity attribute state into database column representation and back again.
 *
 * @author Kingen
 */
public class JpaAttributeConverters {

    @Converter(autoApply = true)
    public static class RecordTypeAttributeConverter extends CodeEnumAttributeConverter<RecordType> {

        public RecordTypeAttributeConverter() {
            super(RecordType.class);
        }
    }
}
