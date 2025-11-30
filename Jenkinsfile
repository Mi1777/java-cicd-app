pipeline {
    agent any
    
    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }
        
        stage('Build') {
            steps {
                script {
                    // Essayer différentes méthodes dans l'ordre
                    try {
                        // 1. Essayer Maven Wrapper s'il existe
                        if (fileExists('mvnw.cmd')) {
                            echo '🔨 Utilisation de Maven Wrapper...'
                            bat 'mvnw.cmd clean package -DskipTests'
                        } 
                        // 2. Essayer Maven système
                        else if (bat(script: 'where mvn', returnStatus: true) == 0) {
                            echo '🔨 Utilisation de Maven système...'
                            bat 'mvn clean package -DskipTests'
                        }
                        // 3. Installer Maven Wrapper automatiquement
                        else {
                            echo '📥 Installation automatique de Maven Wrapper...'
                            bat '''
                                @echo off
                                echo Installation de Maven Wrapper...
                                
                                # Créer la structure de dossiers
                                if not exist ".mvn\\wrapper" mkdir ".mvn\\wrapper"
                                
                                # Télécharger les fichiers nécessaires
                                powershell -Command "Invoke-WebRequest -Uri 'https://repo.maven.apache.org/maven2/io/takari/maven-wrapper/0.5.6/maven-wrapper-0.5.6.jar' -OutFile '.mvn\\wrapper\\maven-wrapper.jar' -UseBasicParsing"
                                echo distributionUrl=https://repo.maven.apache.org/maven2/org/apache/maven/apache-maven/3.9.6/apache-maven-3.9.6-bin.zip > .mvn\\wrapper\\maven-wrapper.properties
                                echo wrapperUrl=https://repo.maven.apache.org/maven2/io/takari/maven-wrapper/0.5.6/maven-wrapper-0.5.6.jar >> .mvn\\wrapper\\maven-wrapper.properties
                                
                                powershell -Command "Invoke-WebRequest -Uri 'https://raw.githubusercontent.com/takari/maven-wrapper/master/maven-wrapper-distribution/src/resources/mvnw.cmd' -OutFile 'mvnw.cmd' -UseBasicParsing"
                                
                                echo Construction avec Maven Wrapper...
                                mvnw.cmd clean package -DskipTests
                            '''
                        }
                    } catch (Exception e) {
                        error "❌ Toutes les méthodes de build ont échoué: ${e.message}"
                    }
                }
            }
        }
    }
    
    post {
        always {
            echo '📋 Pipeline terminé'
        }
        success {
            archiveArtifacts 'target/*.jar'
        }
    }
}
