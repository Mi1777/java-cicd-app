pipeline {
    agent any
    
    tools {
        maven 'Maven'
        jdk 'JDK11'
    }
    
    environment {
        DOCKER_IMAGE = 'java-cicd-app'
    }
    
    stages {
        stage('Checkout') {
            steps {
                echo '📥 Récupération du code depuis GitHub...'
                checkout scm
            }
        }
        
        stage('Build') {
            steps {
                echo '🔨 Installation des dépendances Maven...'
                bat 'wsl mvn clean install -DskipTests'
            }
        }
        
        stage('Tests') {
            steps {
                echo '🧪 Exécution des tests unitaires (JUnit)...'
                bat 'wsl mvn test'
            }
            post {
                always {
                    junit '**/target/surefire-reports/*.xml'
                }
            }
        }
        
        stage('SAST - SonarQube') {
            steps {
                echo '🔍 Analyse de sécurité avec SonarQube...'
                script {
                    try {
                        withSonarQubeEnv('SonarQube') {
                            bat 'wsl mvn sonar:sonar'
                        }
                    } catch (Exception e) {
                        echo "⚠️ SonarQube non disponible, passage au stage suivant"
                    }
                }
            }
        }
        
        stage('Build Docker Image') {
            steps {
                echo '🐳 Construction de l\'image Docker...'
                bat 'wsl docker build -t ${DOCKER_IMAGE}:${BUILD_NUMBER} .'
                bat 'wsl docker tag ${DOCKER_IMAGE}:${BUILD_NUMBER} ${DOCKER_IMAGE}:latest'
            }
        }
        
        stage('Déploiement') {
            steps {
                echo '🚀 Déploiement du container...'
                bat 'wsl docker stop java-app || true'
                bat 'wsl docker rm java-app || true'
                bat 'wsl docker run -d --name java-app -p 8081:8080 ${DOCKER_IMAGE}:latest'
            }
        }
    }
    
    post {
        success {
            echo '✅ Pipeline exécutée avec succès !'
        }
        failure {
            echo '❌ Échec de la pipeline !'
        }
    }
}
