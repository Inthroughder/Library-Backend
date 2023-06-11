package ru.nsu.ccfit.databases.g20202.tsarev.libraryproject.security;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nsu.ccfit.databases.g20202.tsarev.libraryproject.dto.UserDTO;

import java.util.*;

@Service
@RequiredArgsConstructor
public class UserService {

    private final List<UserDTO> users;

    public UserService() {
        Set<Role> roles = new HashSet<>();
        roles.add(Role.ADMIN);
        roles.add(Role.USER);
        this.users = List.of(
                new UserDTO("admin", "admin", "Admin", "Admin", roles),
                new UserDTO("user", "user", "User", "User", Collections.singleton(Role.USER))
        );
    }

    public Optional<UserDTO> getByLogin(@NonNull String login) {
        return users.stream()
                .filter(user -> login.equals(user.getLogin()))
                .findFirst();
    }

}
