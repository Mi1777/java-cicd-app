pipeline {
    agent any
    
    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }
        
        stage('Compile') {
            steps {
                echo '🔨 Compilation...'
                bat 'mvn clean compile'
            }
        }
        
        stage('Package') {
            steps {
                echo '📦 Création du JAR...'
                bat 'mvn clean package -DskipTests'
            }
        }
    }
    
    post {
        always {
            echo '📋 Build terminé'
        }
        success {
            echo '✅ Build réussi !'
            archiveArtifacts 'target/*.jar'
        }
        failure {
            echo '❌ Build échoué !'
        }
    }
}
