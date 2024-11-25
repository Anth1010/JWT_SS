package vn.iotstart.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "roles") // Chỉ định tên bảng là "roles"
public class Role implements Serializable {

    @Id
    @Column(name = "role_id") // Chỉ định cột role_id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto increment trên SQL Server
    private Long id;

    @Column(name = "role_name", length = 50, columnDefinition = "nvarchar(50) not null") // Định nghĩa cột role_name
    private String name;
}
