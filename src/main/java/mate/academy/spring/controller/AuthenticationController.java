package mate.academy.spring.controller;

import jakarta.validation.Valid;
import mate.academy.spring.mapper.impl.response.UserResponseMapper;
import mate.academy.spring.model.User;
import mate.academy.spring.model.dto.request.AuthenticationRequestDto;
import mate.academy.spring.model.dto.response.UserResponseDto;
import mate.academy.spring.security.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    private final UserResponseMapper userResponseMapper;

    @Autowired
    public AuthenticationController(AuthenticationService authenticationService,
                                    UserResponseMapper userResponseMapper) {
        this.authenticationService = authenticationService;
        this.userResponseMapper = userResponseMapper;
    }

    @PostMapping("/register")
    public UserResponseDto add(@RequestBody @Valid
                                   AuthenticationRequestDto authenticationRequestDto) {
        User registeredUser = authenticationService.register(authenticationRequestDto.getEmail(),
                authenticationRequestDto.getPassword());
        return userResponseMapper.toDto(registeredUser);
    }
}
