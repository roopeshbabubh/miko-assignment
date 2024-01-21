package com.roopesh.springredisapp.service;

import com.roopesh.springredisapp.entity.ALangAddRegistry;
import com.roopesh.springredisapp.entity.ALangMVData;
import com.roopesh.springredisapp.mapper.ALangMapper;
import com.roopesh.springredisapp.repository.ALangAddRegistryRepository;
import com.roopesh.springredisapp.repository.ALangMVDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Service
public class ALangService {

    @Autowired
    ALangMVDataRepository mVDataRepo;

    @Autowired
    ALangAddRegistryRepository addRegistryRepo;

    @Autowired
    ALangMapper aLMapper;

    public ALangMVData mvStatement(String statement) {
        try {

            String[] instructionParts = statement.replaceAll(",#", " ").split(" ");
            String register = instructionParts[1].trim();
            int value = Integer.parseInt(instructionParts[2].trim());
            return moveValueToDB(register, value);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public ALangMVData addStatement(String statement) {
        try {

            String[] instructionParts = statement.split(" ");
            String[] targetRegisters = instructionParts[1].split(",");
            String register = targetRegisters[0].trim();
            int result = 0;
            for (String targetRegister : targetRegisters) {
                if (targetRegister.startsWith("REG")) {
                    ALangMVData regMVData = findByRegister(targetRegister.trim());
                    if (regMVData != null) {
                        result += regMVData.getCurrentValue();
                    }
                } else {
                    result += Integer.parseInt(targetRegister.trim());
                }
            }

            addRegistryRepo.save(aLMapper.mapToALangAddRegistry(instructionParts[1].trim()));

            return moveValueToDB(register, result);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public ALangMVData moveValueToDB(String register, int currentValue) {

        ALangMVData prevMVData = findByRegister(register);
        if (prevMVData != null) {
            prevMVData.setPreviousValue(prevMVData.getCurrentValue());
            prevMVData.setCurrentValue(currentValue);
            prevMVData = mVDataRepo.save(prevMVData);
            return prevMVData;
        }
        prevMVData = mVDataRepo.save(aLMapper.mapToALangMVData(register, currentValue));

        return prevMVData;
    }

    public List<ALangMVData> showStatement(String statement) {
        try {

            String[] instructionParts = statement.split(" ");
            String register = instructionParts[1].trim();
            if (Objects.equals(register, "REG")) {
                return (List<ALangMVData>) mVDataRepo.findAll();
            } else {
                ALangMVData mVData = findByRegister(register);
                if (mVData != null) {
                    return Collections.singletonList(mVData);
                } else {
                    return Collections.emptyList();
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private ALangMVData findByRegister(String register) {
        ArrayList<ALangMVData> mVDataList = (ArrayList<ALangMVData>) mVDataRepo.findAll();
        return mVDataList.stream()
                .filter(mvData -> Objects.equals(mvData.getRegister(), register))
                .findFirst()
                .orElse(null);
    }

    public List<ALangMVData> executeProgram(String program) {
        try {

            List<ALangMVData> mVDataList = new ArrayList<>();
            String[] instructions = program.split("\n");
            for (String instruction : instructions) {

                instruction = instruction.trim();
                String[] instructionParts = instruction.split(" ");
                String operation = instructionParts[0].trim();

                switch (operation) {
                    case "MV":
                        mvStatement(instruction);
                        break;

                    case "ADD":
                        addStatement(instruction);
                        break;

                    case "SHOW":
                        mVDataList = showStatement(instruction);
                        break;

                    default:
                        throw new RuntimeException("'" + operation + "' is not a valid operation");
                }
            }
            return mVDataList;
        } catch (Exception e) {
            throw new RuntimeException("Error executing program: " + e.getMessage(), e);
        }
    }

    public List<ALangAddRegistry> getAddRegistries() {
        return (List<ALangAddRegistry>) addRegistryRepo.findAll();
    }
}
