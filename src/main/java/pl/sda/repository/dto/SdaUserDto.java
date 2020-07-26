package pl.sda.repository.dto;

import lombok.Builder;
import lombok.Data;
import pl.sda.repository.domain.SdaUser;

import java.util.List;

@Data
@Builder
public class SdaUserDto {
    private List<SdaUser> sdaUsers;
    private int pageNumber;
    private int pageSize;
    private int numberOfPages;
}
