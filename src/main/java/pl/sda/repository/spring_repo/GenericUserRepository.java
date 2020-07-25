package pl.sda.repository.spring_repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;
import pl.sda.repository.domain.SdaUser;

import java.util.Optional;

@NoRepositoryBean
public interface GenericUserRepository extends CrudRepository<SdaUser, String> {
    Optional<SdaUser> findByPesel(String pesel);
}
