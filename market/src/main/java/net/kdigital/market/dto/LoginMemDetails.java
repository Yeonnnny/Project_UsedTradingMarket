package net.kdigital.market.dto;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.ToString;

@ToString
public class LoginMemDetails implements UserDetails {
    // 외부의 역직렬화한 데이터와 일치하는지 비교해야할 때 시리얼 번호가 필요함
    private static final long serialVersionID = 1L;
    private String memId;
    private String memPw;
    private String memName;
    private String phone;
    private boolean enabled;
    private String rolename;

    // 생성자
    public LoginMemDetails(MemDTO memDTO) {
        super();
        this.memId = memDTO.getMemId();
        this.memPw = memDTO.getMemPw();
        this.memName = memDTO.getMemName();
        this.phone = memDTO.getPhone();
        this.enabled = memDTO.isEnabled();
        this.rolename = memDTO.getRolename();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collection = new ArrayList<>();
        collection.add(new GrantedAuthority() {
            private static final long serialVersionID = 1L;

            @Override
            public String getAuthority() {
                return rolename;
            }
        });
        return collection;
    }

    @Override
    public String getPassword() {
        return this.memPw;
    }

    @Override
    public String getUsername() {
        return this.memId;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
