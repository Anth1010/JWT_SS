package vn.iotstart.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users") // Chỉ định tên bảng là "users"
public class Users {

    @Id
    @Column(name = "user_id") // Chỉ định cột user_id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto increment trên SQL Server
    private Long id;

    private String username;

    @Email // Kiểm tra định dạng email hợp lệ
    private String email;

    @Column(length = 60, columnDefinition = "nvarchar(50) not null") // Định nghĩa độ dài và kiểu cột
    private String name;

    private String password;

    private boolean enabled;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER) // Quan hệ nhiều-nhiều với bảng Role
    @JoinTable(
        name = "users_roles", // Bảng trung gian giữa Users và Roles
        joinColumns = @JoinColumn(name = "user_id"), // Cột khóa ngoại cho bảng Users
        inverseJoinColumns = @JoinColumn(name = "role_id") // Cột khóa ngoại cho bảng Roles
    )
    private Set<Role> roles = new HashSet<>(); // Quan hệ nhiều-nhiều giữa Users và Role
}
