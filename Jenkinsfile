pipeline {
    agent any
    
    tools {
        maven 'Maven'
        jdk 'JDK11'
    }
    
    stages {
        stage('Build & Package') {
            steps {
                echo '🔨 Build et package...'
                bat 'mvn clean package -DskipTests'
            }
        }
    }
    
    post {
        success {
            echo '✅ Pipeline réussie !'
            archiveArtifacts 'target/*.jar'
        }
        always {
            echo '📊 Build terminé'
        }
    }
}
