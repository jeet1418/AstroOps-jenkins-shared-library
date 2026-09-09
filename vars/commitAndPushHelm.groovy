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

        sh '''
            git config credential.helper \
                '!f() { echo "username=$GIT_USERNAME"; echo "password=$GIT_PASSWORD"; }; f'

            git push \
                "https://github.com/jeet1418/AstroOps-helm.git" \
                HEAD:main

            git config --unset credential.helper
        '''
    }
}