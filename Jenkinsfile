pipeline{
    agent any

    options{
        skipDefaultCheckout()
        timestamps()
        buildDiscarder(logRotator(numToKeepStr: '10'))
        disableConcurrentBuilds()
    }

    triggers{
        pollSCM('H/5 * * * *')
    }

    stages{

        stage('Checkout'){
            steps{
                checkout scm
                echo 'Source code downloaded!'
            }
        }

        stage('Package'){
            steps{
                bat 'mvn -B clean verify'
            }
        }

        stage('Archive'){
            steps{
                archiveArtifacts(
                    artifacts: 'target/mini-calculator-1.0.0.jar'
                )
            }
        }

         stage('Deploy') {
                    steps {
                        bat '''
                            if not exist "C:\\jenkins-deploy\\mini-calculator" mkdir "C:\\jenkins-deploy\\mini-calculator"

                            copy /Y "target\\mini-calculator-1.0.0.jar" "C:\\jenkins-deploy\\mini-calculator\\mini-calculator.jar"
                        '''

                        bat 'java -jar "C:\\jenkins-deploy\\mini-calculator\\mini-calculator.jar"'
                    }
                }
    }

    post{

     always {
        junit testResults: 'target/surefire-reports/*.xml',
         allowEmptyResults: true
     }

      success {
         echo 'Build completed successfully!'
      }

      failure {
         echo 'Build failed!'
      }
    }
}