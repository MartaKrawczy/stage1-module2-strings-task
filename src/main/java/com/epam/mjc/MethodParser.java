package com.epam.mjc;

import java.util.ArrayList;
import java.util.List;

public class MethodParser {

    /**
     * Parses string that represents a method signature and stores all it's members into a {@link MethodSignature} object.
     * signatureString is a java-like method signature with following parts:
     *      1. access modifier - optional, followed by space: ' '
     *      2. return type - followed by space: ' '
     *      3. method name
     *      4. arguments - surrounded with braces: '()' and separated by commas: ','
     * Each argument consists of argument type and argument name, separated by space: ' '.
     * Examples:
     *      accessModifier returnType methodName(argumentType1 argumentName1, argumentType2 argumentName2)
     *      private void log(String value)
     *      Vector3 distort(int x, int y, int z, float magnitude)
     *      public DateTime getCurrentDateTime()
     *
     * @param signatureString source string to parse
     * @return {@link MethodSignature} object filled with parsed values from source string
     */
    public MethodSignature parseFunction(String signatureString) {
        List<MethodSignature.Argument> arguments = new ArrayList<>();
        MethodSignature methodSignature;

        String[] temp = signatureString.split("\\(");
        String[] splittedMethodsData = temp[0].split(" ");
        String parametersData = temp[1].replace(")", "");

        if (parametersData.length() != 0) {
            String[] pairParameters = parametersData.split(",");
            for (int x = 0; x < pairParameters.length; x++) {
                String pair = pairParameters[x].trim();
                String[] pair2 = pair.split(" ");
                MethodSignature.Argument argument = new MethodSignature.Argument(pair2[0], pair2[1]);
                arguments.add(argument);
            }

            if (splittedMethodsData.length == 3) {
                methodSignature = new MethodSignature(splittedMethodsData[2], arguments);
                methodSignature.setAccessModifier(splittedMethodsData[0]);
                methodSignature.setReturnType(splittedMethodsData[1]);
            } else {
                methodSignature = new MethodSignature(splittedMethodsData[1], arguments);
                methodSignature.setReturnType(splittedMethodsData[0]);
            }
        } else {
            if (splittedMethodsData.length == 3) {
                methodSignature = new MethodSignature(splittedMethodsData[2]);
                methodSignature.setAccessModifier(splittedMethodsData[0]);
                methodSignature.setReturnType(splittedMethodsData[1]);
            } else {
                methodSignature = new MethodSignature(splittedMethodsData[1]);
                methodSignature.setReturnType(splittedMethodsData[0]);
            }
        }
        return methodSignature;
    }
}
