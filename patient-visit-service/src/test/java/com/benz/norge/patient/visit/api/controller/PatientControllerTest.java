package com.benz.norge.patient.visit.api.controller;

import com.benz.norge.patient.visit.api.entity.Patient;
import com.benz.norge.patient.visit.api.service.PatientService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@WebMvcTest
@ExtendWith({SpringExtension.class})
@DisplayName("PatientControllerTest")
public class PatientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PatientService patientService;

    @Test
    @DisplayName("SavePatientTest")
    public void savePatientTest() throws Exception{

         String expectedPatient=new ObjectMapper().writeValueAsString(getPatient_1());

        Mockito.when(patientService.savePatient(Mockito.any(Patient.class))).thenReturn(getPatient_1());

        MvcResult result=mockMvc.perform(post("/patient/save").contentType(MediaType.APPLICATION_JSON_VALUE).content(expectedPatient))
                .andExpect(status().isCreated()).andReturn();

        int actualStatus = result.getResponse().getStatus();

        Assertions.assertEquals(HttpStatus.CREATED.value(),actualStatus,()->"expected status "+HttpStatus.CREATED.value()+ " but actual was "+actualStatus);
    }

    @Test
    @DisplayName("FindPatientTest")
    public void findPatientTest() throws Exception{

        Mockito.when(patientService.findPatient(Mockito.any(String.class))).thenReturn(getPatient_1());

        MvcResult result=mockMvc.perform(get("/patient/{id}",getPatient_1().getPatientId()).accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk()).andReturn();

        int actualStatus=result.getResponse().getStatus();

        Assertions.assertEquals(HttpStatus.OK.value(),actualStatus,()->"expected status "+HttpStatus.OK.value()+ " but actual was "+actualStatus);
    }

    @Test
    @DisplayName("AllPatientTest")
    public void getPatientsTest() throws Exception{

        List<Patient> patients=new ArrayList<>(Arrays.asList(getPatient_1(),getPatient_2()));
        Mockito.when(patientService.getPatients()).thenReturn(patients);

        MvcResult result=mockMvc.perform(get("/patient").accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk()).andReturn();

        int actualStatus = result.getResponse().getStatus();

        Assertions.assertEquals(HttpStatus.OK.value(),actualStatus,()->"expected status "+HttpStatus.OK.value()+ " but actual was "+actualStatus);

    }

    @Test
    @DisplayName("UpdatePatientTest")
    public void updatePatientTest() throws Exception{

        String patientId="COL9834";
        String expectedPatient=new ObjectMapper().writeValueAsString(getPatient_2());

        Mockito.when(patientService.updatePatient(Mockito.any(String.class),Mockito.any(Patient.class))).thenReturn(getPatient_2());

        MvcResult result=mockMvc.perform(put("/patient/{id}",patientId).contentType(MediaType.APPLICATION_JSON_VALUE).content(expectedPatient))
                .andExpect(status().isCreated()).andReturn();

        int actualStatus=result.getResponse().getStatus();

        Assertions.assertEquals(HttpStatus.CREATED.value(),actualStatus,()->"expected status "+HttpStatus.CREATED.value()+ " but actual was "+actualStatus);

    }

    @Test
    @DisplayName("DeletePatientTest")
    public void deletePatientTest() throws Exception{

        String patientId="COL9834";

        MvcResult result=mockMvc.perform(delete("/patient/{id}",patientId))
                .andExpect(status().isOk()).andReturn();

        int actualStatus=result.getResponse().getStatus();

        Assertions.assertEquals(HttpStatus.OK.value(),actualStatus,()->"expected status "+HttpStatus.OK.value()+ " but actual was "+actualStatus);

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
