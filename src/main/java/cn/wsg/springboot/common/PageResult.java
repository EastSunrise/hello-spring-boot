package cn.wsg.springboot.common;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.util.Assert;

/**
 * A result with pagination.
 *
 * @author Kingen
 */
public class PageResult<T> {

    private final Page<T> page;

    public PageResult(Page<T> page) {
        Assert.notNull(page, "Page of a result must not be null");
        this.page = page;
    }

    /**
     * Returns data within this result.
     */
    public List<T> getData() {
        return page.getContent();
    }

    /**
     * Returns pagination of this result.
     */
    public SimplePage getPage() {
        return new SimplePage(page);
    }

    private static class SimplePage {

        private final Page<?> page;

        private SimplePage(Page<?> page) {
            this.page = page;
        }

        public int getPageNumber() {
            return page.getNumber();
        }

        public int getPageSize() {
            return page.getSize();
        }

        public int getTotalPages() {
            return page.getTotalPages();
        }

        public long getTotalElements() {
            return page.getTotalElements();
        }
    }
}
