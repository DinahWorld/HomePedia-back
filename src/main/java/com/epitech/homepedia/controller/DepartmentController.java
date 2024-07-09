package com.epitech.homepedia.controller;

import com.epitech.homepedia.dto.DepartmentDTO;
import com.epitech.homepedia.services.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/api/v1/department")
@RequiredArgsConstructor
public class DepartmentController {
    private final DepartmentService departmentService;

    @PostMapping
    public ResponseEntity<String> createDepartment(@RequestBody DepartmentDTO name) {
        departmentService.addDepartement(name);
        return ResponseEntity.ok("Department created");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateDepartment(@PathVariable Long id, @RequestBody DepartmentDTO name) {
        departmentService.updateDepartementName(id, name);
        return ResponseEntity.ok("Department updated");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDepartment(@PathVariable Long id) {
        departmentService.deleteDepartement(id);
        return ResponseEntity.ok("Department deleted");
    }

    @GetMapping("/all")
    public ResponseEntity<List<DepartmentDTO>> getAllDepartments() {
        return ResponseEntity.ok(departmentService.getAllDepartement());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DepartmentDTO> getDepartment(@PathVariable Long id) {
        return ResponseEntity.ok(departmentService.getDepartementDTO(id));
    }

}