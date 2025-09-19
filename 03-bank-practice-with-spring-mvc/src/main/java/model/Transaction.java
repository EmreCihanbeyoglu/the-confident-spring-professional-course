package model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class Transaction {
    private String id;

    private Integer amount;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime timestamp;

    private String reference;

    private String slogan;

    public Transaction(Integer amount, String reference, String slogan) {
        this.id = UUID.randomUUID().toString();
        this.amount = amount;
        this.timestamp = LocalDateTime.now();
        this.reference = reference;
        this.slogan = slogan;
    }
}
