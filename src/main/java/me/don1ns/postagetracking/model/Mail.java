package me.don1ns.postagetracking.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.don1ns.postagetracking.constant.MailType;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Mail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated
    private MailType type;
    private Long recipientIndex;
    private String recipientAddress;
    private String recipientFullName;
    @OneToMany(mappedBy = "mail")
    @Cascade(CascadeType.ALL)
    @JsonManagedReference
    private List<MailStatus> mailStatus = new ArrayList<>();

    public Mail(MailType type, Long recipientIndex,
                String recipientAddress, String recipientFullName,
                List<MailStatus> mailStatus) {
        this.type = type;
        this.recipientIndex = recipientIndex;
        this.recipientAddress = recipientAddress;
        this.recipientFullName = recipientFullName;
        this.mailStatus = mailStatus;
    }
}
