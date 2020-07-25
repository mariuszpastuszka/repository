package pl.sda.repository.spring_repo;

import org.hibernate.annotations.Parameter;
import org.springframework.data.jpa.repository.Query;
import pl.sda.repository.domain.SdaUser;

import java.util.Optional;

public interface SdaUserRepository extends GenericUserRepository {
    @Query(value = "select *" +
            "from SDA_USER " +
            "where PESEL = :pesel", nativeQuery = true)
    Optional<SdaUser> findByPeselAndName(@Parameter(name = "pesel") String pesel, String name);

}
