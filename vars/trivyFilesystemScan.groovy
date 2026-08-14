def call(Map config = [:]) {

    if (!config.servicePath) {
        error "Missing required parameter: servicePath"
    }

    def servicePath = config.servicePath

    dir(servicePath) {

        sh '''
            trivy fs . > trivyfs.txt
        '''
    }
}