def call(Map config = [:]) {

    if (!config.image) {
        error "Missing required parameter: image"
    }

    def image = config.image

    sh """
        echo "=========================================="
        echo "Scanning Docker Image"
        echo "Image: ${image}"
        echo "=========================================="

        trivy image ${image} > trivyimage.txt
    """
}