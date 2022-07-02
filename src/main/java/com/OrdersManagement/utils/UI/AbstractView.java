package com.OrdersManagement.utils.UI;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Locale;
import java.util.Scanner;

public abstract class AbstractView {
    protected AbstractView parentView;
    protected final String displayTitle;
    protected final String displayName;

    protected final Scanner scanner = new Scanner(System.in).useLocale(Locale.ROOT);
//  https://stackoverflow.com/questions/16040601/why-is-nextline-returning-an-empty-string
    protected final Scanner stringScanner = new Scanner(System.in).useLocale(Locale.ROOT);

    public AbstractView(String displayTitle, String displayName) {
        this.displayTitle = displayTitle;
        this.displayName = displayName;
    }

    public abstract void show();

    protected void back(){
        if (parentView != null) {
            parentView.show();
        } else {
            throw new IllegalStateException("No parent view");
        }
    }

    public AbstractView getParentView() {
        return parentView;
    }

    public void setParentView(AbstractView parentView) {
        this.parentView = parentView;
    }

    public String getDisplayTitle() {
        return displayTitle;
    }

    public String getDisplayName() {
        return displayName;
    }
    public void print(String message) {
        System.out.print(message);
    }
    public void println(String message) {
        System.out.println(message);
    }
    protected <T> T prompt(String prompt, Class<T> type) {
        return prompt(prompt, type, null);
    }
    protected <T> T prompt(String prompt, Class<T> type, T defaultValue) {
        T result = null;
        boolean isValid = false;
        this.println(prompt);
        while (!isValid) {
            Object input;
            try {
                if (type == Integer.class) {
                    input = scanner.nextInt();
                } else if (type == String.class) {
                    input = stringScanner.nextLine();
                } else if (type == Boolean.class) {
                    input = scanner.nextBoolean();
                } else if (type == Double.class) {
                    input = scanner.nextDouble();
                } else if (type == Float.class) {
                    input = scanner.nextFloat();
                } else if (type == Long.class) {
                    input = scanner.nextLong();
                } else if (type == Short.class) {
                    input = scanner.nextShort();
                } else if (type == Byte.class) {
                    input = scanner.nextByte();
                } else if (type == Character.class) {
                    input = scanner.next().charAt(0);
                } else if (type == BigDecimal.class) {
                    input = scanner.nextBigDecimal();
                } else if (type == BigInteger.class) {
                    input = scanner.nextBigInteger();
                } else {
                    throw new IllegalArgumentException("Invalid type");
                }
            } catch (Exception e) {
                input = defaultValue;
            }
            try {
                result = type.cast(input);
                isValid = true;
            } catch (ClassCastException e) {
                this.println(e.toString());
                this.println("Invalid input. Try again:");
            }
        }
        return result;
    }

}
