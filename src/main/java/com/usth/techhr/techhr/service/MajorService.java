package com.usth.techhr.techhr.service;

import com.usth.techhr.techhr.dto.MajorDTO;
import com.usth.techhr.techhr.model.Major;

import java.util.List;

public interface MajorService {
    Major getMajorById(long id);

    Major getMajorByValue(String value);

    List<MajorDTO> getAllMajors();
}
