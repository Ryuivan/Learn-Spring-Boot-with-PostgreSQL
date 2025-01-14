package ryu.spring.ems.Learn.Spring.Boot.with.PostgreSQL.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ryu.spring.ems.Learn.Spring.Boot.with.PostgreSQL.entity.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
