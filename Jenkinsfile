pipeline {
    agent any
    
    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }
        
        stage('Build with Wrapper') {
            steps {
                echo '🔨 Construction avec Maven Wrapper...'
                script {
                    // Vérifier si Maven Wrapper existe
                    def wrapperExists = fileExists('mvnw') || fileExists('mvnw.cmd')
                    
                    if (wrapperExists) {
                        bat 'mvnw.cmd clean package -DskipTests'
                    } else {
                        echo '❌ Maven Wrapper non trouvé. Utilisation de Maven système...'
                        // Essayer Maven système
                        bat '''
                            where mvn >nul 2>&1
                            if %errorlevel% equ 0 (
                                mvn clean package -DskipTests
                            ) else (
                                echo "ERREUR: Maven non trouvé!"
                                echo "Solutions:"
                                echo "1. Installez Maven sur le système"
                                echo "2. Ajoutez Maven Wrapper au projet (mvn -N io.takari:maven:wrapper)"
                                exit 1
                            )
                        '''
                    }
                }
            }
        }
    }
    
    post {
        always {
            echo '📋 Pipeline terminé'
        }
    }
}
