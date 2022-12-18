package cn.wsg.springboot.common;

/**
 * Indicates that an object is displayable on Web Pages.
 *
 * @author Kingen
 */
@FunctionalInterface
public interface Displayable {

    /**
     * Returns the display name of the object.
     *
     * @return the display name
     */
    String getDisplayName();
}
