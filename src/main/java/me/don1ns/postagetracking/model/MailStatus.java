package me.don1ns.postagetracking.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.don1ns.postagetracking.constant.Status;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class MailStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @Cascade(CascadeType.ALL)
    @JsonBackReference
    private Mail mail;
    @ManyToOne
    @Cascade(CascadeType.ALL)
    private PostOffice postOffice;
    @Enumerated
    private Status status;
    private LocalDateTime timestamp = LocalDateTime.now();
}
