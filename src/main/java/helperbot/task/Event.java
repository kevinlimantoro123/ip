package helperbot.task;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;

/**
 * Represents an event task.
 */
public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;
    protected DateTimeFormatter fromFormatter;
    protected DateTimeFormatter toFormatter;
    private final int priority;

    /**
     * Constructor for Event.
     *
     * @param description Description of the event
     * @param from Starting time of the event
     * @param to Ending time of the event
     */
    public Event(String description, String from, String to, int priority) {
        super(description, TaskType.EVENT, priority);
        this.from = parseDateTime(from);
        this.to = parseDateTime(to);
        this.priority = priority;

        if (this.to.isBefore(this.from)) {
            throw new IllegalArgumentException("End date cannot be before start date");
        }

        if (this.from.toLocalTime().equals(LocalDateTime.MIN.toLocalTime())) {
            fromFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        } else {
            fromFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy hh:mma");
        }
        if (this.to.toLocalTime().equals(LocalDateTime.MIN.toLocalTime())) {
            toFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        } else {
            toFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy hh:mma");
        }
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
        throw new IllegalArgumentException(
            "Invalid date format. Please use yyyy-MM-dd, yyyy/MM/dd, dd-MM-yyyy, or dd/MM/yyyy");
    }
    /**
     * Returns the string representation of the event task.
     *
     * @return String representation of the event task
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from.format(fromFormatter)
            + " to: " + to.format(toFormatter) + ")" + " (Priority: " + priority + ")";
    }
}
