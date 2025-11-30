pipeline {
    agent any
    
    tools {
        // Utilisez les noms EXACTS que vous avez configurés dans Jenkins
        maven 'M3'  // ou le nom que vous avez configuré
        jdk 'JDK8'  // ou le nom que vous avez configuré
    }
    
    stages {
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
    }
}
