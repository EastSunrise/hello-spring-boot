package cn.wsg.springboot.common;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

/**
 * Pagination of a request.
 *
 * @author Kingen
 */
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Pagination implements Pageable {

    private static final Sort DEFAULT_SORT = Sort.by(Direction.DESC, "gmtModified");
    private static final int DEFAULT_PAGE_SIZE = 20;

    private int pageNumber = 0;
    private int pageSize = DEFAULT_PAGE_SIZE;

    @Override
    public int getPageNumber() {
        return pageNumber;
    }

    @Override
    public int getPageSize() {
        return pageSize;
    }

    @Override
    public long getOffset() {
        return (long) pageNumber * (long) pageSize;
    }

    @Override
    public Sort getSort() {
        return DEFAULT_SORT;
    }

    @Override
    public Pagination next() {
        return new Pagination(getPageNumber() + 1, getPageSize());
    }

    @Override
    public Pagination previousOrFirst() {
        return hasPrevious() ? previous() : first();
    }

    public Pagination previous() {
        return getPageNumber() == 0 ? this : new Pagination(getPageNumber() - 1, getPageSize());
    }

    @Override
    public Pagination first() {
        return new Pagination(0, getPageSize());
    }

    @Override
    public Pagination withPage(int pageNumber) {
        return new Pagination(pageNumber, getPageSize());
    }

    @Override
    public boolean hasPrevious() {
        return pageNumber > 0;
    }
}
