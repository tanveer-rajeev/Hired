package com.pppfreak.Hired.repository;

import com.pppfreak.Hired.Entity.CompanyProfile;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyProfileRepository extends CrudRepository<CompanyProfile,Integer> {
    CompanyProfile findByEmail(String email);
    CompanyProfile findByCompanyName(String name);
}
