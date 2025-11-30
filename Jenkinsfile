pipeline {
    agent any
    
    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }
        
        stage('Install Maven via WSL') {
            steps {
                echo '📥 Installation de Maven via WSL...'
                bat '''
                    @echo off
                    echo Vérification de WSL...
                    wsl --list --verbose
                    
                    echo Installation de Maven via WSL Ubuntu...
                    wsl -d Ubuntu sudo apt update
                    wsl -d Ubuntu sudo apt install -y maven
                    
                    echo Vérification de l installation...
                    wsl -d Ubuntu mvn --version
                '''
            }
        }
        
        stage('Build via WSL') {
            steps {
                echo '🔨 Construction via WSL...'
                bat 'wsl -d Ubuntu mvn clean package -DskipTests'
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
