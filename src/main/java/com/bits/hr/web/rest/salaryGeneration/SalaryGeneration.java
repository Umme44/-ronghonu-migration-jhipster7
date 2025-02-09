package com.bits.hr.web.rest.salaryGeneration;

import com.bits.hr.errors.BadRequestAlertException;
import com.bits.hr.service.approvalProcess.SalaryLockService;
import com.bits.hr.service.salaryGenerationFractional.FractionalSalaryGenerationService;
import com.bits.hr.service.salaryGenerationFractional.SingeEmployeeSalaryGenerationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/payroll-mgt")
public class SalaryGeneration {

    @Autowired
    private FractionalSalaryGenerationService fractionalSalaryGenerationService;

    @Autowired
    private SingeEmployeeSalaryGenerationService singeEmployeeSalaryGenerationService;

    @Autowired
    private SalaryLockService salaryLockService;

    @GetMapping("/salary-generation/{year}/{month}")
    public Boolean salaryGeneration(@PathVariable int year, @PathVariable int month) throws Exception {
        if (salaryLockService.isLocked(String.valueOf(year), String.valueOf(month))) {
            throw new BadRequestAlertException(" Regeneration locked!! ", "Payroll Management", "entryLocked");
        }
        return fractionalSalaryGenerationService.generateAndSave(year, month);
    }

    @GetMapping("/salary-generation/{employeeId}/{year}/{month}")
    public Boolean singleSalaryGeneration(@PathVariable long employeeId, @PathVariable int year, @PathVariable int month) throws Exception {
        if (salaryLockService.isLocked(String.valueOf(year), String.valueOf(month))) {
            throw new BadRequestAlertException(" Regeneration locked!! ", "Payroll Management", "entryLocked");
        }
        return singeEmployeeSalaryGenerationService.generateAndSaveSingleSalary(employeeId, year, month);
    }
}
