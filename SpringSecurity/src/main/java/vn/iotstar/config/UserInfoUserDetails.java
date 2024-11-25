package vn.iotstar.config;



import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.iotstar.entity.UserInfo;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoUserDetails implements UserDetails {

    private static final long serialVersionUID = 1L;
    
    private String name;
    private String password;
    private List<GrantedAuthority> authorities;

    // Constructor từ đối tượng UserInfo
    public UserInfoUserDetails(UserInfo userInfo) {
        this.name = userInfo.getName();  // Lấy tên người dùng từ UserInfo
        this.password = userInfo.getPassword();  // Lấy mật khẩu từ UserInfo

        // Chuyển đổi roles từ chuỗi sang List<GrantedAuthority>
        this.authorities = Arrays.stream(userInfo.getRoles().split(","))
                                 .map(SimpleGrantedAuthority::new)
                                 .collect(Collectors.toList());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;  // Trả về quyền hạn người dùng
    }

    @Override
    public String getPassword() {
        return password;  // Trả về mật khẩu
    }

    @Override
    public String getUsername() {
        return name;  // Trả về tên người dùng
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;  // Tài khoản không hết hạn
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;  // Tài khoản không bị khóa
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;  // Mật khẩu không hết hạn
    }

    @Override
    public boolean isEnabled() {
        return true;  // Tài khoản đang kích hoạt
    }
}
