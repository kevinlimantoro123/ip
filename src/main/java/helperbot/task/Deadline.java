package helperbot.task;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import java.util.Arrays;
import java.util.List;

public class Deadline extends Task {
    protected LocalDateTime dateTime;

    public Deadline(String description, String dateTime) {
        super(description, TaskType.DEADLINE);
        this.dateTime = parseDateTime(dateTime);
    }

    private LocalDateTime parseDateTime(String dateTime) {
        List<DateTimeFormatter> dateFormatters = Arrays.asList(
                DateTimeFormatter.ofPattern("yyyy-MM-dd"),
                DateTimeFormatter.ofPattern("yyyy/MM/dd"),
                DateTimeFormatter.ofPattern("dd-MM-yyyy"),
                DateTimeFormatter.ofPattern("dd/MM/yyyy"),
                DateTimeFormatter.ofPattern("MMM dd yyyy")
        );
        List<DateTimeFormatter> dateTimeFormatters = Arrays.asList(
                DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"),
                DateTimeFormatter.ofPattern("yyyy/MM/dd HHmm"),
                DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm"),
                DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"),
                DateTimeFormatter.ofPattern("MMM dd yyyy hh:mma")
        );

        for (DateTimeFormatter formatter : dateTimeFormatters) {
            try {
                return LocalDateTime.parse(dateTime, formatter);
            } catch (DateTimeException e) {
                // Continue to next formatter
            }
        }

        for (DateTimeFormatter formatter : dateFormatters) {
            try {
                return LocalDate.parse(dateTime, formatter).atStartOfDay();
            } catch (DateTimeParseException e) {
                // Continue to next formatter
            }
        }

        throw new IllegalArgumentException
                ("Invalid date format. Please use yyyy-MM-dd, yyyy/MM/dd, dd-MM-yyyy, or dd/MM/yyyy");
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter;
        if (dateTime.toLocalTime().equals(LocalDateTime.MIN.toLocalTime())) {
            formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        } else {
            formatter = DateTimeFormatter.ofPattern("MMM dd yyyy hh:mma");
        }
        return "[D]" + super.toString() + " (by: " + dateTime.format(formatter) + ")";
    }
}