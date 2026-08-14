def call(Map config = [:]) {

    if (!config.image) {
        error "Missing required parameter: image"
    }
    if (!config.dockerfile) {
        error "Missing required parameter: dockerfile"
    }

    def image     = config.image
    def dockerfile = config.dockerfile
    def platform  = config.get('platform', 'linux/amd64')

    sh """
        echo "=========================================="
        echo "Building Docker Image"
        echo "Image: ${image}"
        echo "Platform: ${platform}"
        echo "Dockerfile: ${dockerfile}"
        echo "=========================================="

        docker buildx build \
            --platform ${platform} \
            -f ${dockerfile} \
            -t ${image} \
            --load \
            .
    """
}