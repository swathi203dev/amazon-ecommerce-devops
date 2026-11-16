pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build with Gradle') {
            steps {
                sh '''
                docker run --rm \
                  -v jenkins_home:/var/jenkins_home \
                  -w "$WORKSPACE/app" \
                  gradle:8.14.4-jdk17 \
                  gradle clean bootJar
                '''
            }
        }

        stage('Build Docker Image') {
            steps {
                sh 'docker build -t amazon-ecommerce:latest app'
            }
        }

        stage('Deploy Application') {
            steps {
                sh '''
                docker stop amazon-ecommerce-compose || true
                docker rm amazon-ecommerce-compose || true
                docker run -d \
                  --name amazon-ecommerce-compose \
                  --restart unless-stopped \
                  -p 8080:8080 \
                  amazon-ecommerce:latest
                '''
            }
        }
    }
}
