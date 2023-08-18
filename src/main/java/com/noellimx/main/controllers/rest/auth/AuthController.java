package com.noellimx.main.controllers.rest.auth;


import com.noellimx.main.controllers.rest.auth.bodytype.request.AuthenticateUserPasswordForm;
import com.noellimx.main.controllers.rest.auth.bodytype.request.RegisterForm;
import com.noellimx.main.controllers.rest.auth.bodytype.response.AuthenticatedResponse;
import com.noellimx.main.service.JwtAuthService;
import com.noellimx.main.service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

  final JwtAuthService jwtAuthService;

  final MyUserDetailsService uds;

  final PasswordEncoder pwEncoder;

  @Autowired
  public AuthController(JwtAuthService studentService, MyUserDetailsService uds,
      PasswordEncoder pwEncoder) {
    this.jwtAuthService = studentService;
    this.uds = uds;
    this.pwEncoder = pwEncoder;
  }

  @PostMapping("/register")
  public ResponseEntity register(@RequestBody RegisterForm form) {

    UserDetails userDetails = User.builder().username(form.username).password(form.password)
        .build();
    this.uds.createUser(userDetails);
    return ResponseEntity.status(201).build();
  }

  @PostMapping("/authenticate")
  @ResponseBody
  public ResponseEntity<AuthenticatedResponse> authenticate(
      @RequestBody AuthenticateUserPasswordForm form) {

    UserDetails userDetailsForm = User.builder()
        .username(form.username).password(form.password)
        .build();

    UserDetails userDetails = uds.authenticateUser(userDetailsForm);

    if (userDetails == null) {
      throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
    }
    String token = jwtAuthService.generateToken(userDetails);

    System.out.println("Controller authenticated:  " + token);
    return ResponseEntity.ok(new AuthenticatedResponse(token));
  }
}
