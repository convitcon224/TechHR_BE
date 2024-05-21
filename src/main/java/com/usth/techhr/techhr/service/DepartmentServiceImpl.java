package com.usth.techhr.techhr.service;

import com.usth.techhr.techhr.dto.DepartmentDTO;
import com.usth.techhr.techhr.exception.ObjectNotFoundException;
import com.usth.techhr.techhr.model.Department;
import com.usth.techhr.techhr.repository.DepartmentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    private DepartmentRepository departmentRepository;
    private ModelMapper modelMapper;

    @Autowired
    public DepartmentServiceImpl(DepartmentRepository departmentRepository, ModelMapper modelMapper) {
        this.departmentRepository = departmentRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Department getDepartmentById(long id) {
        return departmentRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("No department found with id " + id));
    }

    @Override
    public Department getDepartmentByName(String name){
        return Optional.ofNullable(departmentRepository.findDepartmentByName(name)).orElseThrow(() -> new ObjectNotFoundException("No department found with name " + name));
    }

    @Override
    public List<DepartmentDTO> getAllDepartments() {
        return departmentRepository.findAll().stream().map(department -> modelMapper.map(department,DepartmentDTO.class)).toList();
    }
}
