def call(Map config = [:]) {

    if (!config.servicePath) {
        error "Missing required parameter: servicePath"
    }

    def servicePath = config.servicePath

    dir(servicePath) {

        sh '''
            mkdir -p "$WORKSPACE/.trivy-cache"

            trivy fs \
                --cache-dir "$WORKSPACE/.trivy-cache" \
                . > trivyfs.txt
        '''
    }
}