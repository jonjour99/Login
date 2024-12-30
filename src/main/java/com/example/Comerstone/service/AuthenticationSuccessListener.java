package com.example.Comerstone.service;

import com.example.Comerstone.entity.UserEntity;
import com.example.Comerstone.repository.UserRepository;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationSuccessListener implements
    ApplicationListener<AuthenticationSuccessEvent> {

  @Autowired
  private UserRepository userRepository;

  @Override
  public void onApplicationEvent(AuthenticationSuccessEvent event) {
    // 로그인 성공 시 처리할 내용
    String username = event.getAuthentication().getName();

    UserEntity user = userRepository.findByUsername(username);
    if (user != null) {
      // 마지막 로그인 시간 업데이트
      user.setLastLoginTime(LocalDateTime.now());
      userRepository.save(user);
    }
  }
}
