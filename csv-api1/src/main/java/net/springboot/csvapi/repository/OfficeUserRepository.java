package net.springboot.csvapi.repository;

import net.springboot.csvapi.model.OfficeUser;
import org.springframework.data.jpa.repository.JpaRepository;

//import net.springboot.csvapi.model.OfficeUser;

public interface OfficeUserRepository extends JpaRepository<OfficeUser, Long> {
}
