package com.pppfreak.Hired.Entity;

public interface Subscribe {
    void registerObserver(Employee employee);
    void removedObserver(Employee employee);
    void notifyObserver(CompanyProfile companyProfile);
}
