import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class SimpleAssemblyLanguage {

    private static Map<String, Integer> registers = new HashMap<>();

    private static void executeProgram(String program) {
        String[] instructions = program.split("\n");

        for (String instruction : instructions) {
            executeInstruction(instruction);
        }
    }

    private static void executeInstruction(String instruction) {

        String[] instructionParts = instruction.split(" ");
        String operation = instructionParts[0].trim();

        switch (operation) {
            case "MV":
                moveValue(instructionParts[1]);
                break;

            case "ADD":
                addRegisters(instructionParts[1]);
                break;

            case "SHOW":
                showRegisterValue(instructionParts[1]);
                break;

            default:
                System.out.println("Invalid operation: " + operation);
        }
    }

    private static void moveValue(String instructionPart) {
        String[] targetRegisters = instructionPart.split(",#");
        String register = targetRegisters[0].trim();
        int value = Integer.parseInt(targetRegisters[1].trim());
        registers.put(register, value);
    }

    private static void addRegisters(String instructionPart) {
        String[] targetRegisters = instructionPart.split(",");
        String register = targetRegisters[0].trim();
        int result = 0;
        for (String targetRegister : targetRegisters) {
            if (targetRegister.startsWith("REG")) {
                if (registers.get(targetRegister.trim()) != null) {
                    result += registers.get(targetRegister.trim());
                }
            } else {
                result += Integer.parseInt(targetRegister.trim());
            }
        }
        registers.put(register, result);
    }

    private static void showRegisterValue(String register) {
        if (Objects.equals(register, "REG")) {
            System.out.println("SHOW " + register + " :-");
            for (Map.Entry<String, Integer> entry : registers.entrySet()) {
                System.out.println(entry.getKey() + ": " + entry.getValue());
            }
        } else {
            System.out.println("SHOW " + register + ": " + registers.get(register.trim()));
        }
    }

    public static void main(String[] args) {

        String program = "MV REG1,#2000\n" +
                "MV REG2,#4000\n" +
                "ADD REG1,REG2\n" +
                "ADD REG1,600\n" +
                "SHOW REG";

        System.out.println("program :-\n" + program + "\n");
        executeProgram(program);
    }
}