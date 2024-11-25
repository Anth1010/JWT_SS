package vn.iotstar.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.UserDetailsService;
import vn.iotstar.entity.UserInfo;
import vn.iotstar.repository.UserInfoRepository;

public class UserInfoService implements UserDetailsService {

    @Autowired
    private UserInfoRepository userInfoRepository;

    public UserInfoService(UserInfoRepository repository) {
		// TODO Auto-generated constructor stub
	}

	@Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Tìm kiếm người dùng trong cơ sở dữ liệu
        UserInfo userInfo = userInfoRepository.findByName(username)
                                              .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));

        // Chuyển đối tượng UserInfo thành UserInfoUserDetails
        return new UserInfoUserDetails(userInfo);
    }
}
