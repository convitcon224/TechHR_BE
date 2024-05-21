package com.usth.techhr.techhr.service;

import com.usth.techhr.techhr.common.DataUtil;
import com.usth.techhr.techhr.dto.MajorDTO;
import com.usth.techhr.techhr.exception.ObjectNotFoundException;
import com.usth.techhr.techhr.model.Employee;
import com.usth.techhr.techhr.model.Major;
import com.usth.techhr.techhr.repository.MajorRepository;
import jakarta.persistence.Query;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class MajorServiceImpl implements MajorService {
    private MajorRepository majorRepository;
    private ModelMapper modelMapper;

    @Autowired
    public MajorServiceImpl(MajorRepository majorRepository, ModelMapper modelMapper) {
        this.majorRepository = majorRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Major getMajorById(long id) {
        return majorRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("No major with id " + id + " found"));
    }

    @Override
    public Major getMajorByValue(String value) {
        return Optional.ofNullable(majorRepository.findMajorByValue(value)).orElseThrow(() -> new ObjectNotFoundException("No major found with value " + value));
    }

    @Override
    public List<MajorDTO> getAllMajors() {
        return majorRepository.findAll().stream().map(major -> modelMapper.map(major, MajorDTO.class)).toList();
    }
}
