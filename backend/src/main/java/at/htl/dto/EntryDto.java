package at.htl.dto;

import java.time.LocalDate;

public record EntryDto(
        Long id,
        Double amount,
        String reason,
        Boolean edited,
        LocalDate date
) {
}
