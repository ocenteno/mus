@Library('mi-libreria') _

def url = 'https://github.com/ocenteno/mus.git'

node {
   stage('Clone') { 
      cleanWs()
      git branch: 'master', url: url
   }
   stage('Build') {
      sh 'mvn compile'
   }
   stage("Publish") {
      def version =  sh( returnStdout: true, script: 'git tag' )
      etiquetar('2.0', url, 'CredencialesGithub')
   }
}
