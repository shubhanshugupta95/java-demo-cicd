pipeline {
  agent {
    kubernetes {
      label 'jenkins-agent'
      yaml """
apiVersion: v1
kind: Pod
spec:
  containers:
  - name: maven
    image: maven:3.9.9-eclipse-temurin-17
    command:
    - cat
    tty: true

  - name: docker
    image: docker:24.0.5
    command:
    - cat
    tty: true
    volumeMounts:
    - name: docker-sock
      mountPath: /var/run/docker.sock

  volumes:
  - name: docker-sock
    hostPath:
      path: /var/run/docker.sock
"""
    }
  }

  environment {
    IMAGE_NAME = "shubhanshugupta/java-demo-cicd"
  }

  stages {
    stage('Build (Maven)') {
      steps {
        container('maven') {
          sh 'mvn -B clean package'
        }
      }
    }

    stage('Build Docker Image') {
      steps {
        container('docker') {
          sh 'docker build -t $IMAGE_NAME:latest .'
        }
      }
    }

    stage('Push to Docker Hub') {
      steps {
        container('docker') {
          withCredentials([usernamePassword(
            credentialsId: 'dockerhub-creds',
            usernameVariable: 'USER',
            passwordVariable: 'PASS'
          )]) {
            sh '''
              echo $PASS | docker login -u $USER --password-stdin
              docker push $IMAGE_NAME:latest
            '''
          }
        }
      }
    }
  }
}
