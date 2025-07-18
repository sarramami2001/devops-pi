package tn.esprit.spring.kaddem;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ApiEndpointsTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void testGetAllEtudiants() {
        ResponseEntity<String> response = restTemplate.getForEntity("/kaddem/etudiant/retrieve-all-etudiants", String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody().contains("nomE"));
    }

    @Test
    void testAddEtudiant() {
        String json = "{" +
                "\"nomE\":\"TestNom\"," +
                "\"prenomE\":\"TestPrenom\"}";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<>(json, headers);
        ResponseEntity<String> response = restTemplate.postForEntity("/kaddem/etudiant/add-etudiant", request, String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void testUpdateEtudiant() {
        // Suppose qu'un étudiant avec id 1 existe
        String json = "{" +
                "\"idEtudiant\":1," +
                "\"nomE\":\"UpdatedNom\"," +
                "\"prenomE\":\"UpdatedPrenom\"}";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<>(json, headers);
        ResponseEntity<String> response = restTemplate.exchange("/kaddem/etudiant/update-etudiant", HttpMethod.PUT, request, String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void testDeleteEtudiant() {
        // Suppose qu'un étudiant avec id 1 existe
        ResponseEntity<Void> response = restTemplate.exchange("/kaddem/etudiant/remove-etudiant/1", HttpMethod.DELETE, null, Void.class);
        assertTrue(response.getStatusCode().is2xxSuccessful() || response.getStatusCode() == HttpStatus.NOT_FOUND);
    }

    @Test
    void testAddDepartement() {
        String json = "{" +
                "\"nomDepart\":\"TestDepartement\"}";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<>(json, headers);
        ResponseEntity<String> response = restTemplate.postForEntity("/kaddem/departement/add-departement", request, String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void testUpdateDepartement() {
        // Suppose qu'un département avec id 1 existe
        String json = "{" +
                "\"idDepart\":1," +
                "\"nomDepart\":\"UpdatedDepartement\"}";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<>(json, headers);
        ResponseEntity<String> response = restTemplate.exchange("/kaddem/departement/update-departement", HttpMethod.PUT, request, String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void testDeleteDepartement() {
        // Suppose qu'un département avec id 1 existe
        ResponseEntity<Void> response = restTemplate.exchange("/kaddem/departement/remove-departement/1", HttpMethod.DELETE, null, Void.class);
        assertTrue(response.getStatusCode().is2xxSuccessful() || response.getStatusCode() == HttpStatus.NOT_FOUND);
    }

    @Test
    void testGetAllDepartements() {
        ResponseEntity<String> response = restTemplate.getForEntity("/kaddem/departement/retrieve-all-departements", String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void testGetAllEquipes() {
        ResponseEntity<String> response = restTemplate.getForEntity("/kaddem/equipe/retrieve-all-equipes", String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void testGetAllUniversites() {
        ResponseEntity<String> response = restTemplate.getForEntity("/kaddem/universite/retrieve-all-universites", String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void testGetAllContrats() {
        ResponseEntity<String> response = restTemplate.getForEntity("/kaddem/contrat/retrieve-all-contrats", String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}
