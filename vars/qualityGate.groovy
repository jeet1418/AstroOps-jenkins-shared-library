def call(Map config = [:]) {

    waitForQualityGate(
        abortPipeline: config.get('abortPipeline', false),
        credentialsId: config.get('credentialsId', 'sonar-token')
    )
}