package me.don1ns.postagetracking.controller;

import io.swagger.v3.oas.annotations.Operation;
import me.don1ns.postagetracking.constant.MailType;
import me.don1ns.postagetracking.constant.Status;
import me.don1ns.postagetracking.model.Mail;
import me.don1ns.postagetracking.model.MailStatus;
import me.don1ns.postagetracking.model.PostOffice;
import me.don1ns.postagetracking.service.MailService;
import me.don1ns.postagetracking.service.MailStatusService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/mail")
public class MailController {
    private final MailService mailService;
    private final MailStatusService mailStatusService;

    public MailController(MailService mailService, MailStatusService mailStatusService) {
        this.mailService = mailService;
        this.mailStatusService = mailStatusService;
    }


    @Operation(summary = "Регистрация почтового отправления")
    @PostMapping("/register")
    public ResponseEntity<Mail> register(@RequestParam MailType type,
                                               @RequestParam Long recipientIndex,
                                               @RequestParam String recipientAddress,
                                               @RequestParam String recipientFullName) {
        MailStatus mailStatus = new MailStatus();
        mailStatus.setStatus(Status.REGISTERED);
        List<MailStatus> mailStatusList = new ArrayList<>();
        mailStatusList.add(mailStatus);
        Mail mail = new Mail(type, recipientIndex,recipientAddress,recipientFullName,mailStatusList);
        mailStatus.setMail(mail);
        mailStatusService.register(mailStatus);
        return ResponseEntity.ok(mailService.save(mail));
    }

    @Operation(summary = "Прибытие в почтовое отделение")
    @PostMapping("/{id}/arrive")
    public ResponseEntity<Mail> arrive(@PathVariable Long id,@RequestBody PostOffice postOffice) {
        Mail mail = updateMailStatus(id, postOffice, Status.ARRIVED);
        return ResponseEntity.ok(mailService.update(mail));
    }

    @Operation(summary = "Убытие из почтого отделения")
    @PostMapping("/{id}depart")
    public ResponseEntity<Mail> depart(@PathVariable Long id,@RequestBody PostOffice postOffice) {
        Mail mail = updateMailStatus(id, postOffice, Status.DEPARTED);
        return ResponseEntity.ok(mailService.update(mail));
    }

    @Operation(summary = "Получение адресатом")
    @PostMapping("/{id}/receive")
    public ResponseEntity<Mail> receive(@PathVariable Long id,@RequestBody PostOffice postOffice) {
        Mail mail = updateMailStatus(id, postOffice, Status.RECEIVED);
        return ResponseEntity.ok(mailService.update(mail));
    }
    @Operation(summary = "Просмотр статуса и полной истории почтового отправления")
    @GetMapping("/{id}/check_status")
    public ResponseEntity<Collection<MailStatus>> check_status(@PathVariable Long id) {
        return ResponseEntity.ok(mailStatusService.checkStatus(id));
    }

    private Mail updateMailStatus(Long id, PostOffice postOffice, Status status) {
        Mail mail = mailService.getById(id);
        MailStatus mailStatus = new MailStatus();
        mailStatus.setStatus(status);
        mailStatus.setPostOffice(postOffice);
        mailStatus.setMail(mail);
        List<MailStatus> mailStatusList = mail.getMailStatus();
        mailStatusList.add(mailStatus);
        mail.setMailStatus(mailStatusList);
        return mail;
    }
}
