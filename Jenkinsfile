pipeline {
  agent {
    kubernetes {
      label 'jenkins-agent'
    }
  }

  environment {
    IMAGE_NAME = "shubhanshugupta95/java-demo-cicd"
  }

  stages {
    stage('Checkout') {
      steps {
        echo "Using SCM checkout"
      }
    }

    stage('Build (Maven)') {
      steps {
        sh 'mvn -B clean package'
      }
    }

    stage('Build Docker Image') {
      steps {
        sh 'docker build -t $IMAGE_NAME:latest .'
      }
    }

    stage('Push to Docker Hub') {
      steps {
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