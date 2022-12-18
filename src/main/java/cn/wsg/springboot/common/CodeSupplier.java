package cn.wsg.springboot.common;

/**
 * Represents a supplier of an integer code.
 *
 * @author Kingen
 */
@FunctionalInterface
public interface CodeSupplier {

    /**
     * Returns the integer code.
     *
     * @return the integer code
     */
    int getCode();
}
