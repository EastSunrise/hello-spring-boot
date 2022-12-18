package cn.wsg.springboot.common;


import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * Utility for Java enums.
 *
 * @author Kingen
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class EnumUtils {

    /**
     * Resolves the enum constant of the specified enum type matching the specified integer code.
     *
     * @throws IllegalArgumentException if no enum of the specified type matches the code
     * @see CodeSupplier
     */
    public static <E extends Enum<E> & CodeSupplier> E valueOfIntCode(Class<E> clazz, int code) {
        for (E e : clazz.getEnumConstants()) {
            if (e.getCode() == code) {
                return e;
            }
        }
        throw new IllegalArgumentException(String.format("Unknown code %d for '%s'", code, clazz));
    }
}
