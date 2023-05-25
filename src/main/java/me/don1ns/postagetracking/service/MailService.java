package me.don1ns.postagetracking.service;

import me.don1ns.postagetracking.model.Mail;
import me.don1ns.postagetracking.repository.MailRepository;
import org.springframework.stereotype.Service;


@Service
public class MailService{
    private final MailRepository mailRepository;

    public MailService(MailRepository mailRepository) {
        this.mailRepository = mailRepository;
    }

    public Mail save(Mail mail) {
        return mailRepository.save(mail);
    }

    public Mail getById(Long id) {
        return mailRepository.findById(id).orElseThrow();
    }

    public Mail update(Mail mail) {
        return mailRepository.save(mail);
    }
}
