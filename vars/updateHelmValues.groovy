def call(Map config = [:]) {

    if (!config.helmFile) {
        error "Missing required parameter: helmFile"
    }
    if (!config.imageRepo) {
        error "Missing required parameter: imageRepo"
    }
    if (!config.imageTag) {
        error "Missing required parameter: imageTag"
    }

    def helmFile    = config.helmFile
    def imageRepo   = config.imageRepo
    def imageTag    = config.imageTag

    sh """
        echo "=========================================="
        echo "Updating Helm Values"
        echo "File: ${helmFile}"
        echo "Repository: ${imageRepo}"
        echo "Tag: ${imageTag}"
        echo "=========================================="

        yq -i \
            '.image.repository = "${imageRepo}"' \
            ${helmFile}

        yq -i \
            '.image.tag = "${imageTag}"' \
            ${helmFile}

        echo ""
        echo "Updated values.yaml:"
        cat ${helmFile}
    """
}