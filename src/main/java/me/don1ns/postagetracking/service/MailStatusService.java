package me.don1ns.postagetracking.service;

import me.don1ns.postagetracking.model.MailStatus;
import me.don1ns.postagetracking.repository.MailStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class MailStatusService{
    private final MailStatusRepository mailStatusRepository;
    @Autowired
    public MailStatusService(MailStatusRepository mailStatusRepository) {
        this.mailStatusRepository = mailStatusRepository;
    }

    public MailStatus register(MailStatus mailStatus) {
        return mailStatusRepository.save(mailStatus);
    }
    public Collection<MailStatus> checkStatus(Long id) {
        return mailStatusRepository.findAll().stream()
                .filter(c -> c.getMail().getId().equals(id))
                .collect(Collectors.toList());
    }

}
