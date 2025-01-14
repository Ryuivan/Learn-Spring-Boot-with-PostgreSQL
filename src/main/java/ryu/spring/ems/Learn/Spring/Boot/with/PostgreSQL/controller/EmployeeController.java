package ryu.spring.ems.Learn.Spring.Boot.with.PostgreSQL.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ryu.spring.ems.Learn.Spring.Boot.with.PostgreSQL.dto.EmployeeDto;
import ryu.spring.ems.Learn.Spring.Boot.with.PostgreSQL.service.EmployeeService;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
@AllArgsConstructor
public class EmployeeController {
    private EmployeeService employeeService;

    //    Build add Employee REST API
    @PostMapping
    public ResponseEntity<EmployeeDto> createEmployee(@RequestBody EmployeeDto employeeDto) {
        EmployeeDto savedEmployee = employeeService.createEmployee(employeeDto);

        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }

    //    Build get Employee REST API
    @GetMapping("/{employeeId}")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable("employeeId") Long employeeId) {
        EmployeeDto employeeDto = employeeService.getEmployeeById(employeeId);

        return ResponseEntity.ok(employeeDto);
    }

    //    Build get All Employees REST API
    @GetMapping
    public ResponseEntity<List<EmployeeDto>> getAllEmployees() {
        List<EmployeeDto> employees = employeeService.getAllEmployees();

        return ResponseEntity.ok(employees);
    }

    //    Build update Employee by ID REST API
    @PutMapping("/{employeeId}")
    public ResponseEntity<EmployeeDto> updateEmployee(
            @PathVariable("employeeId") Long employeeId,
            @RequestBody EmployeeDto updatedEmployee
    ) {
        EmployeeDto employeeDto = employeeService.updateEmployee(employeeId, updatedEmployee);

        return ResponseEntity.ok(employeeDto);
    }

    //    Build delete Employee by ID REST API
    @DeleteMapping("/{employeeId}")
    public ResponseEntity<String> deleteEmployee(@PathVariable("employeeId") Long employeeId) {
        employeeService.deleteEmployee(employeeId);

        return ResponseEntity.ok("Employee deleted successfully");
    }
}
