package com.example.Comerstone.dto;


import com.example.Comerstone.entity.UserEntity;
import java.util.ArrayList;
import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class CustomUserDetails implements UserDetails {

  private UserEntity userEntity;

  public CustomUserDetails(UserEntity userEntity) {

    this.userEntity = userEntity;
  }

  // 권한 반환
  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    Collection<GrantedAuthority> collection = new ArrayList<>();

    collection.add(new GrantedAuthority() {

      @Override
      public String getAuthority() {

        return userEntity.getRole();
      }
    });

    return collection;
  }

  @Override
  public String getPassword() {
    return userEntity.getPassword();
  }

  @Override
  public String getUsername() {
    return userEntity.getUsername();
  }

  // 계정 만료 여부 반환
  @Override
  public boolean isAccountNonExpired() {
    // 만료되었는지 확인하는 로직
    return true; // true -> 만료되지 않음
  }

  // 계정 잠금 여부 반환
  @Override
  public boolean isAccountNonLocked() {
    return true; // true -> 잠금되지 않음
  }

  // 패스워드 만료 여부 반환
  @Override
  public boolean isCredentialsNonExpired() {
    return true; // true -> 만료되지 않음
  }

  // 계정 사용 가능 여부 변환
  @Override
  public boolean isEnabled() {
    return true; // true -> 사용 가능
  }
}