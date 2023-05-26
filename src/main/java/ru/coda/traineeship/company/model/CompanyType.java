package ru.coda.traineeship.company.model;

public enum CompanyType {
    GOVERNMENT_MOSCOW, OTHER;

    public String value(CompanyType type) {
        return type.name();
    }
}
