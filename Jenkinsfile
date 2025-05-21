pipeline {
    options {
        buildDiscarder(logRotator(numToKeepStr: '10', artifactNumToKeepStr: '10'))
    }
    agent any
    tools {
        //gradle 'gradle-8.6'
        jdk "JAVA17"
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
            archiveArtifacts artifacts: 'build/libs/*.war', fingerprint: true
            junit 'build/test-results/**/*.xml'
            jacoco(
                    execPattern: 'build/jacoco/*.exec',
                    classPattern: 'build/classes',
                    sourcePattern: 'src/main/java'
            )
            recordIssues(
                    tools: [checkStyle(pattern: 'build/reports/checkstyle/*.xml'),
                            pmdParser(pattern: 'build/reports/pmd/*.xml')]
            )
            recordCoverage(
                    tools: [[parser: 'JACOCO', pattern: 'build/reports/jacoco/test/jacocoTestReport.xml']],
                    qualityGates: [[threshold: 80, metric: 'LINE', unstable: true]]
            )
        }
        success {
            echo "Buid succeeded"
        }
        failure {
            echo "Build failed"
        }
    }
}