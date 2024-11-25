package vn.iotstar.service;



import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import vn.iotstar.entity.UserInfo;
import vn.iotstar.repository.UserInfoRepository;

@Service
public class UserService {

    private final UserInfoRepository repository;
    private final PasswordEncoder passwordEncoder;

    // Constructor injection để Spring tự động inject repository và passwordEncoder
    public UserService(UserInfoRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    // Phương thức để thêm user vào database
    public String addUser(UserInfo userInfo) {
        // Mã hóa mật khẩu trước khi lưu vào database
        userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
        
        // Lưu user vào repository (database)
        repository.save(userInfo);
        
        return "Thêm user thành công!";
    }
}
