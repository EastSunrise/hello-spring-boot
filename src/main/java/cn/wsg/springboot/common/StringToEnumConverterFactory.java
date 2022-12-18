package cn.wsg.springboot.common;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;
import org.springframework.util.Assert;

/**
 * A factory for converters that can convert a {@link String} to an {@link Enum}.
 * <p>
 * Compares the string in turn with
 * <ul>
 *     <li>{@link Displayable#getDisplayName()} if this {@code Enum} is assignable from {@link Displayable};</li>
 *     <li>{@link Enum#name()}, ignoring case.</li>
 * </ul>
 *
 * @author Kingen
 */
@NoArgsConstructor
public class StringToEnumConverterFactory implements ConverterFactory<String, Enum<?>> {

    private final Map<Class<?>, Converter<String, ? extends Enum<?>>> cachedConverters = new ConcurrentHashMap<>();

    @Override
    @SuppressWarnings("unchecked")
    public <T extends Enum<?>> Converter<String, T> getConverter(Class<T> targetType) {
        Converter<String, T> converter = (Converter<String, T>) cachedConverters.get(targetType);
        if (converter == null) {
            converter = new StringToEnumConverter<>(targetType);
            cachedConverters.put(targetType, converter);
        }
        return converter;
    }

    private static class StringToEnumConverter<T extends Enum<?>> implements Converter<String, T> {

        private final Class<T> enumType;

        StringToEnumConverter(Class<T> enumType) {
            Assert.notNull(enumType, "The type of enums must not be null");
            this.enumType = enumType;
        }

        @Override
        public T convert(String source) {
            if (StringUtils.isBlank(source)) {
                return null;
            }
            T[] ts = enumType.getEnumConstants();
            if (Displayable.class.isAssignableFrom(enumType)) {
                for (T t : ts) {
                    if (StringUtils.equals(((Displayable) t).getDisplayName(), source)) {
                        return t;
                    }
                }
            }
            for (T t : ts) {
                if (StringUtils.equalsIgnoreCase(t.name(), source)) {
                    return t;
                }
            }
            throw new IllegalArgumentException(String.format("Unknown string %s for %s", source, enumType));
        }
    }
}
