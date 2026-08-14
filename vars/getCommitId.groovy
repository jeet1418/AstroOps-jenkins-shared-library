def call() {

    return sh(
        script: 'git rev-parse --short=8 HEAD',
        returnStdout: true
    ).trim()
}