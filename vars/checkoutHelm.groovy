def call(Map config = [:]) {

    if (!config.url) {
        error "Missing required parameter: url"
    }
    if (!config.credentialsId) {
        error "Missing required parameter: credentialsId"
    }

    def directory     = config.get('directory', 'helm-repo')
    def credentialsId = config.credentialsId
    def url           = config.url
    def branch        = config.get('branch', 'main')

    dir(directory) {

        git(
            credentialsId: credentialsId,
            url: url,
            branch: branch
        )
    }
}