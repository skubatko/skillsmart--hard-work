package ru.skubatko.dev.skillsmart.hard.work.task12.case1;

import ru.skubatko.dev.skillsmart.hard.work.task12.case1.common.Employee;
import ru.skubatko.dev.skillsmart.hard.work.task12.case1.common.EmployeeDto;
import ru.skubatko.dev.skillsmart.hard.work.task12.case1.common.EmployeeRepository;
import ru.skubatko.dev.skillsmart.hard.work.task12.case1.common.Specification;

import java.util.List;

public class EmployeeService {

    private EmployeeRepository repository;

    public void setRepository(EmployeeRepository repository) {
        this.repository = repository;
    }

    public List<EmployeeDto> getEmployeesByParameters(String fullName) {
        Specification<Employee> specification = fullNameSpec(fullName);
        specification = specification.and(jobNameSpec());
        return repository.findAll(specification);
    }

    private Specification<Employee> fullNameSpec(String fullName) {
        return null;
    }

    private Specification<Employee> jobNameSpec() {
        return null;
    }
}
