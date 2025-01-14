package ryu.spring.ems.Learn.Spring.Boot.with.PostgreSQL.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ryu.spring.ems.Learn.Spring.Boot.with.PostgreSQL.dto.EmployeeDto;
import ryu.spring.ems.Learn.Spring.Boot.with.PostgreSQL.entity.Employee;
import ryu.spring.ems.Learn.Spring.Boot.with.PostgreSQL.exception.ResourceNotFoundException;
import ryu.spring.ems.Learn.Spring.Boot.with.PostgreSQL.mapper.EmployeeMapper;
import ryu.spring.ems.Learn.Spring.Boot.with.PostgreSQL.repository.EmployeeRepository;
import ryu.spring.ems.Learn.Spring.Boot.with.PostgreSQL.service.EmployeeService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private EmployeeRepository employeeRepository;

    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        Employee employee = EmployeeMapper.mapToEmployee(employeeDto);
        Employee savedEmployee = employeeRepository.save(employee);

        return EmployeeMapper.mapToEmployeeDto(savedEmployee);
    }

    @Override
    public EmployeeDto getEmployeeById(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id " + employeeId));

        return EmployeeMapper.mapToEmployeeDto(employee);
    }

    @Override
    public List<EmployeeDto> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();

        return employees.stream().map(EmployeeMapper::mapToEmployeeDto)
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeDto updateEmployee(Long employeeId, EmployeeDto updatedEmployee) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id " + employeeId));

        employee.setFirstName(updatedEmployee.getFirstName());
        employee.setLastName(updatedEmployee.getLastName());
        employee.setEmail(updatedEmployee.getEmail());

        Employee newUpdatedEmployee = employeeRepository.save(employee);

        return EmployeeMapper.mapToEmployeeDto(newUpdatedEmployee);
    }

    @Override
    public void deleteEmployee(Long employeeId) {
        employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id " + employeeId));

        employeeRepository.deleteById(employeeId);
    }


}
