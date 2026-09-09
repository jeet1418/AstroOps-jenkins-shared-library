def call(Map config = [:]) {

    if (!config.servicePath) {
        error "Missing required parameter: servicePath"
    }
    if (!config.projectName) {
        error "Missing required parameter: projectName"
    }
    if (!config.projectKey) {
        error "Missing required parameter: projectKey"
    }

    def servicePath = config.servicePath
    def projectName = config.projectName
    def projectKey  = config.projectKey

    dir(servicePath) {

        withSonarQubeEnv('sonar-server') {

            sh """
                sonar-scanner \
                    -Dsonar.projectName=${projectName} \
                    -Dsonar.projectKey=${projectKey} \
                    -Dsonar.sources=. \
                    -Dsonar.userHome="\$WORKSPACE/.sonar"
            """
        }
    }
}