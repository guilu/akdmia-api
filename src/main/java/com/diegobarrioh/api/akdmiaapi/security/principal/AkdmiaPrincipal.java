package com.diegobarrioh.api.akdmiaapi.security.principal;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@Setter
@Getter
public class AkdmiaPrincipal extends User {

    private String email;
    public AkdmiaPrincipal(String username,
                           Collection<? extends GrantedAuthority> authorities) {
        super(username,"" , authorities);
    }

}
