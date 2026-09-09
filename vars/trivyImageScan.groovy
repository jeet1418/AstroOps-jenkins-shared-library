def call(Map config = [:]) {

    if (!config.image) {
        error "Missing required parameter: image"
    }

    def image = config.image

    sh """
        mkdir -p "\$WORKSPACE/.trivy-cache"

        echo "=========================================="
        echo "Scanning Docker Image"
        echo "Image: ${image}"
        echo "=========================================="

        trivy image \
            --cache-dir "\$WORKSPACE/.trivy-cache" \
            "${image}"
    """
}