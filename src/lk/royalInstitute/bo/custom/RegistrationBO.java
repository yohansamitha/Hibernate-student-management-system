package lk.royalInstitute.bo.custom;

import lk.royalInstitute.bo.SuperBO;
import lk.royalInstitute.dto.RegistrationDTO;

public interface RegistrationBO extends SuperBO {


    String getRegistrationID();

    boolean placeRegistration(RegistrationDTO registrationDTO);
}
