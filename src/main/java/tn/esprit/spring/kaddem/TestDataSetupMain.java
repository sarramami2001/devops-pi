package tn.esprit.spring.kaddem;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestDataSetupMain {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext("tn.esprit.spring.kaddem");
        // Le bean TestDataSetup sera exécuté automatiquement car il implémente CommandLineRunner
        System.out.println("Test data setup terminé.");
        System.exit(0);
    }
}
