package com.cicd.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    private static List<String> history = new ArrayList<>();
    
    public static void main(String[] args) {
        System.out.println("==============================================");
        System.out.println("  📱 CALCULATRICE SCIENTIFIQUE - v1.0.0");
        System.out.println("  👤 Développé par: Amira");
        System.out.println("  🚀 Pipeline CI/CD: Jenkins + Docker + GitHub");
        System.out.println("==============================================\n");
        
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        
        while (running) {
            afficherMenu();
            System.out.print("Votre choix: ");
            
            try {
                int choix = scanner.nextInt();
                
                switch (choix) {
                    case 1:
                        effectuerCalcul(scanner, "addition");
                        break;
                    case 2:
                        effectuerCalcul(scanner, "soustraction");
                        break;
                    case 3:
                        effectuerCalcul(scanner, "multiplication");
                        break;
                    case 4:
                        effectuerCalcul(scanner, "division");
                        break;
                    case 5:
                        effectuerCalcul(scanner, "puissance");
                        break;
                    case 6:
                        effectuerCalcul(scanner, "racine");
                        break;
                    case 7:
                        afficherHistorique();
                        break;
                    case 8:
                        effacerHistorique();
                        break;
                    case 0:
                        System.out.println("\n👋 Merci d'avoir utilisé la calculatrice!");
                        running = false;
                        break;
                    default:
                        System.out.println("❌ Choix invalide!");
                }
            } catch (Exception e) {
                System.out.println("❌ Erreur: Entrée invalide!");
                scanner.nextLine(); // Clear buffer
            }
            
            if (running) {
                System.out.println("\nAppuyez sur Entrée pour continuer...");
                scanner.nextLine();
                scanner.nextLine();
            }
        }
        
        scanner.close();
    }
    
    private static void afficherMenu() {
        System.out.println("\n╔════════════════════════════════╗");
        System.out.println("║         MENU PRINCIPAL         ║");
        System.out.println("╠════════════════════════════════╣");
        System.out.println("║ 1. ➕ Addition                 ║");
        System.out.println("║ 2. ➖ Soustraction             ║");
        System.out.println("║ 3. ✖️  Multiplication          ║");
        System.out.println("║ 4. ➗ Division                 ║");
        System.out.println("║ 5. 🔢 Puissance                ║");
        System.out.println("║ 6. √  Racine carrée            ║");
        System.out.println("║ 7. 📋 Voir l'historique        ║");
        System.out.println("║ 8. 🗑️  Effacer l'historique    ║");
        System.out.println("║ 0. 🚪 Quitter                  ║");
        System.out.println("╚════════════════════════════════╝");
    }
    
    private static void effectuerCalcul(Scanner scanner, String operation) {
        try {
            double a, b, resultat;
            String calcul;
            
            if (operation.equals("racine")) {
                System.out.print("Entrez un nombre: ");
                a = scanner.nextDouble();
                resultat = Math.sqrt(a);
                calcul = String.format("√%.2f = %.2f", a, resultat);
            } else {
                System.out.print("Entrez le premier nombre: ");
                a = scanner.nextDouble();
                System.out.print("Entrez le deuxième nombre: ");
                b = scanner.nextDouble();
                
                switch (operation) {
                    case "addition":
                        resultat = addition(a, b);
                        calcul = String.format("%.2f + %.2f = %.2f", a, b, resultat);
                        break;
                    case "soustraction":
                        resultat = soustraction(a, b);
                        calcul = String.format("%.2f - %.2f = %.2f", a, b, resultat);
                        break;
                    case "multiplication":
                        resultat = multiplication(a, b);
                        calcul = String.format("%.2f × %.2f = %.2f", a, b, resultat);
                        break;
                    case "division":
                        resultat = division(a, b);
                        calcul = String.format("%.2f ÷ %.2f = %.2f", a, b, resultat);
                        break;
                    case "puissance":
                        resultat = puissance(a, b);
                        calcul = String.format("%.2f ^ %.2f = %.2f", a, b, resultat);
                        break;
                    default:
                        return;
                }
            }
            
            System.out.println("\n✅ Résultat: " + calcul);
            history.add(calcul);
            
        } catch (ArithmeticException e) {
            System.out.println("❌ Erreur: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("❌ Erreur: Entrée invalide!");
            scanner.nextLine();
        }
    }
    
    private static void afficherHistorique() {
        System.out.println("\n╔════════════════════════════════╗");
        System.out.println("║      📋 HISTORIQUE             ║");
        System.out.println("╚════════════════════════════════╝");
        
        if (history.isEmpty()) {
            System.out.println("  Aucun calcul effectué.");
        } else {
            for (int i = 0; i < history.size(); i++) {
                System.out.println("  " + (i + 1) + ". " + history.get(i));
            }
        }
    }
    
    private static void effacerHistorique() {
        history.clear();
        System.out.println("✅ Historique effacé!");
    }
    
    // Méthodes de calcul
    public static double addition(double a, double b) {
        return a + b;
    }
    
    public static double soustraction(double a, double b) {
        return a - b;
    }
    
    public static double multiplication(double a, double b) {
        return a * b;
    }
    
    public static double division(double a, double b) {
        if (b == 0) {
            throw new ArithmeticException("Division par zéro impossible!");
        }
        return a / b;
    }
    
    public static double puissance(double base, double exposant) {
        return Math.pow(base, exposant);
    }
    
    public static double racineCarree(double nombre) {
        if (nombre < 0) {
            throw new ArithmeticException("Racine carrée d'un nombre négatif impossible!");
        }
        return Math.sqrt(nombre);
    }
    
    // Getters pour les tests
    public static String getVersion() {
        return "1.0.0";
    }
    
    public static String getAuteur() {
        return "Amira";
    }
    
    public static String getDescription() {
        return "Calculatrice scientifique avec historique";
    }
}
