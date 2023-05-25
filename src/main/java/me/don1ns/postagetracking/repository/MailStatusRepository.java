package me.don1ns.postagetracking.repository;

import me.don1ns.postagetracking.model.MailStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MailStatusRepository extends JpaRepository<MailStatus, Long> {
}
