package org.exercises;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import static org.assertj.core.api.Assertions.*;

public class ReinforcementTests {
    ByteArrayOutputStream output = new ByteArrayOutputStream();

    @BeforeEach
    void setup() {
        System.setOut(new PrintStream(output));
    }

    @AfterEach
    void cleanup() {
        System.setOut(System.out);
        System.setIn(System.in);
    }

    @Test
    void r1_1() {
        inputAllBaseTypes("2.10\nfalse\ntrue\nhello\nfalse\n");
        assertThat(output.toString()).isEqualTo("double\nboolean\nboolean\nstring\nboolean\n");
    }

    @Test
    void r1_2() {
        GameEntry[] A = new GameEntry[5];
        GameEntry[] B;

        for (int i = 0; i < A.length; i++)
            A[i] = new GameEntry();

        B = A.clone();
        A[4].setScore(550);

        assertThat(B[4].getScore()).isEqualTo(550);
    }

    @Test
    void r1_3() {
        long n = 8, m = 4;

        assertThat(isMultiple(n, m)).isTrue();
        assertThat(isMultiple(12, 7)).isFalse();
    }

    @Test
    void r1_4() {
        assertThat(isEven(4)).isTrue();
        assertThat(isEven(5)).isFalse();
    }

    @Test
    void r1_5() {

    }

    void inputAllBaseTypes(String input) {
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        Scanner scanner = new Scanner(inputStream, StandardCharsets.UTF_8.name());

        do {
            String value = scanner.nextLine();
            String baseType = "";

            try {
                Double.parseDouble(value);
                System.out.print("double\n");
            } catch (Exception ignored) {
                baseType = "!double";
            }

            if (baseType.equals("!double")) {
                if (value.equals("true") || value.equals("false"))
                    System.out.print("boolean\n");
                else
                    baseType = "!boolean";
            }

            if (baseType.equals("!boolean")) {
                System.out.print("string\n");
            }

        } while (scanner.hasNextLine());
    }

    boolean isMultiple(long n, long m) {
        // n = m * i
        // i = n / m
        // i : any integer
        long i = n / m;

        return n == m * i;
    }

    boolean isEven(long n) {
        // 0 is Even; 1 is Odd
        // 0,1,...n
        boolean isEven = true;

        for (int i = 0; i < n; i++) {
            isEven = !isEven;
        }

        return isEven;
    }
}
