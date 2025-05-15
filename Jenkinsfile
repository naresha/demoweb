pipeline {
    agent any
    tools {
        //gradle 'gradle-8.6'
        jdk JAVA17
    }
    environment {
        VERSION = "${env.BUILD_NUMBER}"
    }
    stages {
        stage('Checkout') {
            steps {
                git url: 'https://github.com/naresha/demoweb.git', branch: 'main'
            }
        }
        stage('Build & Analyze') {
            steps {
                sh """
                    ./gradlew clean build \
                    -PbuildVersion=${VERSION} \
                    -x test
                """
                sh "./gradlew checkstyleMain pmdMain"
            }
        }
        stage('Test & Coverage') {
            steps {
                sh "./gradlew test jacocoTestReport"
            }
        }
    }
    post {
        always {
            archiveArtifacts artifacts: 'build/libs/*.jar', fingerprint: true
            junit 'build/test-results/**/*.xml'
            jacoco(
                execPattern: 'build/jacoco/*.exec',
                classPattern: 'build/classes',
                sourcePattern: 'src/main/java'
            )
            recordIssues(
                tools: [checkStyle(pattern: 'build/reports/checkstyle/*.xml'),
                        pmd(pattern: 'build/reports/pmd/*.xml')]
            )
        }
    }
}