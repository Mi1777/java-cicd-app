pipeline {
    agent any
    
    tools {
        maven 'Maven'
        jdk 'JDK11'
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
                script {
                    // Créer le JAR manuellement sans exécuter les tests
                    bat '''
                        mvn jar:jar -DskipTests
                        if exist target\\*.jar (
                            echo "✅ JAR créé avec succès"
                        ) else (
                            echo "❌ Échec de création du JAR"
                            exit 1
                        )
                    '''
                }
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
