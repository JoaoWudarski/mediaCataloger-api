package com.joaowudarski.gateway.repository.data;

import com.br.jvcw.domain.SecureUser;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity implements SecureUser {

    @Id
    private String id;
    private String username;
    private String email;
    private String password;
    private String rolePermission;

    @Override
    public Map<String, Object> getTokenBody() {
        return Map.of();
    }

    @Override
    public List<String> getPermissionList() {
        return List.of(rolePermission);
    }
}
