package net.springboot.csvapi.repository;

import net.springboot.csvapi.model.OfficeUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OfficeUserRepository extends JpaRepository<OfficeUser, Long> {
}
