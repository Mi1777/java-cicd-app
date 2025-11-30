pipeline {
    agent any
    
    tools {
        maven 'Maven'
        jdk 'JDK11'
    }
    stage('Nettoyage Cache') {
    steps {
        echo '🧹 Nettoyage cache Maven...'
        bat 'mvn dependency:purge-local-repository -DactTransitively=false -DreResolve=false'
    }
}
    stages {
        stage('Build') {
            steps {
                echo '🔨 Build Maven...'
                bat 'mvn clean compile -DskipTests'
            }
        }
        
        stage('Package') {
            steps {
                echo '📦 Création du JAR...'
                bat 'mvn package -DskipTests'
            }
        }
    }
    
    post {
        success {
            echo '✅ Pipeline réussie !'
            archiveArtifacts 'target/*.jar'
        }
        failure {
            echo '❌ Échec de la pipeline'
        }
    }
}
