pipeline {
    agent any
    
    tools {
        maven 'Maven-3.9'
        jdk 'JDK-17'
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
                sh 'mvn clean install -DskipTests'
            }
        }
        
        stage('Tests') {
            steps {
                echo '🧪 Exécution des tests unitaires (JUnit)...'
                sh 'mvn test'
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
                            sh 'mvn sonar:sonar'
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
                sh '''
                    docker build -t ${DOCKER_IMAGE}:${BUILD_NUMBER} .
                    docker tag ${DOCKER_IMAGE}:${BUILD_NUMBER} ${DOCKER_IMAGE}:latest
                '''
            }
        }
        
        stage('Déploiement') {
            steps {
                echo '🚀 Déploiement du container...'
                sh '''
                    docker stop java-app || true
                    docker rm java-app || true
                    docker run -d --name java-app -p 8081:8080 ${DOCKER_IMAGE}:latest
                '''
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
