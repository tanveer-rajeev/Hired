package com.pppfreak.Hired.upload;

import com.pppfreak.Hired.customise.MassageConstant;
import com.pppfreak.Hired.helper.CseEmployeeServiceImpl;
import com.pppfreak.Hired.helper.EmployeeType;
import com.pppfreak.Hired.helper.TextileEmployeeServiceHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UploadHelper {
    private final CseEmployeeServiceImpl cseEmployeeServiceImplHelper;
    private final TextileEmployeeServiceHelper textileEmployeeServiceHelper;
    @Autowired
    public UploadHelper(CseEmployeeServiceImpl cseEmployeeServiceImplHelper ,
                        TextileEmployeeServiceHelper textileEmployeeServiceHelper) {
        this.cseEmployeeServiceImplHelper = cseEmployeeServiceImplHelper;
        this.textileEmployeeServiceHelper = textileEmployeeServiceHelper;
    }

    public  String saveEmployeeWithResumeURL(String department,String resumeUrl,Integer id){
        if (EmployeeType.CSE.getDepartment().equals(department)){
           return cseEmployeeServiceImplHelper.setResumeUrl(resumeUrl,id);

        }
        if(EmployeeType.TEXTILE.getDepartment().equals(department)){
            return textileEmployeeServiceHelper.setResumeUrl(resumeUrl,id);
        }
        return MassageConstant.FAILED_TO_UPLOAD.toString();
    }

}
