package com.xpay.userservice.utility;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Comparator;
import java.util.List;

public class PaginationUtil {
    @Data
    @AllArgsConstructor
    public static class PaginatedResponse<T> {
        private List<T> content;
        private int totalItems;
        private int totalPages;
        private int currentPage;
        private int pageSize;
        private String sortBy;
        private String sortDir;
    }

    public static <T> PaginatedResponse<T> paginate(List<T> fullList, int page, int size, String sortBy, String sortDir) {
        if (sortBy != null && !sortBy.isEmpty()) {
            @SuppressWarnings("unchecked")
            Comparator<T> comparator = Comparator.comparing(item -> {
                try {
                    var field = item.getClass().getDeclaredField(sortBy);
                    field.setAccessible(true);
                    return (Comparable<Object>) field.get(item);
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
}
