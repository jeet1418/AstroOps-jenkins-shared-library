def call(Map config = [:]) {

    if (!config.region) {
        error "Missing required parameter: region"
    }

    if (!config.registry) {
        error "Missing required parameter: registry"
    }

    def region   = config.region
    def registry = config.registry

    sh """
        mkdir -p "\$WORKSPACE/.docker"

        export DOCKER_CONFIG="\$WORKSPACE/.docker"

        aws ecr get-login-password \
            --region ${region} |
        docker login \
            --username AWS \
            --password-stdin ${registry}
    """
}