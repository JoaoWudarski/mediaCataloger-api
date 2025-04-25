package com.joaowudarski.host;


import com.joaowudarski.host.data.request.LoginRequest;
import com.joaowudarski.host.data.request.RegisterRequest;
import com.joaowudarski.host.data.response.LoginResponse;
import com.joaowudarski.usecase.LoginUser;
import com.joaowudarski.usecase.RegisterUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserEndpoint {

    private final LoginUser loginUser;
    private final RegisterUser registerUser;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(loginUser.execute(loginRequest));
    }

    @PostMapping("/register")
    public ResponseEntity<LoginResponse> register(@RequestBody RegisterRequest registerRequest) {
        return ResponseEntity.ok(registerUser.execute(registerRequest));
    }
}
