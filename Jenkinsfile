pipeline {
    agent any
    tools {
        maven 'M3'
        jdk 'JDK8'
    }
    stage('Package') {
    steps {
        bat 'mvn dependency:purge-local-repository'
        bat 'mvn clean package -DskipTests'
    }
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
