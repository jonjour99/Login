package com.example.Comerstone.service;

import com.example.Comerstone.dto.JoinDTO;
import com.example.Comerstone.entity.UserEntity;
import com.example.Comerstone.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class JoinService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private BCryptPasswordEncoder BCryptPasswordEncoder;

  public void joinProcess(JoinDTO joinDTO) {

    UserEntity data = new UserEntity();

    data.setUsername(joinDTO.getUsername());
    data.setPassword(BCryptPasswordEncoder.encode(joinDTO.getPassword()));
    //서비스 권한 설정
    data.setRole("ROLE_USER");

    userRepository.save(data);
  }
}
