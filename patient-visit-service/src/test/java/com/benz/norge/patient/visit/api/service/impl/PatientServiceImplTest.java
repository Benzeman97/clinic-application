package com.benz.norge.patient.visit.api.service.impl;

import com.benz.norge.patient.visit.api.dao.PatientDao;
import com.benz.norge.patient.visit.api.entity.Patient;
import com.benz.norge.patient.visit.api.service.PatientService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@ExtendWith({SpringExtension.class})
@DisplayName(("PatientServiceTest"))
public class PatientServiceImplTest {

    @Autowired
    private PatientService patientService;

    @MockBean
    private PatientDao patientDao;

   @Test
   @DisplayName("SavePatientTest")
    public void savePatientTest()
    {
        Patient expectedPatient = getPatient_1();

        Mockito.when(patientDao.findById(expectedPatient.getPatientId())).thenReturn(Optional.of(expectedPatient));

        Patient actualPatient = patientService.savePatient(expectedPatient);

        Assertions.assertEquals(expectedPatient,actualPatient,()->expectedPatient+" should be returned with saved");

    }

    @Test
    @DisplayName("FindPatientTest")
    public void findPatientTest(){
       Patient expectedPatient =getPatient_1();

       Mockito.when(patientDao.findById(expectedPatient.getPatientId())).thenReturn(Optional.of(expectedPatient));

       Patient actualPatient = patientService.findPatient(expectedPatient.getPatientId());

       Assertions.assertEquals(expectedPatient,actualPatient,()->expectedPatient+ "should be returned");
    }

    @Test
    @DisplayName("AllPatientsTest")
    public void getPatients(){
        List<Patient> expectedPatients = new ArrayList<>(Arrays.asList(getPatient_1(),getPatient_2()));

        Mockito.when(patientDao.findAll()).thenReturn(expectedPatients);

        List<Patient> actualPatients= patientService.getPatients();

        Assertions.assertEquals(expectedPatients,actualPatients,()->"patient list should be returned");

    }

    @Test
    @DisplayName("UpdatePatientTest")
    public void updatePatientTest(){
       String patientId="COL9834";
       Patient expectedPatient=getPatient_2();

       Mockito.when(patientDao.save(expectedPatient)).thenReturn(expectedPatient);

       Patient actualPatient= patientService.updatePatient(patientId,getPatient_2());

       Assertions.assertEquals(expectedPatient,actualPatient,expectedPatient+" should be returned with updated");
    }

    @Test
    @DisplayName("DeletePatientTest")
    public void deletePatientTest(){

      String patientId="COL9834";

      Patient deletePatient=getPatient_2();

      Mockito.when(patientDao.findById(patientId)).thenReturn(Optional.of(deletePatient));

      patientService.deletePatient(patientId);

      Mockito.verify(patientDao,Mockito.times(1)).delete(deletePatient);

    }

    private Patient getPatient_1(){
        Patient patient=new Patient();
        patient.setPatientId("KL0987");
        patient.setPatientName("John Mid");
        patient.setGender("male");
        patient.setAge(41);
        patient.setCreatedBy("Solo Rider");
        return patient;
    }

    private Patient getPatient_2(){
        Patient patient=new Patient();
        patient.setPatientId("COL9834");
        patient.setPatientName("Belly Kent");
        patient.setGender("female");
        patient.setAge(49);
        patient.setCreatedBy("Solo Rider");
        return patient;
    }
}


