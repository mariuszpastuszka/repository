package pl.sda.repository.repo_spring;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sda.repository.domain.SdaUser;

public interface MyUserRepo extends JpaRepository<SdaUser, String> {

}
