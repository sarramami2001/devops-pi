package tn.esprit.spring.kaddem;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import tn.esprit.spring.kaddem.repositories.EtudiantRepository;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ApiTest {
    @Autowired
    private EtudiantRepository etudiantRepository;

    @Test
    void testEtudiantCount() {
        long count = etudiantRepository.count();
        assertTrue(count >= 10, "Il doit y avoir au moins 10 étudiants en base");
    }
}
