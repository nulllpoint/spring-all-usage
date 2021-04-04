package com.demo.common.util.oauth;

import java.io.Serializable;

import lombok.Data;

@Data
public class MyUser implements Serializable{
	
	private static final long serialVersionUID = 3497935890426858541L;
    private String userName;
    private String password;
    private boolean accountNonExpired = true;
    private boolean accountNonLocked= true;
    private boolean credentialsNonExpired= true;
    private boolean enabled= true;
}
