package com.roopesh.springredisapp.controller;

import com.roopesh.springredisapp.model.DataModel;
import com.roopesh.springredisapp.service.ALangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/aLang")
public class ALangController {

    @Autowired
    ALangService aLangService;

    @PostMapping("/moveValue")
    public ResponseEntity<?> moveValueStatement(@RequestBody DataModel dataModel) {
        try {
            return new ResponseEntity<>("Process Successful\n" + aLangService.mvStatement(dataModel.getStatement()), HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @PostMapping("/addValue")
    public ResponseEntity<?> addValuesStatement(@RequestBody DataModel dataModel) {
        try {
            return new ResponseEntity<>("Process Successful\n" + aLangService.addStatement(dataModel.getStatement()), HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping("/showReg")
    public ResponseEntity<?> showRegStatement(@RequestBody DataModel dataModel) {
        try {
            return new ResponseEntity<>("Process Successful\n" + aLangService.showStatement(dataModel.getStatement()), HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @PostMapping("/executeProgram")
    public ResponseEntity<?> executeProgramStatement(@RequestBody DataModel dataModel) {
        try {
            return new ResponseEntity<>("Process Successful\n" + aLangService.executeProgram(dataModel.getProgram()), HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping("/addValueStatements")
    public ResponseEntity<?> getAllAddValueStatements() {
        try {
            return new ResponseEntity<>("Process Successful\n" + aLangService.getAddRegistries(), HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
