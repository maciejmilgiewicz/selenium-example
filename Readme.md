# Browser Automation with Selenium

This project demonstrates browser automation test with Cucumber and Selenium in desktop and mobile mode.
It reads any number of input files from `resources/files/input` and extracts cars' registration numbers.
These are fed to `https://www.cazoo.co.uk/value-my-car/` in car details search.
All obtained cars' details are then validated against details stored in files under `resources/files/output`.
All output files need to be in CSV format with comma separated columns labelled as MAKE_MODEL, VARIANT_REG and YEAR_BODY.

## Managing State in Cucumber

Ways of managing test state used within this framework:
- WebDriver state is managed via thread safe DriverManager class using Singleton Design Pattern
- Step definitions transfer state to a subsequent step definitions via instance variables, as per [Cucumber documentation](https://cucumber.io/docs/cucumber/step-definitions/?lang=java#state-management)
- For larger test suites, where step definitions are divided between multiple classes it is also possible to use Text Context / Scenario Context Patterns with PicoContainer for dependency management

## Browser Viewport

BrowserViewport enum defines browser viewport dimensions for multiple browser sizes to fully test reactive websites.
This allows to run the test in desktop and mobile modes with potential to add even more modes into the suite.

## Browser Type

BrowserType enum allows extension for multi-browser tests, e.g. Chrome, Firefox (not fully implemented but present as an example), etc.

## Windows Manager

Please note that unused methods in WindowManager are to showcase possible interactions and the class usage in larger tests.
However, due to the size of this test suite not all needed be used.

## Potential Framework Extensions

- Taking browser screenshots
- Extent reports
- Test / scenario context with pico container
- Overwriting default test configuration by properties passed via command line, etc.

## Prerequisites

In order to execute the test provided by this framework following prerequisites are necessary:
- Docker
- Git

## Execution

Please follow the below steps to execute the test suite:
- Clone the project repository `git clone https://github.com/maciejmilgiewicz/selenium-example`
- Start the Selenium container `./docker/start.sh`
- if ports `5900` (VNC_HOST_PORT) or `4444` (HUB_HOST_PORT) on the host running the framework are already allocated, please adjust ports in `docker/start.sh` script and `browser.remote_uri` in test config `resources/config/defaults.conf` accordingly

Test configuration
```text
browser {
  type = "CHROME"
  viewport = "DESKTOP"
  remote_uri = "http://127.0.0.1:<HUB_HOST_PORT>/wd/hub"
}
```

Start script
```text
docker container run -d \
  --name selenium-chrome \
  --rm \
  -p <VNC_HOST_PORT>:5900 \
  -p <HUB_HOST_PORT>:4444 \
  -v /dev/shm:/dev/shm \
  selenium/standalone-chrome:latest
```
- You can change the default DESKTOP test mode by modifying `browser.viewport` configuration under `resources/config/defaults.conf`, possible modes are DESKTOP and MOBILE
- There are two methods to run cucumber tests presented in this framework
  - Gradle test task `./gradlew clean test`, wired in via RunCucumberTest class
  - Custom Gradle uiTest task `./gradlew clean uiTest` defined in `build.gradle`. It is also possible to pass test tags, e.g. `./gradlew clean uiTest -Ptags='@CheckCarDetails'`
- Selenium container browser can be observed for debugging purposes via VNC viewer on `vnc://<HOST_IP>:<VNC_HOST_PORT>`, e.g. `vnc://localhost:5900`. The VNC password is **secret**
- To tear the container after tests, please run `./docker/stop.sh`
- Cucumber and json reports can be found in `target/` directory after test execution

## Author

Maciej Milgiewicz
