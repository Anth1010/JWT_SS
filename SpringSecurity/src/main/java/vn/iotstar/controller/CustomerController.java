package vn.iotstar.controller;



import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;
import vn.iotstar.entity.Customer;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@EnableMethodSecurity // Cho phép sử dụng @PreAuthorize để phân quyền
public class CustomerController {

    // Danh sách khách hàng giả lập
    private final List<Customer> customers = List.of(
            Customer.builder()
                    .id("001")
                    .name("Nguyễn Hữu Trung")
                    .email("trungnhspkt@gmail.com")
                    .build(),
            Customer.builder()
                    .id("002")
                    .name("Hữu Trung")
                    .email("trunghuu@gmail.com")
                    .build()
    );

    // Phương thức không yêu cầu phân quyền
    @GetMapping("/hello")
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok("hello is Guest");
    }

    // Phương thức yêu cầu quyền ADMIN để truy cập
    @GetMapping("/customer/all")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<Customer>> getCustomerList() {
        return ResponseEntity.ok(customers);
    }
    
    // Phương thức yêu cầu quyền USER để truy cập
    @GetMapping("/customer/{id}")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<Customer> getCustomerById(@PathVariable("id") String id) {
        List<Customer> filteredCustomers = customers.stream()
                .filter(customer -> customer.getId().equals(id))
                .collect(Collectors.toList());

        if (filteredCustomers.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(filteredCustomers.get(0));
    }
}
