pipeline {
    agent any

    stages {
        stage('GIT Clone') {
            steps {
                git url: 'https://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@github.com/idodo01/2024.02.10_StyleCodi_SpringBoot.git', branch: 'master',
                branch: 'master',
                credentialsId: 'jenkins-github'
            }
        }
//         stage('Package') {
//             steps {
//                 sh "chmod 777 mvnw"
//                 sh "./mvnw package"
//             }
//         }
    }
}