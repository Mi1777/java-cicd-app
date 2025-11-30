pipeline {
    agent any
    
    tools {
        maven 'Maven'
        jdk 'JDK11'
    }
    
    stages {
        stage('Build') {
            steps {
                echo '🔨 Build Maven...'
                bat 'mvn clean compile'
            }
        }
        
        stage('Tests') {
            steps {
                echo '🧪 Tests unitaires...'
                bat 'mvn test'
            }
            post {
                always {
                    junit '**/target/surefire-reports/*.xml'
                }
            }
        }
        
        stage('Package') {
            steps {
                echo '📦 Création du WAR...'
                bat 'mvn package'
            }
        }
    }
    
    post {
        success {
            echo '✅ Pipeline réussie !'
            archiveArtifacts 'target/*.war'
        }
        failure {
            echo '❌ Échec de la pipeline'
        }
    }
}
