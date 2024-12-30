package com.example.Comerstone.controller;

import com.example.Comerstone.entity.UserEntity;
import com.example.Comerstone.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

  @Autowired
  private UserRepository userRepository;

  @GetMapping("/admin")
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  public String adminP() {
    return "user/admin";
  }

  @GetMapping("/user")
  @PreAuthorize("hasRole('ROLE_USER')")
  public String userEndpoint() {
    return "user/user";
  }

  @GetMapping("/deleteUser")
  public String deleteUserPage() {
    return "user/deleteUser";
  }

  @PostMapping("/withdraw")
  public String withdrawUserAccount(HttpServletRequest request, HttpServletResponse response) {
    // 회원탈퇴
    String username = SecurityContextHolder.getContext().getAuthentication().getName();

    UserEntity user = userRepository.findByUsername(username);

    if (user != null) {

      userRepository.delete(user);
      return "redirect:/deleteUser";
    }

    // 세션 무효화 및 로그아웃 처리
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    if (auth != null) {
      new SecurityContextLogoutHandler().logout(request, response, auth);
    }

    return "redirect:/deleteUser";
  }
}
