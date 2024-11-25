package vn.iotstar.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Entity // Đánh dấu đây là một entity JPA
@Data // Tự động tạo getter và setter
@AllArgsConstructor // Tạo constructor có tất cả các tham số
@NoArgsConstructor // Tạo constructor không tham số
public class UserInfo {

    @Id // Đánh dấu id là khóa chính của bảng
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Sử dụng auto-increment cho id
    private int id;
    
    private String name;
    private String email;
    private String password;
    private String roles;
}
