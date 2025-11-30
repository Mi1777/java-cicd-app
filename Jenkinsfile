pipeline {
    agent any
    
    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }
        
        stage('Download Maven Wrapper') {
            steps {
                echo '📥 Téléchargement de Maven Wrapper...'
                bat '''
                    @echo off
                    if not exist mvnw.cmd (
                        echo Téléchargement de Maven Wrapper...
                        powershell -Command "Invoke-WebRequest -Uri 'https://repo.maven.apache.org/maven2/io/takari/maven-wrapper/0.5.6/maven-wrapper-0.5.6.tar.gz' -OutFile 'wrapper.tar.gz' -UseBasicParsing"
                        tar -xzf wrapper.tar.gz
                        copy maven-wrapper-0.5.6\maven-wrapper.jar .mvn\wrapper\
                        copy maven-wrapper-0.5.6\maven-wrapper.properties .mvn\wrapper\
                        echo Téléchargement de mvnw...
                        powershell -Command "Invoke-WebRequest -Uri 'https://raw.githubusercontent.com/takari/maven-wrapper/master/maven-wrapper-distribution/src/resources/mvnw' -OutFile 'mvnw' -UseBasicParsing"
                        echo Téléchargement de mvnw.cmd...
                        powershell -Command "Invoke-WebRequest -Uri 'https://raw.githubusercontent.com/takari/maven-wrapper/master/maven-wrapper-distribution/src/resources/mvnw.cmd' -OutFile 'mvnw.cmd' -UseBasicParsing"
                        attrib +r mvnw
                        attrib +r mvnw.cmd
                        rmdir /s /q maven-wrapper-0.5.6
                        del wrapper.tar.gz
                    )
                '''
            }
        }
        
        stage('Build') {
            steps {
                echo '🔨 Construction...'
                bat 'mvnw.cmd clean package -DskipTests'
            }
        }
    }
}
