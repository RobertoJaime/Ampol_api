## Install

```shell
  Description
    At the project root folder, install the project dependencies by running the following command:
  './gradlew clean build'
    installs the project dependencies.
```

## Usage
```shell
  npx playwright test
    Runs the end-to-end tests.

```
## Options

```shell
Description
    Inside the /tests directory, you can run several commands:

  npx playwright test
    Runs the end-to-end tests.

  npx playwright test --ui
    Starts the interactive UI mode.

  npx playwright test --project=chromium
    Runs the tests only on Desktop Chrome.

  npx playwright test example
    Runs the tests in a specific file.

  npx playwright test --debug
    Runs the tests in debug mode.

  npx playwright codegen
    Auto generate tests with Codegen.