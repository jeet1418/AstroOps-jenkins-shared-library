<!-- markdownlint-disable-next-line -->
# <img src="https://get.jenkins.io/art/jenkins-logo/logo.svg" alt="Jenkins logo" width="35"> AstroOps Jenkins Shared Library

A reusable Jenkins Shared Library containing common CI/CD pipeline functions for building, scanning and deploying microservices.

The purpose of this library is to avoid duplicating the same Jenkins pipeline logic across multiple microservices.

---

## Shared Library Functions

The reusable Jenkins pipeline functions are stored inside the `vars/` directory.

| Function | Purpose |
|---|---|
| `checkoutApp()` | Checks out the application source code |
| `checkoutHelm()` | Checks out the Helm repository |
| `commitAndPushHelm()` | Commits and pushes Helm changes |
| `dockerBuild()` | Builds the Docker image |
| `ecrLogin()` | Authenticates Docker with Amazon ECR |
| `getCommitId()` | Gets the current Git commit ID |
| `pushToEcr()` | Pushes the Docker image to Amazon ECR |
| `qualityGate()` | Waits for the SonarQube quality gate |
| `sonarAnalysis()` | Runs SonarQube code analysis |
| `trivyFilesystemScan()` | Scans the source filesystem using Trivy |
| `trivyImageScan()` | Scans the Docker image using Trivy |
| `updateHelmValues()` | Updates the image repository and image tag in Helm values |

## Design Principles

This shared library follows these principles:

- **DRY (Don't Repeat Yourself)** – Common pipeline logic is implemented once.
- **Reusability** – The same functions can be used by multiple microservices.
- **Consistency** – All services follow the same CI/CD process.
- **Maintainability** – Changes to common pipeline logic can be made centrally.
- **Parameterization** – Service-specific values are passed through configuration maps.
- **Separation of Concerns** – Application-specific Jenkinsfiles define the pipeline, while the shared library provides reusable implementations.