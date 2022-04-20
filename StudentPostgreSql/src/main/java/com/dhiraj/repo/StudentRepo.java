package com.dhiraj.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dhiraj.model.Student;

@Repository
public interface StudentRepo extends JpaRepository<Student, Integer> {

}
