package com.m2r.mdsl.utils;

import java.util.Objects;

public class StringWrapper {

    private String value;

    public static StringWrapper of(String value) {
        return new StringWrapper(value);
    }

    public StringWrapper() {
    }

    public StringWrapper(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String toCamelCase() {
        if (value == null) return null;

        if (value.indexOf(' ') == -1) {
            return value.substring(0, 1).toLowerCase() + value.substring(1);
        }

        StringBuilder camelCase = new StringBuilder();
        boolean capitalizeNext = false;
        for (char c : value.toCharArray()) {
            if (Character.isWhitespace(c)) {
                capitalizeNext = true;
            } else {
                if (capitalizeNext) {
                    camelCase.append(Character.toUpperCase(c));
                    capitalizeNext = false;
                } else {
                    camelCase.append(Character.toLowerCase(c));
                }
            }
        }
        return camelCase.toString();
    }

    public String toPascalCase() {
        if (value == null) return null;

        if (value.indexOf(' ') == -1) {
            return value.substring(0, 1).toUpperCase() + value.substring(1);
        }

        StringBuilder pascalCase = new StringBuilder();
        boolean capitalizeNext = true;
        for (char c : value.toCharArray()) {
            if (Character.isWhitespace(c)) {
                capitalizeNext = true;
            } else {
                if (capitalizeNext) {
                    pascalCase.append(Character.toUpperCase(c));
                    capitalizeNext = false;
                } else {
                    pascalCase.append(Character.toLowerCase(c));
                }
            }
        }
        return pascalCase.toString();
    }

    public String toSnakeLowerCase() {
        if (value == null) return null;
        return toSnakeUpperCase().toLowerCase();
    }

    public String toSnakeUpperCase() {
        if (value == null) return null;
        StringBuilder str = new StringBuilder();
        char l = (char) -1;
        for (char c : value.toCharArray()) {
            if (l > -1 && Character.isLowerCase(l) && Character.isUpperCase(c)) {
                str.append("_");
            }
            str.append((c+"").toUpperCase());
            l = c;
        }
        return str.toString();
    }

    public String toKebabLowerCase() {
        if (value == null) return null;
        return toKebabUpperCase().toLowerCase();
    }

    public String toKebabUpperCase() {
        if (value == null) return null;
        StringBuilder str = new StringBuilder();
        char l = (char) -1;
        for (char c : value.toCharArray()) {
            if (l > -1 && Character.isLowerCase(l) && Character.isUpperCase(c)) {
                str.append("-");
            }
            str.append((c+"").toUpperCase());
            l = c;
        }
        return str.toString();
    }

    public String toLowerCase() {
        if (value == null) return null;
        return value.toLowerCase();
    }

    public String toUpperCase() {
        if (value == null) return null;
        return value.toUpperCase();
    }

    public String camelCase() {
        if (value == null) return null;
        return value.substring(0, 1).toLowerCase() + value.substring(1);
    }

    public String toPath() {
        if (value == null) return null;
        return getValue().replaceAll("\\.", "/");
    }

    public String toPackage() {
        if (value == null) return null;
        return getValue().replaceAll("/", ".");
    }

    public String replace(String regex, String replaciment) {
        if (value == null) return null;
        return getValue().replaceAll(regex, replaciment);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        return Objects.equals(value, o.toString());
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
