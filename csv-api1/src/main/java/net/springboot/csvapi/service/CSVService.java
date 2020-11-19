package net.springboot.csvapi.service;

import net.springboot.csvapi.helper.CSVHelper;
import net.springboot.csvapi.model.OfficeUser;
import net.springboot.csvapi.repository.OfficeUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class CSVService {
    @Autowired
    OfficeUserRepository repository;

    public void save(MultipartFile file) {
        try {
            List<OfficeUser> users = CSVHelper.csvToOfficeUsers(file.getInputStream());
            repository.saveAll(users);
        } catch (IOException e) {
            throw new RuntimeException("fail to store csv data: " + e.getMessage());
        }
    }

    public List<OfficeUser> getAllOfficeUsers() {
        return repository.findAll();
    }
}
