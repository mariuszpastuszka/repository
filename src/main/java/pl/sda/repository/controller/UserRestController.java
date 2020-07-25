package pl.sda.repository.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.sda.repository.domain.SdaUser;
import pl.sda.repository.service.SdaUserService;

import java.util.List;

@RestController
public class UserRestController {

    private SdaUserService sdaUserService;

    public UserRestController(SdaUserService sdaUserService) {
        this.sdaUserService = sdaUserService;
    }

    @GetMapping("all-users")
    public List<SdaUser> readAllUsers() {
        return sdaUserService.allUsers();
    }
}
