def call(Map config = [:]) {

    if (!config.credentialsId) {
        error "Missing required parameter: credentialsId"
    }

    if (!config.gitUrl) {
        error "Missing required parameter: gitUrl"
    }

    if (!config.commitMessage) {
        error "Missing required parameter: commitMessage"
    }

    def credentialsId = config.credentialsId
    def gitUrl        = config.gitUrl
    def commitMessage = config.commitMessage

    sh """
        git config user.email "jenkins@example.com"
        git config user.name "Jenkins"

        git add .

        git diff --cached --quiet || git commit -m "${commitMessage}"
    """

    withCredentials([
        usernamePassword(
            credentialsId: credentialsId,
            usernameVariable: 'GIT_USERNAME',
            passwordVariable: 'GIT_PASSWORD'
        )
    ]) {

        sh """
            git push \
                https://\\\${GIT_USERNAME}:\\\${GIT_PASSWORD}@${gitUrl} \
                HEAD:main
        """
    }
}