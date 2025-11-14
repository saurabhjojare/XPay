package com.xpay.userservice.utility;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Comparator;
import java.util.List;

public class PaginationUtil {
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class PaginatedResponse<T> {
        private List<T> content;
        private int totalItems;
        private int totalPages;
        private int currentPage;
        private int pageSize;
        private String sortBy;
        private String sortDir;
    }

    public static <T> PaginatedResponse<T> paginate
            (List<T> fullList, int page, int size, String sortBy, String sortDir) {
        if (sortBy != null && !sortBy.isEmpty()) {
            Comparator<T> comparator = Comparator.comparing(item -> {
                try {
                    var field = item.getClass().getDeclaredField(sortBy);
                    field.setAccessible(true);
                    Object fieldValue = field.get(item);

                    // Return a Comparable wrapper that handles the comparison safely
                    return new ComparableWrapper(fieldValue);
                } catch (Exception e) {
                    throw new RuntimeException("Invalid sort field: " + sortBy, e);
                }
            });

            if ("desc".equalsIgnoreCase(sortDir)) {
                fullList.sort(comparator.reversed());
            } else {
                fullList.sort(comparator);
            }
        }

        int totalItems = fullList.size();
        int totalPages = (int) Math.ceil((double) totalItems / size);
        int fromIndex = Math.min(page * size, totalItems);
        int toIndex = Math.min(fromIndex + size, totalItems);

        List<T> paginatedList = fullList.subList(fromIndex, toIndex);

        return new PaginatedResponse<>(paginatedList, totalItems, totalPages, page, size, sortBy, sortDir);
    }

    // Wrapper class to handle comparison safely without unchecked casts
    private static class ComparableWrapper implements Comparable<ComparableWrapper> {
        private final Object value;

        public ComparableWrapper(Object value) {
            this.value = value;
        }

        @Override
        public int compareTo(ComparableWrapper other) {
            if (this.value == null && other.value == null) {
                return 0;
            }
            if (this.value == null) {
                return -1;
            }
            if (other.value == null) {
                return 1;
            }

            // Check if both values are Comparable
            if (this.value instanceof Comparable && other.value instanceof Comparable) {
                try {
                    // This is safe because we're comparing objects of the same type
                    Comparable thisComparable = (Comparable) this.value;
                    return thisComparable.compareTo(other.value);
                } catch (ClassCastException e) {
                    // Fall back to string comparison if types don't match
                    return this.value.toString().compareTo(other.value.toString());
                }
            }

            // Fall back to string comparison for non-Comparable objects
            return this.value.toString().compareTo(other.value.toString());
        }
    }
}