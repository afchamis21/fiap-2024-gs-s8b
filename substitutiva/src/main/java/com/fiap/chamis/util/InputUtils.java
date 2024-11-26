package com.fiap.chamis.util;

import java.math.BigDecimal;
import java.util.Scanner;

public class InputUtils {
    private static final Scanner scanner = new Scanner(System.in);

    private static String formatMessage(String message) {
        message = message.trim();
        if (!message.endsWith(":")) {
            message += ":";
        }
        return message;
    }

    public static String getStringInput(String message) {
        String input;
        message = formatMessage(message);
        while (true) {
            System.out.print(message + " ");
            input = scanner.nextLine().trim();
            if (!input.isEmpty()) {
                return input;
            }
            System.out.println("Erro: o input não pode estar vazio. Por favor, tente novamente.");
        }
    }

    public static Long getLongInput(String message) {
        message = formatMessage(message);
        while (true) {
            System.out.print(message + " ");
            String input = scanner.nextLine().trim();
            try {
                return Long.parseLong(input);
            } catch (NumberFormatException e) {
                System.out.println("Erro: número inteiro inválido. Por favor, insira um número inteiro válido.");
            }
        }
    }

    public static BigDecimal getBigDecimalInput(String message) {
        message = formatMessage(message);
        while (true) {
            System.out.print(message + " ");
            String input = scanner.nextLine().trim();
            try {
                return new BigDecimal(input);
            } catch (NumberFormatException e) {
                System.out.println("Erro: número decimal inválido. Por favor, insira um valor numérico válido.");
            }
        }
    }

    public static Double getDoubleInput(String message) {
        message = formatMessage(message);
        while (true) {
            System.out.print(message + " ");
            String input = scanner.nextLine().trim();
            try {
                return Double.parseDouble(input);
            } catch (NumberFormatException e) {
                System.out.println("Erro: número decimal inválido. Por favor, insira um número no formato correto (ex.: 123.45).");
            }
        }
    }

    public static Integer getIntegerInput(String message) {
        message = formatMessage(message);
        while (true) {
            System.out.print(message + " ");
            String input = scanner.nextLine().trim();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Erro: número inteiro inválido. Por favor, insira um número inteiro válido.");
            }
        }
    }
}
