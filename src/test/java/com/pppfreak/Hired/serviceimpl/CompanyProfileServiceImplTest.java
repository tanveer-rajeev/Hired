package com.pppfreak.Hired.serviceimpl;

import com.pppfreak.Hired.Entity.CompanyProfile;
import com.pppfreak.Hired.form.request.CompanyProfileModel;
import com.pppfreak.Hired.repository.CompanyJobTitleRepository;
import com.pppfreak.Hired.repository.CompanyProfileRepository;
import com.pppfreak.Hired.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class CompanyProfileServiceImplTest {

    @InjectMocks
    CompanyProfileServiceImpl companyProfileService;

    @Mock
    CompanyProfileRepository companyProfileRepository;
    @Mock
    ModelMapper modelMapper;
    EmployeeRepository employeeRepository;
    @Mock
    BCryptPasswordEncoder bCryptPasswordEncoder;
    @Mock
    CompanyJobTitleRepository companyJobTitleRepository;

    CompanyProfile companyProfile;
    CompanyProfileModel companyProfileModel;

    @BeforeEach
    final void setUp() {
        MockitoAnnotations.initMocks(this);
        companyProfile = new CompanyProfile();

        companyProfile.setEmail("company@gmail.com");
        companyProfile.setEncryptedPassword("encryptedPassword");
        companyProfile.setCompanyName("google");
        companyProfile.setAddress("usa");
        companyProfile.setCompanyActivities("create a search engine ");
        companyProfile.setCompanyWebsiteLink("google.com");

        companyProfileModel = new CompanyProfileModel();
        companyProfileModel.setEmail("company@gmail.com");
        companyProfileModel.setPassword("encryptedPassword");
        companyProfileModel.setCompanyName("google");
        companyProfileModel.setAddress("usa");
        companyProfileModel.setCompanyActivities("create a search engine ");
        companyProfileModel.setCompanyWebsiteLink("google.com");
    }

    @Test
    final void addCompanyProfile() {

        when(companyProfileRepository.findByEmail(anyString())).thenReturn(null);
        when(bCryptPasswordEncoder.encode(anyString())).thenReturn("encryptedPassword");
        when(companyProfileRepository.save(any(CompanyProfile.class))).thenReturn(companyProfile);


        CompanyProfile returnValue = companyProfileService.addCompanyProfile(companyProfileModel);
        assertNotNull(returnValue);
        assertEquals(this.companyProfile.getCompanyName(), returnValue.getCompanyName());


    }
}