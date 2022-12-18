package cn.wsg.springboot.common;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;
import org.springframework.util.Assert;

/**
 * A factory for converters that can convert an integer code to an {@link Enum} matching the code.
 *
 * @author Kingen
 */
public class CodeToEnumConverterFactory implements ConverterFactory<Integer, Enum<?>> {

    private final Map<Class<?>, Converter<Integer, ? extends Enum<?>>> cachedConverters = new ConcurrentHashMap<>();

    @Override
    @SuppressWarnings("unchecked")
    public <T extends Enum<?>> Converter<Integer, T> getConverter(Class<T> targetType) {
        Converter<Integer, T> converter = (Converter<Integer, T>) cachedConverters.get(targetType);
        if (converter == null) {
            converter = new IntToEnumConverter<>(targetType);
            cachedConverters.put(targetType, converter);
        }
        return converter;
    }

    private static class IntToEnumConverter<T extends Enum<?>> implements Converter<Integer, T> {

        private final Class<T> enumType;

        IntToEnumConverter(Class<T> enumType) {
            Assert.notNull(enumType, "The type of enums must not be null");
            this.enumType = enumType;
        }

        @Override
        public T convert(Integer source) {
            T[] ts = enumType.getEnumConstants();
            if (CodeSupplier.class.isAssignableFrom(enumType)) {
                for (T t : ts) {
                    if (((CodeSupplier) t).getCode() == source) {
                        return t;
                    }
                }
            }
            throw new IllegalArgumentException(String.format("Unknown code %d for %s", source, enumType));
        }
    }
}
