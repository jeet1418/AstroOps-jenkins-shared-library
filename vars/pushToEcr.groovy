def call(Map config = [:]) {

    if (!config.image) {
        error "Missing required parameter: image"
    }

    def image = config.image

    sh """
        echo "=========================================="
        echo "Pushing Docker Image"
        echo "Image: ${image}"
        echo "=========================================="

        docker push ${image}
    """
}