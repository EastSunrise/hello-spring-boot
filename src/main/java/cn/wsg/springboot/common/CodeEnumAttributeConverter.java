package cn.wsg.springboot.common;

import javax.persistence.AttributeConverter;
import org.springframework.util.Assert;

/**
 * This class provides a skeletal implementation of {@link AttributeConverter} for enums that are stored as
 * <em>unique</em> integer codes (returned by {@link CodeSupplier#getCode()} ()}) in the database.
 *
 * @param <E> type of enums
 * @author Kingen
 */
public class CodeEnumAttributeConverter<E extends Enum<E> & CodeSupplier> implements AttributeConverter<E, Integer> {

    private final Class<E> enumType;

    public CodeEnumAttributeConverter(Class<E> enumType) {
        Assert.notNull(enumType, "The type of enums must not be null");
        this.enumType = enumType;
    }

    @Override
    public Integer convertToDatabaseColumn(E attribute) {
        if (attribute == null) {
            return null;
        }
        return attribute.getCode();
    }

    @Override
    public E convertToEntityAttribute(Integer dbData) {
        if (dbData == null) {
            return null;
        }
        return EnumUtils.valueOfIntCode(enumType, dbData);
    }
}
