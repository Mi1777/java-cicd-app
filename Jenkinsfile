pipeline {
    agent any
    
    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }
        
        stage('Install Maven') {
            steps {
                echo '📥 Installation de Maven...'
                bat '''
                    if not exist "C:\\maven" (
                        curl -L https://archive.apache.org/dist/maven/maven-3/3.9.6/binaries/apache-maven-3.9.6-bin.zip -o maven.zip
                        powershell -Command "Expand-Archive -Path maven.zip -DestinationPath C:\\"
                        ren "C:\\apache-maven-3.9.6" "C:\\maven"
                        del maven.zip
                    )
                '''
            }
        }
        
        stage('Build') {
            steps {
                echo '🔨 Construction du projet...'
                bat 'C:\\maven\\bin\\mvn.cmd clean package -DskipTests'
            }
        }
    }
    
    post {
        always {
            echo '📋 Pipeline terminé'
        }
    }
}
